$(document).on("click", ".addCommentModal", function() {
	var id = $(this).data('id');
	var parentId = $(this).data('parent-id');
	$(".modal-body #postId").val(id);
	console.log(parentId);
	$(".modal-body #parentId").val(parentId);
});