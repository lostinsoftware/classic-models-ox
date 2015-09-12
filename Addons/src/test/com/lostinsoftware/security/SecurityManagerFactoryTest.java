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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class SecurityManagerFactoryTest {

  @Test
  public void testBuildXMLSecurityManager() throws SecurityException {
    SecurityManager sm = SecurityManagerFactory.buildSecurityManager(
        SecurityManagerFactory.TYPE_XML);
    assertTrue(sm.hasPermission("vp2", "Productlines", "hide-image"));
    assertFalse(sm.hasPermission("vp2", "Productlines", "all"));
  }
}
