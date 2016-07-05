package com.isaac.javaweb.spring.finalexam.web.controller;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;


import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.meta.ProductForWeb;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IProductService;
import com.isaac.javaweb.spring.finalexam.service.impl.ProductServiceImpl;


@Controller
@SessionAttributes("loginuser")
public class SellerController {
	
	public final static int IMAGE_SIZE=1024*1024;
	
	@Autowired
	private IProductService productservice;

	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value="/public")
	public String sellerPublic(@ModelAttribute("loginuser") User user, Model model){		
		model.addAttribute("user", user); 
		return "public";
	}
	
	@RequestMapping(value="/publicSubmit")
	public void publicSubmit(@ModelAttribute("loginuser") User user,Model model){
		model.addAttribute("user", user); 		
		
		//create product instance, and set property.
		Product product=new Product();
		product.setTitle(request.getParameter("title"));
		
		Double doublePrice=new Double(request.getParameter("price"));
		doublePrice=doublePrice*100;
		
		product.setPrice(doublePrice.intValue());
		product.setBrief(request.getParameter("summary"));
		product.setText(request.getParameter("detail").getBytes());
		

		//get image path
		String dbimagepath=getDBImagePath(request.getParameter("pic"));

		if (dbimagepath.equals("-1")){
			return;
		}else
		{
			product.setIcon(dbimagepath.getBytes());
		}

					
		productservice.addContent(product);
		model.addAttribute("product", product);
		
		return;
	}
	
	
	//read file by using url address
	public String readAndSaveImageFromURL (String urladdress, String realpath) throws IOException{
		
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
		String newfilename=realpath+"image/"+dateformat.format(now)+fileextensionname;		
		
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
	
	//this function is working for local image
	public String prepareLocalImage(String localaddress){
		String contextrealpath=request.getSession().getServletContext().getRealPath("/");
		//get file extension name
		String fileextensionname=localaddress.substring(localaddress.lastIndexOf("."));
		Date now=new Date();
		SimpleDateFormat dateformat= new SimpleDateFormat("yy-MM-dd-hh-mm-ss");
		String newfilename=contextrealpath+"image/"+dateformat.format(now)+fileextensionname;		
		String srcfilename=contextrealpath+localaddress;
		
		File srcfile=new File(srcfilename);
		File tarfile=new File(newfilename);
		
		Boolean bresult=srcfile.renameTo(tarfile);
		
		if(bresult){
			return newfilename;
		}
		
		return "-1";
	}
	
	//get DB image path
	public String getDBImagePath(String address){
		String imagepath;
		String dbimagepath;
				
		if(address.equals("url")){		
			try {
				String contextrealpath=request.getSession().getServletContext().getRealPath("/");
				imagepath=readAndSaveImageFromURL(request.getParameter("image"), contextrealpath);
								
				if(imagepath.equals("-1")){
					return imagepath;
				}else{
					dbimagepath=request.getParameter("image")+"  "+imagepath;					 
				}
				
			} catch (IOException e) {
				System.out.println("IOException");
				System.out.println(e.getMessage());
				return "-1";
			}
		}else{
			imagepath=prepareLocalImage(request.getParameter("image"));

			if(imagepath.equals("-1")){
				return imagepath;
			}else{
				dbimagepath=imagepath+"  "+imagepath;				
			}
			
		}		
		return dbimagepath;
	}
	@RequestMapping(value="/show")
	public  void showProduct(ModelMap map,Model model, @RequestParam("id") int productid){
		
		Product product=new Product();
		product.setContentid(productid);
		
		product=productservice.getContentInfo(product);
		
		ProductForWeb productforweb=new ProductForWeb();
		productforweb=getProductInfoForWeb(product, false);
		
		model.addAttribute("product", productforweb);
		
		if(map.containsAttribute("loginuser")){
			model.addAttribute("user", (User)map.get("loginuser"));
		}
		
		return ;
		
	}
	
	public ProductForWeb getProductInfoForWeb(Product product, Boolean isEdit){
		ProductForWeb productforweb=new ProductForWeb();
		
		String imagerelativepath="";
		String tempstring=new String(product.getIcon());
		Boolean isHttpaddress=tempstring.contains("http");

		
		if(isEdit && isHttpaddress){
			imagerelativepath=tempstring.substring(0, tempstring.indexOf(" ")).trim();
		}else{
			imagerelativepath=request.getSession().getServletContext().getContextPath()+"/image"+tempstring.substring(tempstring.lastIndexOf("/"));
		}
		
		
		productforweb.setImage(imagerelativepath.trim());
		productforweb.setTitle(product.getTitle());
		productforweb.setSummary(product.getBrief());
		productforweb.setPrice(product.getPrice()/100.0);
		productforweb.setId(product.getContentid());
		productforweb.setDetail(new String(product.getText()));
		
		if(product.getTrxes().isEmpty()){
			productforweb.setIsSell(false);
			productforweb.setIsBuy(false);
			productforweb.setBuyNum(0);			
		}else{
			productforweb.setIsSell(true);
			productforweb.setIsBuy(true);
			productforweb.setBuyNum(product.getTrxes().size());
			productforweb.setBuyPrice(product.getTrxes().get(0).getPrice()/100.0);
			productforweb.setBuyTime(product.getTrxes().get(0).getTime());
		}
		

		return productforweb;
	}
	
	
	//edit
	@RequestMapping(value="/edit")
	public  void editProduct(ModelMap map, Model model, @RequestParam("id") int productid){
		
		Product product=new Product();
		product.setContentid(productid);
		
		product=productservice.getContentInfo(product);
		
		product=productservice.getContentInfo(product);
		
		ProductForWeb productforweb=new ProductForWeb();
		productforweb=getProductInfoForWeb(product, true);
		
		model.addAttribute("product", productforweb);	
		model.addAttribute("pic", "file");
		
		if(map.containsAttribute("loginuser")){
			model.addAttribute("user", (User)map.get("loginuser"));
		}
		
		return;
	}
	
	//editSubmit
	@RequestMapping(value="/editSubmit")
	public  void editSubmitProduct(ModelMap map, Model model, @RequestParam("id") int productid){
		if(map.containsAttribute("loginuser")){
			model.addAttribute("user", (User)map.get("loginuser"));
		}
		
		
		//create product instance, and set property.
		Product product=new Product();
		product.setContentid(productid);
		product.setTitle(request.getParameter("title"));
		
		Double doublePrice=new Double(request.getParameter("price"));
		doublePrice=doublePrice*100;
		
		product.setPrice(doublePrice.intValue());
		product.setBrief(request.getParameter("summary"));
		product.setText(request.getParameter("detail").getBytes());
		
		
		/////////////////////////////////////////////////////		
		//get image path 		
		String dbimagepath=getDBImagePath(request.getParameter("pic"));
		if (dbimagepath.equals("-1")){
			return;
		}else
		{
			product.setIcon(dbimagepath.getBytes());
		}
		
							
		int result=productservice.updateContent(product);		
		if(result>0){
			model.addAttribute("product", product);
		}else
		{
			model.addAttribute("productId", productid);
		}
				
		return;
	}
	

	@RequestMapping(value="/upload")
	public  @ResponseBody Object uploadFile(@RequestParam("file") MultipartFile file,  Model model){
		 if (!file.isEmpty()) {  
	            try {  
  
	                String filePath = request.getSession().getServletContext().getRealPath("/") + "image/"  
	                        + file.getOriginalFilename();               	                
	                file.transferTo(new File(filePath));  
	                
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	            String imagpath="image/"+file.getOriginalFilename();	            
	            model.addAttribute("result", imagpath);  ;
	        }  
		 
		 return model;
	}

	@RequestMapping(value="/delete")
	public  void deleteProduct(ModelMap map,Model model){
		
		Product product=new Product();
		product.setContentid(Integer.parseInt(request.getParameter("id")));
		
		int iresult=productservice.deleteContent(product);
		
		if(iresult>0){
			model.addAttribute("code", 200);
		}
		
		if(map.containsAttribute("loginuser")){
			model.addAttribute("user", (User)map.get("loginuser"));
		}
		
		return ;
		
	}
}
