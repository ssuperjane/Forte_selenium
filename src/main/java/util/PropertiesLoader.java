package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	String fileName = "parameters.properties";
	
	public String getProperty(String property){
		
		String value = null;
		InputStream stream = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName);
		
		if (stream==null){
			throw new RuntimeException("Could not find the file :"+fileName);
		}
		
		Properties props = new Properties();
		
		try {
			props.load(stream);
		} catch (IOException e){
			throw new RuntimeException("error in loading files"+fileName);
		}
		value=props.getProperty(property);
		
		return value;
	}
	
	//this method aimed to convert String to Boolean 
	public boolean getPropertyAsBoolean(String property){
		boolean result=true;
		String value = getProperty(property);
		result = Boolean.parseBoolean(value); //converting from String to boolean
		return result;
	}
	
	//this method aimed to convert String to Int
	public int getPropertyAsInt(String property){
		int result=0;
		String value = getProperty(property);
		result = Integer.parseInt(value); //converting from String to boolean
		return result;
	}
}
