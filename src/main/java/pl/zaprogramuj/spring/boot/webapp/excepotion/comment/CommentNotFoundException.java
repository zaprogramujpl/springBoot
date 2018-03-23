package pl.zaprogramuj.spring.boot.webapp.excepotion.comment;

public class CommentNotFoundException extends CommentException {
	private static final long serialVersionUID = -6956621688217703359L;

	private static final String NOT_FOUND_COMMENT_WITH_ID = "Not found COMMENT with id: ";

	public CommentNotFoundException(String message) {
		super(message);
	}

	public CommentNotFoundException(long id) {
		super(NOT_FOUND_COMMENT_WITH_ID + id);
	}
}
