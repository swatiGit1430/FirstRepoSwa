package com.FirstProj.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

	public ReadConfig() {
		
		File src = new File("./Configuration/config.properties");
		
		try {
			
			FileInputStream fis = new FileInputStream(src);
			properties= new Properties();
			properties.load(fis);
			
		}catch(Exception e) {
			
			System.out.println("Exception is: "+e.getMessage());
		}

	}

	public String getUserName() {

		return properties.getProperty("userName");
	}

	public String getPassword() {

		return properties.getProperty("password");
	}

	public String getExternalURL() {

		return properties.getProperty("externalURL");
	}

	public String getChromeDriver() {

		return properties.getProperty("chromeDriver");
	}
	
	public String getIEDriver() {

		return properties.getProperty("ieDriver");
	}
	
	public String getFirefoxDriver() {

		return properties.getProperty("firefoxDriver");
	}

}
