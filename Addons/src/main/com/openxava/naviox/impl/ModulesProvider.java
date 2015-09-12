package com.openxava.naviox.impl;

import java.util.*;
import org.openxava.application.meta.*;
import org.openxava.util.*;

/**
 * 
 * @author Javier Paniza
 */

public class ModulesProvider {
	
	private static List<MetaModule> all;
	
	public static List<MetaModule> getAll() {
		if (Users.getCurrent() == null) return Collections.EMPTY_LIST;
		if (all == null) all = createAll();
		return all;
	}

	private static List<MetaModule> createAll() {
		List<MetaModule> result = new ArrayList<MetaModule>();
		for (MetaModule module: MetaModuleFactory.createAll()) {
			if (module.getName().equals("SignIn")) continue;
			result.add(module);
		}
		return result;
	}
	
}
