package com.dopCN.utilities;

import java.io.FileInputStream;
import java.util.Properties;


public class ReadConfigProperty {
	
	Properties prop;
	
	public ReadConfigProperty() {
		
		try {
			FileInputStream fip = new FileInputStream("./src/test/resources/config.properties");
			prop = new Properties();
			prop.load(fip);
		} catch (Exception e) {
			System.out.println("Properties file is not available at specified location" + e.getMessage());
		}
		
	}

	public String getBrowserName() {
		String browser = prop.getProperty("browser");
		return browser;
	}
	
	public String getURL() {
		String url = prop.getProperty("url");
		return url;
	}
	
	public String getUserName() {
		String useName = prop.getProperty("useName");
		return useName;
	}
	
	public String getpassword() {
		String password = prop.getProperty("password");
		return password;
	}

}
