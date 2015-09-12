<%@page import="org.openxava.util.XavaPreferences"%>

<%if (view.isEditable() || 
		!(!view.isEditable() && !XavaPreferences.getInstance().isShowIconForViewReadOnly())
	) { %>
	<% if (ref.isKey()) { %>
	<img src="<%=request.getContextPath()%>/xava/images/key.gif"/>
	<% } else if (ref.isRequired()) {  %>	
	<img src="<%=request.getContextPath()%>/xava/images/required.gif"/>
	<% } %> 
<%} %>

<span id="<xava:id name='<%="error_image_" + ref.getQualifiedName()%>'/>">
<% if ( errors.memberHas(ref)) {%>
<img src="<%=request.getContextPath()%>/xava/images/error.gif"/>
<% } %>
</span>