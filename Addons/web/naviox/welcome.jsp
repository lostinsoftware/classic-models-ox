<%@include file="../xava/imports.jsp"%>

<%@page import="org.openxava.application.meta.MetaApplications"%>
<%@page import="org.openxava.application.meta.MetaApplication"%>

<%-- To put your own text add entries in the i18n messages files of your project --%>

<%
String applicationName = request.getContextPath().substring(1);
MetaApplication metaApplication = MetaApplications.getMetaApplication(applicationName);
%>

<h1><xava:message key="welcome_to" param="<%=metaApplication.getLabel()%>"/></h1>
<h2><%=metaApplication.getDescription()%></h2>

<table style="margin: 20px">
<tr>
	<td><img src="../naviox/images/point1.png" class="feature-image"/></td>
	<td><xava:message key="welcome_point1"/></td>
</tr>
<tr>
	<td><img src="../naviox/images/point2.png" class="feature-image"/></td>
	<td><xava:message key="welcome_point2"/></td>	
</tr>
<tr>
	<td><img src="../naviox/images/point3.png" class="feature-image"/></td>
	<td><xava:message key="welcome_point3"/></td>
</tr>
</table>

<p><xava:message key="signin_tip" param="<%=metaApplication.getLabel()%>"/></p>
