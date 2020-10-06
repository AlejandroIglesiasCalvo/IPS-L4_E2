package confg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conf {
	
	private static Conf instance;
	private Properties properties;
	private final static String CONF_FILE ="resources/configuration.properties";
	
			private Conf(){
		properties = new Properties();
		try{
			final ClassLoader loader = getClass().getClassLoader();
			InputStream config = loader.getResourceAsStream(CONF_FILE);
			properties.load(config);
		}
		catch(IOException e){
			throw new RuntimeException("Porperties file can not loader", e);
		}
	}
	
	public static String get(String key){
		return getInstance().getProperty(key);
	}
	
	private String getProperty(String key){
		String value = properties.getProperty(key);
		if(value==null){
			throw new RuntimeException("Porperties not found in config file");
		}
		return value;
	}

	private static Conf getInstance() {
		if(instance==null){
			instance = new Conf();
		}
		return instance;
	}

}