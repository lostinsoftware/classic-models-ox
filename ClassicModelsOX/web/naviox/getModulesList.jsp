<% 
if ("true".equals(request.getParameter("bookmarkModules"))) {
	modulesList = modules.getBookmarkModules();
	bookmarkModules = true;
}
else {
	modulesList = modules.getAll();
}
%>