<%@include file="../xava/imports.jsp"%>

<%-- To put your own text add entries in the i18n messages files of your project --%>

<%
String language = "es".equals(request.getLocale().getLanguage()) || "ca".equals(request.getLocale().getLanguage())?"es":"en";
%>

<div id="first_steps">
<p><xava:message key="first_steps_p1"/></p>
<p class="screenshot"><img src="../naviox/images/modules-screenshot_<%=language%>.png"/></p>
<p><xava:message key="first_steps_p2"/></p>
<p class="screenshot"><img src="../naviox/images/list-mode-screenshot_<%=language%>.png"/></p>
<p><xava:message key="first_steps_p3"/></p>
</div>