package com.openxava.naviox.impl;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.parse.*;
import org.openxava.application.meta.*;
import org.openxava.util.*;

/**
 * 
 * @author Javier Paniza
 */

public class MetaModuleFactory {
	
	private static String application; 

	public static MetaModule create(String application, String module) {		
		return MetaApplications.getMetaApplication(application).getMetaModule(module);				
	}
	
	public static List<MetaModule> createAll() {
		MetaApplication app = MetaApplications.getMetaApplication(application);
		createDefaultMetaModules(app);
		createAdditionalMetaModules(app);
		return new ArrayList<MetaModule>(app.getMetaModules());
	}

	private static void printModules(MetaApplication app) {
		for (Object m: app.getMetaModules()) {
			String nombre = ((MetaModule) m).getName();
		}
	}

	private static void createDefaultMetaModules(MetaApplication app) {
		for (String className: AnnotatedClassParser.getManagedClassNames()) {
			if (className.endsWith(".GalleryImage") || className.endsWith(".AttachedFile")) continue;
			if (isEmbeddable(className)) continue;
			app.getMetaModule(Strings.lastToken(className, "."));
		}		
	}
	
	private static void createAdditionalMetaModules(MetaApplication app) {
		for (String moduleName: AdditionalModules.get()) {
			app.getMetaModule(moduleName);
		}		
	}
	

	private static boolean isEmbeddable(String className) {
		try {
			return Class.forName(className).isAnnotationPresent(Embeddable.class);
		} 
		catch (ClassNotFoundException e) {
			return false;
		}		
	}

	public static String getApplication() {  
		return application;
	}	
	
	public static void setApplication(String newApplication) { 
		application = newApplication;
	}

}
