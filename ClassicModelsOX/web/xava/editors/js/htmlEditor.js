openxava.addEditorInitFunction(function() {
	var config = { language: openxava.language };
	$('.ox-ckeditor').ckeditor(config);
});


openxava.addEditorDestroyFunction(function() {
	for (var instance in CKEDITOR.instances) {
		CKEDITOR.instances[instance].destroy(false); // Needs to be false, otherwise calculated properties in the same view reset editor content
	}
});





