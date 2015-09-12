if (chartDataEditor == null) var chartDataEditor = {};

openxava.addEditorInitFunction(function() { 
	if ($('#xava_application').length &&
			$('#xava_module').length) {
		if (navigator.userAgent.indexOf("HtmlUnit") >= 0) return; 
		var applicationName = $('#xava_application').val();
		var moduleName = $('#xava_module').val();
		var xavaChartPrefix = openxava.decorateId(applicationName, moduleName, "xava_chart__");
		var idPrefix = "#" + xavaChartPrefix;
		if ($(idPrefix + 'type').length) {
			var chartType = $(idPrefix + 'type').val();
			var grouped = $(idPrefix + 'grouped').val();
			var specification = chartDataEditor.render(applicationName, moduleName, chartType, grouped, xavaChartPrefix);
			if (specification != 'empty') {
				c3.generate(specification);
			}
		}
	}
});

chartDataEditor.render = function(application, module, chartType, grouped, xavaChartPrefix) { 
	var idPrefix = "#" + xavaChartPrefix;
	var rowCount = $(idPrefix + "rowCount").val();
	var columnCount = $(idPrefix + "columnCount").val();
	var specification='empty';
	if (rowCount > 0 && columnCount > 0) {
		specification = {
				bindto:idPrefix + "container",
				data:{
					x:'x',
					columns:[],
					type:chartType},
				axis: {
					x: {
						type: 'categories'
					}
				}
		};
		var index = 0;
		specification.data.columns[0]=['x'];
		for (index = 0; index < columnCount; index++) {
			var category = $(idPrefix + "dataset_" + index + "_title").val();
			specification.data.columns[index + 1]=[];
			var rowIndex = 0;
			for (rowIndex = 0; rowIndex < rowCount; rowIndex++) {
				var label = $(idPrefix + "title_" + rowIndex).val();
				if (index == 0) {
					specification.data.columns[0][rowIndex + 1] = label;
				}
				if (rowIndex == 0) {
					specification.data.columns[index + 1][0] = category;
				}
				var value = $(idPrefix + "dataset_" + index + "_value_" + rowIndex).val();
				specification.data.columns[index + 1][rowIndex + 1]=new Number(value);
			}
		}
		if ("true" == grouped) {
			index = 1;
			specification.data["groups"] = [];
			specification.data.groups[0] = [];
			for (index = 1; index < specification.data.columns.length; index++) {
				specification.data.groups[0][index - 1] = specification.data.columns[index][0];
			}
		}
	}
	return specification;
}

