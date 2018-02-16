function dynamicCssChanging(element) {
	document.getElementById('pageStyle').innerHTML = element.getValue();
}

window.onload = function() {
	var editor = CodeMirror.fromTextArea(document.getElementById("codeCss"), {
		lineNumbers : true,
		styleActiveLine : true,
		matchBrackets : true,
		autoRefresh:true,
		mode : "css"
	});
	
	dynamicCssChanging(editor);

	editor.on("change", function(element) {
		dynamicCssChanging(element);
	});

	setTimeout(function() {
		editor.refresh();
	},1);
};
