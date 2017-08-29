package de.sloth.core.main.loader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * @author Slothking
 * @version V1.0.0
 */
public class ConfigLoader extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8888750979750614945L;
	private static ConfigLoader instance;
	private String configName;
	private File configFile;
	
	public static ConfigLoader getInstance(String configName) {
		if(instance == null) {
			instance = new ConfigLoader(configName);
		}
		return instance;
	}
	
	public ConfigLoader(String configName) {
		this.configName = configName;
		this.configFile = new File(".\\config\\" + this.configName);
	}
	
	public static ConfigLoader getInstance() {
		if(instance == null) {
			instance = new ConfigLoader();
		}
		return instance;
	}
	
	public ConfigLoader() {
		this.configName = "gameconfig";
		this.configFile = new File(".\\config\\" + this.configName);
	}
	
	private void refresh() {
		try {
			if(!configFile.exists()) {
				configFile.createNewFile();
			}
			this.load(new FileReader(this.configFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		try {
			this.store(new FileWriter(configFile), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addConfig(String key, String value) {
		this.put(key, value);
		save();
	}
	
	public String getConfig(String key, String stdValue) {
		refresh();
		String value = (String) this.get(key);
		if(value == null) {
			this.addConfig(key, stdValue);
			return stdValue;
		}
		return value;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public File getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFilePath) {
		ConfigLoader.instance = new ConfigLoader();
		ConfigLoader.instance.configFile = new File(configFilePath);
	}
}