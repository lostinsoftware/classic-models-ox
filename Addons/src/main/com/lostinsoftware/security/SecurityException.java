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
public class SecurityException extends Exception {

  private static final long serialVersionUID = 5293572360472236503L;

  public SecurityException() {
    super();
  }

  public SecurityException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public SecurityException(String message, Throwable cause) {
    super(message, cause);
  }

  public SecurityException(String message) {
    super(message);
  }

  public SecurityException(Throwable cause) {
    super(cause);
  }

}
