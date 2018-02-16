function addEditorContentToPostContent(element) {
	console.log(content);
}

window.onload = function() {
	tinymce.init({
		  selector: '#tiny',
		  height: 500,
		  menubar: false,
		  plugins: [
		    'advlist autolink lists link image charmap print preview anchor textcolor',
		    'searchreplace visualblocks code fullscreen',
		    'insertdatetime media table contextmenu paste code wordcount'
		  ],
		  toolbar: 'insert | undo redo |  formatselect | bold italic backcolor  | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
		  content_css: [
		    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
		    '//www.tinymce.com/css/codepen.min.css'],
		  setup: function (editor) {
		        editor.on('keyup change', function () {
		             console.log(editor.getContent());
		        });
		    } 
		});	
};
