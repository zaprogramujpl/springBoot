package pl.zaprogramuj.spring.boot.webapp.domain.form.post;

public class CommentForm {

	private long id;

	private long postId;
	
	private long parentId;

	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "CommentForm [id=" + id + ", postId=" + postId + ", parentId=" + parentId + ", content=" + content + "]";
	}
}
