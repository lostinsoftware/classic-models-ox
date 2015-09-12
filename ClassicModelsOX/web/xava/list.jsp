<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>

<%@ page import="org.openxava.web.WebEditors" %>

<%
String tabObject = request.getParameter("tabObject");
tabObject = (tabObject == null || tabObject.equals(""))?"xava_tab":tabObject;
org.openxava.tab.Tab tab = (org.openxava.tab.Tab) context.get(request, tabObject);
%>
<jsp:include page='<%=WebEditors.getUrl(tab.getMetaTab())%>'/>

