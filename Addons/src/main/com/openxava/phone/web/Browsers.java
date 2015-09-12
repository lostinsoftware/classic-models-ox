package com.openxava.phone.web;

import javax.servlet.http.*;

/**
 * 
 * @since 5.1
 * @author Javier Paniza
 */

public class Browsers {
	
	public static boolean isMobile(HttpServletRequest request) {
		String browser = request.getHeader("user-agent");
		return browser != null && ((browser.contains("Android") && browser.contains("Mobile")) || browser.contains("iPhone")); 
	}
	
	/**
	 * @since 5.1.1
	 */
	public static String getCSSClass(HttpServletRequest request) {
		return isIPhone(request)?"iphone":"";
	}
	
	private static boolean isIPhone(HttpServletRequest request) {
		String browser = request.getHeader("user-agent");
		return browser != null && browser.contains("iPhone");
	}
	
}
