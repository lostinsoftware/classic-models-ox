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

import java.util.Map;

import com.lostinsoftware.security.Role;

/**
 * @author lostinsoftware
 *
 */
public abstract class RoleAbstract implements Role {

  private String rolename;
  private Map<String, String> permissions;

  @Override
  public String getRolename() {
    return rolename;
  }

  @Override
  public void setRolename(String rolename) {
    this.rolename = rolename;
  }

  @Override
  public Map<String, String> getPermissions() {
    return permissions;
  }

  @Override
  public void setPermissions(Map<String, String> permissions) {
    this.permissions=permissions;
  }

}
