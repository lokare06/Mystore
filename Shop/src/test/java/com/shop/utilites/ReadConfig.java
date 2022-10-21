package com.shop.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
Properties pro;

	

	public ReadConfig() {

	File src=new File("./Configuration/config.properties");

	

	try {

		FileInputStream fis=new FileInputStream(src);

		pro=new Properties();

		pro.load(fis);

		

	}catch(Exception e) {

	System.out.println("Exception is"+e.getMessage());

	}

	}

	

	public String getApplicationURL()

	{

		String url=pro.getProperty("baseURL");

		return url;

	}

	public String getchromepath()

	{

		String path=pro.getProperty("chromepath");

		return path;

	}
	/*	public String getEmailId()

	{

	String uname=pro.getProperty("emailId");

	return uname;

	}

	public String getpassword()

	{

	String pwd=pro.getProperty("pwd");

	return pwd;

	}*/
}
