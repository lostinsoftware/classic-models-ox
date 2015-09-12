<%@ include file="../imports.jsp"%>

<%@ page import="org.openxava.model.meta.MetaProperty" %>
<%@ page import="org.openxava.actions.OnChangeChartLabelColumnAction" %>
<%@ page import="org.openxava.util.Is" %>
<%@ page import="org.openxava.util.Labels" %>
<%@ page import="org.openxava.web.Charts"%>

<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>

<%
String viewObject = request.getParameter("viewObject");

viewObject = (viewObject == null || viewObject.equals(""))?"xava_view":viewObject;
org.openxava.view.View view = (org.openxava.view.View) context.get(request, viewObject);

String tabObject = request.getParameter("tabObject");
tabObject = (tabObject == null || tabObject.equals(""))?"xava_tab":tabObject;
org.openxava.tab.Tab tab = (org.openxava.tab.Tab) context.get(request, tabObject);

String chartObject = request.getParameter("chartObject");
chartObject = (chartObject == null || chartObject.equals(""))?"xava_chart":chartObject;
org.openxava.session.Chart chart = (org.openxava.session.Chart) context.get(request, chartObject);

String propertyKey = request.getParameter("propertyKey");
String script = request.getParameter("script");
MetaProperty p = (MetaProperty) request.getAttribute(propertyKey);
String title = (p == null)?"":p.getDescription(request);
String fvalue = (String) request.getAttribute(propertyKey + ".fvalue");
String value = (String) request.getAttribute(propertyKey + ".value");
boolean label = org.openxava.util.XavaPreferences.getInstance().isReadOnlyAsLabel() || "true".equalsIgnoreCase(request.getParameter("readOnlyAsLabel"));

boolean showAllColumns = Boolean.TRUE.equals((Boolean) view.getRoot().getObject("xava.chartColumnShowAllColumns"));
boolean showOnlyNumericColumns = "true".equalsIgnoreCase(request.getParameter("showOnlyNumericColumns"));

java.util.List<String> selectedColumns = new java.util.ArrayList<String>();
if (!Is.emptyString(value)) {
	selectedColumns.add(value);
}
java.util.Collection columns = showAllColumns?
		tab.getMetaTab().getMetaModel().getRecursiveQualifiedPropertiesNames():
		tab.getMetaTab().getMetaModel().getRecursiveQualifiedPropertiesNamesUntilSecondLevel();

boolean editable = columns.size() > 1;
if (editable) {
%>
<select id="<%=propertyKey%>" name="<%=propertyKey%>" tabindex="1" class=<%=style.getEditor()%> <%=script%> title="<%=title%>">	
<%
		
	%>
	<option value="" >&nbsp;</option>
	<%
		// current user
		for (java.util.Iterator it = columns.iterator(); it.hasNext(); ) {
		String column = (String) it.next();
		String selected = "";
		MetaProperty property = tab.getMetaTab().getMetaModel().getMetaProperty(column);
		if (showOnlyNumericColumns && !property.isNumber()) {
			continue;
		}
		if (column.equals(value)) {
			selected = "selected"; 
		}
	%>
		<option value="<%=column%>" <%=selected%>><%=property.getLabel()%></option>
	<%
		}
		if (!showAllColumns) {
	%>
	<option value="<%=OnChangeChartLabelColumnAction.SHOW_MORE%>"><xava:message key="my_report_show_more_columns"/></option>
	<%
		} else {
	%>
	<option value="<%=OnChangeChartLabelColumnAction.SHOW_LESS%>"><xava:message key="my_chart_show_less_columns"/></option>
	<% }
	
%>
</select>
<input type="hidden" name="<%=propertyKey%>__DESCRIPTION__" value="<%=fvalue%>">
<% 
} else {
%>	
<jsp:include page="textEditor.jsp">
	<jsp:param name="script" value=""/>
	<jsp:param name="editable" value="false" />
</jsp:include> 
<% 
} 
%>			
