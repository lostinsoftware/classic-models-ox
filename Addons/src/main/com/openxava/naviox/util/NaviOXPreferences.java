package com.openxava.naviox.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.util.*;

/**
 * To get the preferences from naviox.properties
 * 
 * The properties encryptPassword, storePasswordAsHex and all ldap properties are obtained
 * directly from User class for not creating a compilation dependency between User.java and naviox.jar. 
 * 
 * @author Javier Paniza
 */
public class NaviOXPreferences {

	private final static String PROPERTIES_FILE = "naviox.properties";
	private static Log log = LogFactory.getLog(NaviOXPreferences.class);
	private static NaviOXPreferences instance;
	private boolean testingAutologin = false; 
	private Properties properties;

	private NaviOXPreferences() {
	}

	public static NaviOXPreferences getInstance() {
		if (instance == null) {
			instance = new NaviOXPreferences();
		}
		return instance;
	}
	
	public static void startTestingAutologin() { 
		getInstance().testingAutologin = true;
	}
	
	public static void stopTestingAutologin() { 
		getInstance().testingAutologin = false;
	}

	private Properties getProperties() {
		if (properties == null) {
			PropertiesReader reader = new PropertiesReader(
					XavaPreferences.class, PROPERTIES_FILE);
			try {
				properties = reader.get();
			} catch (IOException ex) {
				log.error(XavaResources.getString("properties_file_error",
						PROPERTIES_FILE), ex);
				properties = new Properties();
			}
		}
		return properties;
	}
		
	public String getAutologinUser() { 
		if (testingAutologin) return "user"; // Only for testing purposes
		return getProperties().getProperty("autologinUser", "").trim();
	}
	
	public String getAutologinPassword() {
		if (testingAutologin) return "user"; // Only for testing purposes
		return getProperties().getProperty("autologinPassword", "").trim();
	}
	
	private String getCreateSchema() {
		return getProperties().getProperty("createSchema", "CREATE SCHEMA ${schema}").trim();
	}
	
	/**
	 * @since 5.2
	 */
	public String getCreateSchema(String database) {
		return getProperties().getProperty("createSchema." + database, getCreateSchema()).trim();
	}
	
	/**
	 * @since 5.3
	 */
	public boolean isStartInLastVisitedModule() { 
		return "true".equalsIgnoreCase(getProperties().getProperty("startInLastVisitedModule", "true").trim());
	}
	
	/**
	 * @since 5.3
	 */
	public boolean isRememberVisitedModules() { 
		return "true".equalsIgnoreCase(getProperties().getProperty("rememberVisitedModules", "true").trim());
	}
	
	/**
	 * 
	 */
	public String getSecurityManagerFactoryType() {
    return getProperties().getProperty("securityManagerFactoryType", "xml").trim();
	}
}
