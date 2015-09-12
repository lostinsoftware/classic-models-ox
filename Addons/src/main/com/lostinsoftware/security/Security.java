/*******************************************************************************
* Copyright (c) 2015 lostinsoftware. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*
* Contributors:
*          lostinsoftware - initial implementation and ongoing maintenance
*          
*******************************************************************************/
package com.lostinsoftware.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.util.Users;
import org.openxava.util.XavaResources;

import com.openxava.naviox.util.NaviOXPreferences;

/**
 * @author lostinsoftware
 *
 */
public class Security {
  
  public final static String OBJ_ALL = "all";
  public final static String OBJ_OFFICES = "Offices";
  public final static String OBJ_EMPLOYEES = "Employees";
  public final static String OBJ_CUSTOMERS = "Customers";
  public final static String OBJ_ORDERS = "Orders";
  public final static String OBJ_PRODUCTLINES = "Productlines";
  public final static String OBJ_PRODUCTS = "Products";
  public final static String OBJ_PAYMENTS = "Payments";

  public final static String ACT_ALL = "all";
  public final static String ACT_HIDE_IMAGE = "hide-image";
  
  private final static String PREFERENCES_FILE = "naviox.properties";
  private static Log log = LogFactory.getLog(Security.class);
  
  private static SecurityManager securityManager;
  
  public static String getCurrentUser() {
    return Users.getCurrent();
  }

  public static boolean validUser(String username, String password) {
    SecurityManager sm = getSecurityManager();
    if (sm==null) return false;
    return sm.validUser(username, password);
  }
  
  private static SecurityManager getSecurityManager() {
    if (securityManager == null) {
      String type = NaviOXPreferences.getInstance().getSecurityManagerFactoryType();
      try {
        securityManager = SecurityManagerFactory.buildSecurityManager(type);
      } catch (SecurityException e) {
        log.error(XavaResources.getString("properties_file_error",
            PREFERENCES_FILE), e);
      }
    }
    return securityManager;
  }
  
  public static boolean  hasPermission(String object, String action) {
    SecurityManager sm = getSecurityManager();
    if (sm==null) return false;
    return sm.hasPermission(getCurrentUser(), object, action);
  }

  public static boolean  hasRole(String role) {
    SecurityManager sm = getSecurityManager();
    if (sm==null) return false;
    return sm.hasRole(getCurrentUser(), role);
  }
  
  public static boolean hasAnyObject(String ... objects ) {
    SecurityManager sm = getSecurityManager();
    if (sm==null) return false;
    return sm.hasAnyObject(getCurrentUser(), objects);
  }


}
