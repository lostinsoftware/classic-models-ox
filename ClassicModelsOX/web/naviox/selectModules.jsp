<%@include file="../xava/imports.jsp"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="org.openxava.util.Is"%>
<%@page import="org.openxava.application.meta.MetaModule"%>

<jsp:useBean id="modules" class="com.openxava.naviox.Modules" scope="session"/>

<%
String searchWord = request.getParameter("searchWord");
searchWord = searchWord == null?"":searchWord.toLowerCase(); 
Collection modulesList = null;
boolean bookmarkModules = false;
%>
<%@ include file="getModulesList.jsp" %> 
<%
String smodulesLimit = request.getParameter("modulesLimit");
int modulesLimit = smodulesLimit == null?15:Integer.parseInt(smodulesLimit);
int counter = 0; 
boolean loadMore = false;
for (Iterator it= modulesList.iterator(); it.hasNext();) {
	if (counter == modulesLimit) {
		loadMore = true; 
		break;
	}
	MetaModule module = (MetaModule) it.next();
	String selected = module.getName().equals(modules.getCurrent())?"selected":""; 
	String label = module.getLabel(request.getLocale()); 
	String description = module.getDescription(request.getLocale());
		
	if (!Is.emptyString(searchWord) && !label.toLowerCase().contains(searchWord) && !description.toLowerCase().toLowerCase().contains(searchWord)) continue;  
	counter++;
%>
	<a  href="<%=modules.getModuleURI(request, module)%>">
	<div id="<%=module.getName()%>_module" class="module-row <%=selected%>" onclick="$('#<%=module.getName()%>_loading').show()">	
		<div class="module-name">
			<%=label%>
			<% if (bookmarkModules) { %>
			<img src="<%=request.getContextPath()%>/naviox/images/bookmark-on.png" class="bookmark-decoration"/>
			<% } %>
			<img id="<%=module.getName()%>_loading" src="<%=request.getContextPath()%>/naviox/images/loading.gif" style="float: right; display:none;"/>
		</div>
		<div class="module-description"><%=description%></div>
	</div>	
	</a>
	
<%	
}

if (loadMore) {
%>
	<a  href="javascript:naviox.displayAllModulesList('<%=searchWord%>')">
	<div id="more_modules" class="module-row" onclick="$('#loading_more_modules').show(); $('#load_more_modules').hide();">
	<span id="loading_more_modules" style="display:none;">
	<xava:message key="loading"/>...
	<img src="<%=request.getContextPath()%>/naviox/images/loading.gif" style="float: right;"/>
	</span>
	<span id="load_more_modules">
	<xava:message key="load_more_modules"/>...
	</span>	
	</div>	
	</a>
<%
}
%>