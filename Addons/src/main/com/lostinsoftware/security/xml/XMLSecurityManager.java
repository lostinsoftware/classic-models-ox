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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openxava.util.Is;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.lostinsoftware.security.Role;
import com.lostinsoftware.security.SecurityException;
import com.lostinsoftware.security.SecurityManager;
import com.lostinsoftware.security.User;

/**
 * @author lostinsoftware
 *
 */
public class XMLSecurityManager implements SecurityManager {

  private static final String TAG_USER = "user";
  private static final String TAG_ROLE = "role";

  private static final String ATT_USERNAME = "username";
  private static final String ATT_PASSWORD = "password";
  private static final String ATT_FULLNAME = "fullname";
  private static final String ATT_ROLES = "roles";
  private static final String ATT_ROLENAME = "rolename";
  private static final String ATT_OBJECT = "object";
  private static final String ATT_ACTION = "action";

  private static XMLSecurityManager instance = null;
  private Map<String, User> users = new HashMap<String, User>();
  private Map<String, Role> roles = new HashMap<String, Role>();
  private Map<String, Map<String, String>> userPermissions = 
      new HashMap<String, Map<String, String>>();
  

  private XMLSecurityManager() {
  }

  public static XMLSecurityManager instance() {
    if (instance == null)
      instance = new XMLSecurityManager();
    return instance;
  };

  @Override
  public void init(Object securitySource) throws SecurityException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setValidating(false);
    DocumentBuilder db;
    try {
      db = dbf.newDocumentBuilder();
      db.setEntityResolver(new EntityResolver() {
        @Override
        public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException {
          return null;
        }
      });
      Document root = db.parse((InputStream) securitySource);
      loadRoles(root);
      loadUsers(root);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new SecurityException(e);
    }
  }

  private void loadRoles(Document root) {
    NodeList nl = root.getElementsByTagName(TAG_ROLE);
    for (int i = 0; i < nl.getLength(); i++) {
      Node node = nl.item(i);
      NamedNodeMap nnm = node.getAttributes();
      if (nnm == null) continue;
      Node nodAux = nnm.getNamedItem(ATT_ROLENAME);
      if (nodAux == null) continue;
      String rolename = nodAux.getNodeValue();
      if (Is.empty(rolename)) continue;
      Role role = new XMLRole();
      role.setRolename(rolename);
      Map<String, String> permissions = new HashMap<String, String>();
      role.setPermissions(permissions);
      roles.put(rolename, role);
      
      // Load permissions
      NodeList children = node.getChildNodes();
      if (children==null) continue;
      for (int j=0; j<children.getLength(); j++){
        nnm = children.item(j).getAttributes();
        if (nnm == null) continue;
        nodAux = nnm.getNamedItem(ATT_OBJECT);
        if (nodAux == null) continue;
        String object = nodAux.getNodeValue();
        nodAux = nnm.getNamedItem(ATT_ACTION);
        if (nodAux == null) continue;
        String action = nodAux.getNodeValue();
        if (Is.empty(object) || Is.empty(action)) continue;
        permissions.put(object, action);
      }
    }

  }

  private void loadUsers(Document root) {
    NodeList nl = root.getElementsByTagName(TAG_USER);
    for (int i=0; i<nl.getLength(); i++) {
      Node node = nl.item(i);
      NamedNodeMap nnm = node.getAttributes();
      if (nnm == null) continue;
      Node nodAux = nnm.getNamedItem(ATT_USERNAME);
      if (nodAux == null) continue;
      String username = nodAux.getNodeValue();
      if (Is.empty(username)) continue;

      nodAux = nnm.getNamedItem(ATT_PASSWORD);
      if (nodAux == null) continue;
      String password = nodAux.getNodeValue();
      if (Is.empty(password)) continue;

      User user = new XMLUser();
      user.setUsername(username);
      user.setPassword(password);
      users.put(username, user);
      List<Role> lstRoles = new ArrayList<Role>();
      user.setRoles(lstRoles);

      nodAux = nnm.getNamedItem(ATT_FULLNAME);
      if (nodAux == null) continue;
      String fullname = nodAux.getNodeValue();
      user.setFullname(fullname);
      
      nodAux = nnm.getNamedItem(ATT_ROLES);
      if (nodAux == null) continue;
      String listroles = nodAux.getNodeValue();
      if (Is.empty(listroles)) continue;
      String[] arrRoles = listroles.split(",");
      for (String role: arrRoles) {
        Role r = roles.get(role.trim());
        if (r!=null) {
          lstRoles.add(r);
        }
      }
    }
  }

  @Override
  public boolean validUser(String username, String password) {
    if (Is.empty(password) || Is.empty(username)) return false;
    User user = users.get(username);
    if (user==null) return false;
    return password.equals(user.getPassword());
  }


  public User getUser(String username)  {
    if (Is.empty(username)) return null;
    User user = users.get(username);
    return user;
      
  }
  
  private Map<String, String> loadPermissions(User user) {
    if (user==null) return new HashMap<String, String>();
    Map<String, String> permissions = new HashMap<String, String>();
    if (Is.empty(user.getRoles())) return new HashMap<String, String>();
    for (Role role: user.getRoles()) {
      if (Is.empty(role.getPermissions())) continue;
      permissions.putAll(role.getPermissions());
    }
    return permissions;
  }
  
  @Override
  public boolean hasPermission(String username, String object, String action) {
    if (Is.empty(username)) return false;
    Map<String, String> permissions = userPermissions.get(username);
    if (permissions==null) {
      User user = getUser(username);
      permissions = loadPermissions(user);
    }
    if (permissions==null || permissions.size()==0) return false;
    String act = permissions.get(object);
    if (Is.empty(act)) return false;
    return act.equals(action);
  }

  @Override
  public boolean hasRole(String username, String rolename) {
    User user = getUser(username);
    if (user==null) return false;
    return user.getRoles().stream()
        .anyMatch(r -> r.getRolename().equals(rolename));
  }
  
  @Override
  public boolean hasAnyObject(String username, String ... objects ) {
    if (Is.empty(username)) return false;
    if (Is.emptyString(objects)) return false;
    Map<String, String> permissions = userPermissions.get(username);
    if (permissions==null) {
      User user = getUser(username);
      permissions = loadPermissions(user);
    }
    if (permissions==null || permissions.size()==0) return false;
    List<String> lstObjects = Arrays.asList(objects);
    return permissions.keySet().stream().anyMatch(r->lstObjects.contains(r));
  }

}
