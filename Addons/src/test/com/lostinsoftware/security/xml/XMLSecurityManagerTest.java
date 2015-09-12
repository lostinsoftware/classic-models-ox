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
package com.lostinsoftware.security.xml;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.validation.constraints.AssertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lostinsoftware.security.SecurityException;

public class XMLSecurityManagerTest {
  
  private static String XML_SECURITY_GOOD_DATA = null;
  private static String XML_SECURITY_BAD_DATA = null;

  private static XMLSecurityManager xmlSecurityManager;
  
  @BeforeClass
  public static void initialize() {
    xmlSecurityManager = XMLSecurityManager.instance();
    XML_SECURITY_GOOD_DATA = 
        "﻿<security>  " 
            + "  <role rolename=\"admin\">  "
            + "    <permission object=\"all\" action=\"all\"/> "
            + "  </role> "
            + "  <role rolename=\"hhrr\"> "
            + "    <permission object=\"offices\" action=\"all\"/> "
            + "    <permission object=\"employees\" action=\"all\"/> "
            + "  </role> "
            + "  <role rolename=\"order\"> "
            + "    <permission object=\"orders\" action=\"all\"/> "
            + "    <permission object=\"productlines\" action=\"hide-image\"/> "
            + "    <permission object=\"products\" action=\"all\"/> "
            + "  </role> "
            + "  <role rolename=\"payment\"> "
            + "    <permission object=\"payments\" action=\"all\"/> "
            + "  </role> "
            + "  <role rolename=\"productline\"> "
            + "    <permission object=\"productlines\" action=\"all\"/> "
            + "    <permission object=\"products\" action=\"all\"/> "
            + "  </role> "
            + "   "
            + "  <user username=\"ceo\" password=\"ceo\" fullname=\"Chief Executive Officer\" roles=\"admin\"/> "
            + "  <user username=\"vp1\" password=\"vp1\" fullname=\"Vice President 1\" roles=\"payment, productline\"/> "
            + "  <user username=\"vp2\" password=\"vp2\" fullname=\"Vice President 2\" roles=\"hhrr, order\"/> "
            + "  <user username=\"cfo\" password=\"cfo\" fullname=\"Chief Financial Officer\" roles=\"payment\"/> "
            + "  <user username=\"cmo\" password=\"cmo\" fullname=\"Chief Marketing Officer\" roles=\"productline\"/> "
            + "  <user username=\"cso\" password=\"cso\" fullname=\"Chief Sales Officer\" roles=\"order\"/> "
            + "  <user username=\"chro\" password=\"coo\" fullname=\"Chief Human Resources Officer\" roles=\"hhrr\"/> "
            + "</security> ";
    XML_SECURITY_BAD_DATA = 
        "﻿<security>  " 
            + "  <role rolename=\"admin\">  "
            + "    <permission object=\"all\" action=\"all\"/> "
            + "  </role> "
            + "  <role rolename=\"hhrr\"> "
            + "    <permission object=\"offices\" action=\"all\"/> "
            + "    <permission object=\"employees\" action=\"all\"/> "
            + "  </role> "
            + "  <role rolename=\"order\"> "
            + "    <permission object=\"orders\" action=\"all\"/> "
            + "    <permission object=\"productlines\" action=\"hide-image\"/> "
            + "    <permission object=\"products\" action=\"all\"/> "
            + "  </role> "
            + "  <role rolename=\"payment\"> "
            + "    <permission object=\"payments\" action=\"all\"/> "
            + "  </role> "
            + "  <role rolename=\"productline\"> "
            + "    <permission object=\"productlines\" action=\"all\"/> "
            + "    <permission object=\"products\" action=\"all\"/> "
            + "  </role> "
            + "   "
            + "  <user username=\"ceo\" password=\"ceo\" fullname=\"Chief Executive Officer\" roles=\"admin\"/> "
            + "  <user username=\"vp1\" password=\"vp1\" fullname=\"Vice President 1\" roles=\"payment, productline\"/> "
            + "  <user username=\"vp2\" password=\"vp2\" fullname=\"Vice President 2\" roles=\"hhrr, order\"/> "
            + "  <user username=\"cfo\" password=\"cfo\" fullname=\"Chief Financial Officer\" roles=\"payment\"/> "
            + "  <user username=\"cmo\" password=\"cmo\" fullname=\"Chief Marketing Officer\" roles=\"productline\"/> "
            + "  <user username=\"cso\" password=\"cso\" fullname=\"Chief Sales Officer\" roles=\"order\"/> "
            + "  <user username=\"chro\" password=\"coo\" fullname=\"Chief Human Resources Officer\" roles=\"hhrr\"/> "
            + "</security ";

  }

  // @Ignore
  @Test(expected=SecurityException.class)
  public void testInitBadResource() throws SecurityException {
    InputStream stream = 
        new ByteArrayInputStream(XML_SECURITY_BAD_DATA.getBytes(StandardCharsets.UTF_8));    
    xmlSecurityManager.init(stream);
  }

  // @Ignore
  @Test
  public void testValidUser() throws SecurityException {
    InputStream stream = 
        new ByteArrayInputStream(XML_SECURITY_GOOD_DATA.getBytes(StandardCharsets.UTF_8));    
    xmlSecurityManager.init(stream);
    assertTrue(xmlSecurityManager.validUser("ceo", "ceo"));
    assertFalse(xmlSecurityManager.validUser("", "ceo"));
    assertFalse(xmlSecurityManager.validUser("vp1", "ceo"));
  }

  @Test
  public void testHasRole() throws SecurityException {
    InputStream stream = 
        new ByteArrayInputStream(XML_SECURITY_GOOD_DATA.getBytes(StandardCharsets.UTF_8));    
    xmlSecurityManager.init(stream);
    assertTrue(xmlSecurityManager.hasRole("vp2", "order"));
    assertFalse(xmlSecurityManager.hasRole("vp2", "admin"));
  }

  @Test
  public void testHasPermission() throws SecurityException {
    InputStream stream = 
        new ByteArrayInputStream(XML_SECURITY_GOOD_DATA.getBytes(StandardCharsets.UTF_8));    
    xmlSecurityManager.init(stream);
    assertTrue(xmlSecurityManager.hasPermission("vp2", "productlines", "hide-image"));
    assertFalse(xmlSecurityManager.hasPermission("vp2", "productlines", "all"));
  }

  @Test
  public void testHasAnyObject() throws SecurityException {
    InputStream stream = 
        new ByteArrayInputStream(XML_SECURITY_GOOD_DATA.getBytes(StandardCharsets.UTF_8));    
    xmlSecurityManager.init(stream);
    assertTrue(xmlSecurityManager.hasAnyObject("vp2", "all", "products"));
    assertFalse(xmlSecurityManager.hasAnyObject("vp2", "all", "payments"));
  }
}
