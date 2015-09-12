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

/**
 * @author lostinsoftware
 *
 */
public interface SecurityManager {
  
  public void init(Object securitySource) throws SecurityException;
  public boolean validUser(String username, String password);
  public boolean hasRole(String username, String rolename);
  public boolean hasPermission(String username, String object, String action);
  public boolean hasAnyObject(String username, String ... objects);
  
  
}
