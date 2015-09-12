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
package com.lostinsoftware.classicmodels.actions;

import org.openxava.actions.TabBaseAction;

import com.lostinsoftware.security.Security;

/**
 * @author lostinsoftware
 *
 */
public class HideProductImageAction extends TabBaseAction {

  @Override
  public void execute() throws Exception {
    boolean hideImage = Security.hasPermission(Security.OBJ_PRODUCTLINES
            , Security.ACT_HIDE_IMAGE);
    getView().setHidden("image", hideImage);  // Hide in detail mode
    getTab().getMetaProperty("image").setHidden(hideImage); // Hide in list mode
    
  }

}
