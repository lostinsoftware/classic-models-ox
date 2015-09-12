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
package com.lostinsoftware.security.base;

import java.util.List;
import java.util.Map;

import com.lostinsoftware.security.Role;
import com.lostinsoftware.security.User;

/**
 * @author lostinsoftware
 *
 */
public abstract class UserAbstract implements User {

  private String username;
  private String password;
  private String fullname;
  private List<Role> roles;
  
  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  
  
  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getFullname() {
    return fullname;
  }

  @Override
  public List<Role> getRoles() {
    return roles;
  }

  @Override
  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
  
}
