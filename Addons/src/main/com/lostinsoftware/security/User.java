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

import java.util.List;
import java.util.Map;

/**
 * @author lostinsoftware
 *
 */
public interface User {

  public void setUsername(String username);
  public String getUsername();
  public void setPassword(String password);
  public String getPassword();
  public void setFullname(String fullname);
  public String getFullname();
  public List<Role> getRoles();
  public void setRoles(List<Role> roles);
}
