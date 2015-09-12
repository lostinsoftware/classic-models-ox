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

import java.io.IOException;
import java.net.URL;

import com.lostinsoftware.security.xml.XMLSecurityManager;

/**
 * @author lostinsoftware
 *
 */
public class SecurityManagerFactory {
  private final static String XML_SECURIYY_FILE = "security.xml";
  
  public static String TYPE_XML= "xml";
  public static SecurityManager buildSecurityManager (String type) throws SecurityException {
    
    SecurityManager securityManager=null;
    if (TYPE_XML.equalsIgnoreCase(type)) {
      securityManager = XMLSecurityManager.instance();
      URL url = SecurityManagerFactory.class.getClassLoader().getResource(XML_SECURIYY_FILE);
      try {
        securityManager.init(url.openStream());
      } catch (IOException e) {
        throw new SecurityException(e);
      }
    } else {
      throw new SecurityException("Invalid security type " + type);
    }
    return securityManager;
  }

}
