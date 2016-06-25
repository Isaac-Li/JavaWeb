package com.isaac.javaweb.spring.finalexam.web.controller;




import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IProductService;


@Controller
@SessionAttributes("loginuser")
public class SellerController {
	
	public final static int IMAGE_SIZE=1024*1024;
	
	@Autowired
	private IProductService productservice;

	
	@RequestMapping(value="/public")
	public String sellerPublic(@ModelAttribute("loginuser") User user, Model model){		
		model.addAttribute("user", user); 
		return "public";
	}
	
	@RequestMapping(value="/publicSubmit")
	public void publicSubmit(@ModelAttribute("loginuser") User user,Model model, HttpServletRequest request){
		model.addAttribute("user", user); 		
		
		//create product instance, and set property.
		Product product=new Product();
		product.setTitle(request.getParameter("title"));
		
		Double doublePrice=new Double(request.getParameter("price"));
		doublePrice=doublePrice*100;
		
		product.setPrice(doublePrice.intValue());
		product.setBrief(request.getParameter("summary"));
		product.setText(request.getParameter("detail").getBytes());
		
				
		String imagepath;
		String dbimagepath;
		try {
			String contextrealpath=request.getSession().getServletContext().getRealPath("/");
			imagepath=readAndSaveImage(request.getParameter("image"), contextrealpath);			
			if(imagepath=="-1"){
				return;
			}else{
				dbimagepath=request.getParameter("image")+"  "+imagepath;
				product.setIcon(dbimagepath.getBytes());
			}
			
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e.getMessage());
			return;

		}
					
		productservice.addContent(product);
		model.addAttribute("product", product);
		
		return;
	}
	
	
	//read file by using url address
	public String readAndSaveImage (String urladdress, String realpath) throws IOException{
		
		URL url=new URL(urladdress);
		URLConnection conn=url.openConnection();
		int imagesize=conn.getContentLength();

		
		//check file size
		if(imagesize>IMAGE_SIZE || imagesize<0){
			return "-1";
		}
		
		InputStream is=url.openStream();
		
		

		
		//get file extension name
		String fileextensionname=urladdress.substring(urladdress.lastIndexOf("."));
	
		//save image to local folder
		Date now=new Date();
		SimpleDateFormat dateformat= new SimpleDateFormat("yy-MM-dd-hh-mm-ss");
		String newfilename=realpath+"img/"+dateformat.format(now)+fileextensionname;		
		
		FileOutputStream fileout=new FileOutputStream(newfilename);
				
		
		//read image file from url 
		byte[] filecontent=new byte[imagesize];
		int len=0;
		while((len=is.read(filecontent))!=-1){
			fileout.write(filecontent, 0,len);	
		}	
		
		is.close();
		fileout.close();

		return newfilename;
	}
	
	@RequestMapping(value="/show")
	public  void showProduct(@ModelAttribute("loginuser") User user, Model model, @RequestParam("id") int productid){

		Product product=new Product();
		product.setContentid(productid);
		
		product=productservice.getContentInfo(product);
		
		Map<String, Object> result = new HashMap<String, Object>();
		String imagerelativepath="";
		String tempstring=new String(product.getIcon());
		imagerelativepath=tempstring.substring(tempstring.lastIndexOf("/"));
		
				
		result.put("image", imagerelativepath);
		result.put("title", product.getTitle());
		result.put("summary", product.getBrief());
		result.put("price", product.getPrice()/100.0);
		result.put("id", product.getContentid());
		result.put("detail", new String(product.getText()));
						
		model.addAttribute("product", result);
		model.addAttribute("user", user); 
		return ;
		
	}
	
	@RequestMapping(value="/edit")
	public  void editProduct(@ModelAttribute("loginuser") User user, Model model, @RequestParam("id") int productid){

		Product product=new Product();
		product.setContentid(productid);
		
		product=productservice.getContentInfo(product);
		
		Map<String, Object> result = new HashMap<String, Object>();
		String imagerelativepath="";
		String tempstring=new String(product.getIcon());
		
		imagerelativepath=getIconAddressFromDBPath(tempstring, true);
				
		result.put("image", imagerelativepath.trim());
		result.put("title", product.getTitle());
		result.put("summary", product.getBrief());
		result.put("price", product.getPrice()/100.0);
		result.put("id", product.getContentid());
		result.put("detail", new String(product.getText()));
						
		model.addAttribute("product", result);
		model.addAttribute("user", user); 
		return;
	}
	
	public String getIconAddressFromDBPath(String src, Boolean bURL){
		
		if(bURL){
			return src.substring(0, src.indexOf(" ")).trim();
		}else
		{
			return src.substring(src.indexOf(" ")).trim();
		}	
		
	}
}
