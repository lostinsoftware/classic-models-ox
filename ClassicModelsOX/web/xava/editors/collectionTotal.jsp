<%@page import="org.openxava.web.WebEditors"%>
<%@page import="org.openxava.model.meta.MetaProperty"%>
<%@page import="org.openxava.view.View"%>

<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>
<jsp:useBean id="errors" class="org.openxava.util.Messages" scope="request"/>

<%
String viewObject = request.getParameter("viewObject");
View view = (View) context.get(request, viewObject);
String collectionName = request.getParameter("collectionName");
View subview = view.getSubview(collectionName);
int row = Integer.parseInt(request.getParameter("row"));
int column = Integer.parseInt(request.getParameter("column"));
%>

<%
MetaProperty p = (MetaProperty) subview.getMetaPropertiesList().get(column);
String ftotal = WebEditors.format(request, p, subview.getCollectionTotal(row, column), errors, view.getViewName(), true);		
%> 	
<nobr> 
<%=ftotal%>&nbsp;
</nobr>	
