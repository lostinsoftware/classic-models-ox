if (naviox == null) var naviox = {};

naviox.init = function() {
		
	naviox.watchSearch(); 
	
	$( "#show_modules" ).click(function() {			
		if ($("#modules_list" ).css("display") == "none") {
			naviox.showModules();
		}
		else {
			naviox.hideModules();
		}
		return false;
	});
}

naviox.watchSearch = function() { 
	jQuery( "#search_modules_text" ).typeWatch({
		callback: naviox.filterModules,
	    wait:500,
	    highlight:true,
	    captureLength:0
	});
	
	$( "#search_modules_text" ).keyup(function() {
		if ($(this).val() == "") naviox.displayModulesList(); 
	});	
}

naviox.hideModules = function() { 
	$("#modules_list").fadeOut();			
	$("#show_modules").removeClass("show-modules-selected");
}

naviox.showModules = function() {  
	$("#modules_list").fadeIn();			
	$("#modules_list").show(); // For IE8
	$("#show_modules").addClass("show-modules-selected");	
}

naviox.bookmark = function() {
	var bookmark = $('#bookmark'); 
	var src = bookmark.attr('src');
	if (naviox.changeBookmark(bookmark, src, "off", "on")) {
		Modules.bookmarkCurrentModule();
	}
	else if (naviox.changeBookmark(bookmark, src, "on", "off")) {
		Modules.unbookmarkCurrentModule();
	}	
}

naviox.changeBookmark = function(bookmark, src, from, to) {
	var idx = src.indexOf("bookmark-" + from + ".png"); 
	if (idx >= 0) {
		bookmark.attr('src', src.substring(0, idx) + "bookmark-" + to + ".png");
		return true;
	}	
	return false;
}

naviox.filterModules = function() {
	Modules.filter($("#search_modules_text").val(), naviox.refreshSearchModulesList);
}

naviox.displayModulesList = function() { 
	Modules.displayModulesList(naviox.refreshModulesList);  
}

naviox.displayAllModulesList = function(searchWord) {  
	Modules.displayAllModulesList(searchWord, naviox.refreshModulesList);  
}

naviox.goFolder = function(folderOid) {
	Folders.goFolder(folderOid, naviox.refreshFolderModulesList);
}

naviox.goBack = function(folderOid) {
	Folders.goBack(naviox.refreshFolderBackModulesList);
}

naviox.refreshModulesList = function(modulesList) { 
	if (modulesList == null) {
		window.location=openxava.location="..";
		return;
	}
	$('#modules_list_core').html(modulesList);
	$('#modules_list_header').show();
	$('#modules_list_search_header').hide();
}

naviox.refreshSearchModulesList = function(modulesList) { 
	if (modulesList == null) {
		window.location=openxava.location="..";
		return;
	}
	$('#modules_list_core').html(modulesList);
	$('#modules_list_header').hide();
	$('#modules_list_search_header').show();	
}

naviox.refreshFolderModulesList = function(modulesList) {
	if (modulesList == null) {
		window.location=openxava.location=".."
		return;
	}
	$('#modules_list_content').append("<td></td>"); 
	$('#modules_list_content').children().last().html(modulesList);
	
	$('.modules-list-header').width($(window).width()); 
	
	var box = $('#modules_list_box');
    box.animate({
    		left: -box.outerWidth() / 2
    	},    	
    	function() {
    		$('#modules_list_content').children().first().remove();
    		box.css("left", "0");
    		naviox.watchSearch();
    		$('.modules-list-header').css("width", "100%"); 
    	}
    );
}

naviox.refreshFolderBackModulesList = function(modulesList) {
	if (modulesList == null) {
		window.location=openxava.location="..";
		return;
	}
	$('#modules_list_content').prepend("<td></td>"); 
	var box = $('#modules_list_box');
	box.css("left", "-" + box.outerWidth() + "px");
	$('#modules_list_content').children().first().html(modulesList);

	$('.modules-list-header').width($(window).width()); 
		
    box.animate({
    		left: 0 
    	},    	
    	function() {
    		$('#modules_list_content').children().last().remove();
    		naviox.watchSearch(); 
    		$('.modules-list-header').css("width", "100%"); 
    	}
    );    
}
