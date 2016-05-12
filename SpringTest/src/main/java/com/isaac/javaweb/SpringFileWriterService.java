package com.isaac.javaweb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class SpringFileWriterService implements SpringWriter {	
	private String filepath;
	private File filename;
	private BufferedWriter out;
	private String filenamewithDate;
	

	public SpringFileWriterService(String filepath, String filenamewithDate) {
		super();
		this.filepath = filepath;
		this.filenamewithDate=filenamewithDate;
	}
	
	public void init(){		
		Date now= new Date();
		SimpleDateFormat dateformat= new SimpleDateFormat("yy-MM-dd-hh-mm-ss");
		filenamewithDate=filenamewithDate+dateformat.format(now)+".txt";
		filepath=filepath+"\\"+filenamewithDate;
		filename=new File(filepath);
		System.out.println(filepath);
		
		try {
			filename.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void destroy(){		
		if(out!=null){
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void write(String content) {
		try {
			out=new BufferedWriter(new FileWriter(filename));
			out.write(content);
			out.write("\r\n");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to write file!!!");
			e.printStackTrace();
		}
		
	}



}
