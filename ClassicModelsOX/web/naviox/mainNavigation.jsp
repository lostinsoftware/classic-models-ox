<%@include file="../xava/imports.jsp"%>

<%@page import="java.util.Iterator"%>
<%@page import="org.openxava.application.meta.MetaModule"%>
<%@page import="org.openxava.util.Users"%>
<%@page import="org.openxava.util.Is"%>
<%@page import="com.openxava.naviox.Modules"%>
<%@page import="com.openxava.naviox.util.NaviOXPreferences"%>
<%@page import="com.lostinsoftware.security.Security"%>

<jsp:useBean id="modules" class="com.openxava.naviox.Modules" scope="session"/>
<!-- 
<div role="navigation" class="navbar navbar-inverse navbar-fixed-top">
 -->
  <div class="container-fluid">
    <!-- Toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar-collapse" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
    
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="main-navbar-collapse">
 <% 
if (modules.hasModules()) {
 %>
      <ul class="nav navbar-nav">
    <%
    if (Security.hasAnyObject(Security.OBJ_OFFICES, Security.OBJ_EMPLOYEES, Security.OBJ_ALL)) {
    %>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><xava:message key="hhrr"/></a>
          <ul class="dropdown-menu">
				    <%
				    if (Security.hasAnyObject(Security.OBJ_OFFICES, Security.OBJ_ALL)) {
				    %>
            <li><a href='<%=modules.getModuleURI(request, "Offices")%>?retainOrder=true'><xava:message key="offices"/></a></li>
				    <%
				    }
				    if (Security.hasAnyObject(Security.OBJ_EMPLOYEES, Security.OBJ_ALL)) {
				    %>
            <li><a href='<%=modules.getModuleURI(request, "Employees")%>?retainOrder=true'><xava:message key="employees"/></a></li>
				    <%
				    }
				    %>
          </ul>
        </li>
    <%
    } // Human resources 
    if (Security.hasAnyObject(Security.OBJ_PRODUCTLINES, Security.OBJ_PRODUCTS, Security.OBJ_ALL)) {
    %> 
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><xava:message key="products"/></a>
          <ul class="dropdown-menu">
	          <%
	          if (Security.hasAnyObject(Security.OBJ_PRODUCTLINES, Security.OBJ_ALL)) {
	          %>
            <li><a href='<%=modules.getModuleURI(request, "Productlines")%>?retainOrder=true'><xava:message key="productlines"/></a></li>
            <%
            }
            if (Security.hasAnyObject(Security.OBJ_PRODUCTS, Security.OBJ_ALL)) {
            %>
            <li><a href='<%=modules.getModuleURI(request, "Products")%>?retainOrder=true'><xava:message key="products"/></a></li>
            <%
            }
            %>
          </ul>
        </li>
    <%
    } // Products
    if (Security.hasAnyObject(Security.OBJ_CUSTOMERS, Security.OBJ_ORDERS, Security.OBJ_ALL)) {
    %> 
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><xava:message key="orders"/></a>
          <ul class="dropdown-menu">
            <%
            if (Security.hasAnyObject(Security.OBJ_CUSTOMERS, Security.OBJ_ALL)) {
            %>
            <li><a href='<%=modules.getModuleURI(request, "Customers")%>?retainOrder=true'><xava:message key="customers"/></a></li>
            <%
            }
            if (Security.hasAnyObject(Security.OBJ_ORDERS, Security.OBJ_ALL)) {
            %>
            <li><a href='<%=modules.getModuleURI(request, "Orders")%>?retainOrder=true'><xava:message key="orders"/></a></li>
            <%
            }
            %>
          </ul>
        </li>
    <%
    } // End Products
    if (Security.hasAnyObject(Security.OBJ_PAYMENTS, Security.OBJ_ALL)) {
    %> 
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><xava:message key="payments"/></a>
          <ul class="dropdown-menu">
            <li><a href='<%=modules.getModuleURI(request, "Payments")%>?retainOrder=true'><xava:message key="payments"/></a></li>
          </ul>
        </li>
    <%
    } // End Payments
    %>
      </ul>
	  <%
    
	  if (Is.emptyString(NaviOXPreferences.getInstance().getAutologinUser())) {
	    String userName = Users.getCurrent();
	   %>
      <ul class="nav navbar-nav navbar-right">
 	    <li><a  href="<%=request.getContextPath()%>/naviox/signOut.jsp" class="sign-in"><xava:message key="signout"/> (<%=userName%>)</a></li>
 	    </ul>
	<%
	  }
	%> <!-- no modules -->
<% } else { 
     if (Is.emptyString(NaviOXPreferences.getInstance().getAutologinUser())) {
		  %>
      <ul class="nav navbar-nav navbar-right">
        <li>
				  <a href="<%=request.getContextPath()%>/m/SignIn" >
				      <xava:message key="signin"/>
				  </a>
				</li>
		  </ul>
		  <%
		   }
  } %>
   </div> <!--  end div .navbar-collapse -->
 </div> <!--  end div .ncontainer-fluid -->
 <!--
</div>   end div main navbar -->

