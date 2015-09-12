<%@ include file="../imports.jsp"%>

<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Locale" %> <%-- Trifon --%>
<%@ page import="org.openxava.util.Locales" %> <%-- Trifon --%>
<%@ page import="org.openxava.util.Labels" %> <%-- Trifon --%>



<%@page import="org.openxava.web.Ids"%><jsp:useBean id="context" class="org.openxava.controller.ModuleContext"
scope="session"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>

<%
String tabObject = request.getParameter("tabObject");
tabObject = (tabObject == null || tabObject.equals(""))?"xava_tab":tabObject;
org.openxava.tab.Tab tab = (org.openxava.tab.Tab) context.get(request, "xava_customizingTab");

%>
<div id="xava_add_columns">
<table id="<xava:id name='xavaPropertiesList'/>" class='<%=style.getList()%>' width="100%" <%=style.getListCellSpacing()%> style="margin-bottom: 5px; <%=style.getListStyle()%>">
<tr class="<%=style.getListPair()%>" style="border-bottom: 1px solid; height: 0px;"/> 
<%
int f=0;
Locale currentLocale = Locales.getCurrent(); //Trifon
for (Iterator it=tab.getColumnsToAdd().iterator(); it.hasNext();) { 
	String property = (String) it.next();
	String cssClass=f%2==0?style.getListPair():style.getListOdd();	
	String cssCellClass=f%2==0?style.getListPairCell():style.getListOddCell();
	String events=f%2==0?style.getListPairEvents():style.getListOddEvents();
	String rowId = Ids.decorate(request, "xavaPropertiesList") + f;
	String actionOnClick = org.openxava.web.Actions.getActionOnClick(
		request.getParameter("application"), request.getParameter("module"), 
		null, f, null, rowId,
		"", "", 
		null, tabObject);	
	f++;
	String propertyI18n = Labels.getQualified(property, currentLocale); 
%>
<tr id="<%=rowId%>" class="<%=cssClass%>" <%=events%> style="border-bottom: 1px solid;">
	<td class="<%=cssCellClass%>" style="<%=style.getListCellStyle()%>" width="5">
		<xava:action action='AddColumns.addColumn' argv='<%="property=" + property%>'/>
	</td>		
	<td class="<%=cssCellClass%>" style="<%=style.getListCellStyle()%>" width="5">
		<INPUT type="CHECKBOX" name="<xava:id name='xava_selected'/>" value="selectedProperties:<%=property%>" <%=actionOnClick%>/>
	</td>
	<td class="<%=cssCellClass%>" style="<%=style.getListCellStyle()%>"> 
		<xava:link action='AddColumns.addColumn' argv='<%="property=" + property%>' cssStyle="text-decoration: none; outline: none;">
		<div><%=propertyI18n%></div>
		</xava:link>
	</td>
</tr>
<%
}
%>

<%
if (tab.isColumnsToAddUntilSecondLevel()) {
%>
<tr class="<%=style.getListPair()%>">
<td/>
<td/>
<td>
<xava:link action="AddColumns.showMoreColumns" cssClass="<%=style.getActionLink()%>" cssStyle="margin-left: 0px;" /> 
</td>
</tr>
<%
}
%>

</table>
</div>