package pl.zaprogramuj.spring.boot.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.zaprogramuj.spring.boot.webapp.component.CommentComponent;
import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.controller.admin.AdminHomeController;
import pl.zaprogramuj.spring.boot.webapp.domain.page.PageCharacteristics;
import pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics.PageCharacteristicsNotExistsException;
import pl.zaprogramuj.spring.boot.webapp.service.CommentService;
import pl.zaprogramuj.spring.boot.webapp.service.EmailService;
import pl.zaprogramuj.spring.boot.webapp.service.PageCharacteristicsService;
import pl.zaprogramuj.spring.boot.webapp.service.PasswordResetTokenService;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;
import pl.zaprogramuj.spring.boot.webapp.service.SystemPropertiesService;
import pl.zaprogramuj.spring.boot.webapp.service.UserRoleService;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;

public abstract class AbstractController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SystemPropertiesService systemProperties;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PostService postService;

	@Autowired
	private PageCharacteristicsService pageCharacteristics;

	@Autowired
	private CommentService commentService;

	@Autowired
	private LoggedUserInformationComponent loggedUserInformationComponent;

	@Autowired
	private CommentComponent commentComponent;

	@Autowired
	@Qualifier("userFormValidator")
	private Validator userFormValidator;

	@Autowired
	@Qualifier("forgotPasswordFormValidator")
	private Validator forgotPasswordFormValidator;

	@ModelAttribute("systemVersion")
	public String getSystemVersion() {
		return systemProperties.getSystemVersion();
	}

	@ModelAttribute("updatePageCss")
	public String getUpdatePageCssURL() {
		return AdminHomeController.BASIC_MAPPING + AdminHomeController.UPDATE_PAGE_CHARACTERISTICS_MAPPING;
	}

	@ModelAttribute("pageCharacteristics")
	public PageCharacteristics getPageCSS(HttpServletRequest request) throws PageCharacteristicsNotExistsException {
		PageCharacteristics pageCharacteristics = null;

		if (getPageCharacteristics().isPageCharacteristicsWithUriAddress(request.getRequestURI())) {
			pageCharacteristics = getPageCharacteristics().getPageCharacteristicsByUriAddress(request.getRequestURI());
		} else {
			pageCharacteristics = new PageCharacteristics();
			pageCharacteristics.setUriAddress(request.getRequestURI());
		}

		return pageCharacteristics;
	}

	public UserService getUserService() {
		return userService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public PostService getPostService() {
		return postService;
	}

	public PageCharacteristicsService getPageCharacteristics() {
		return pageCharacteristics;
	}

	public Validator getUserFormValidator() {
		return userFormValidator;
	}

	public Validator getForgotPasswordFormValidator() {
		return forgotPasswordFormValidator;
	}

	public PasswordResetTokenService getPasswordResetTokenService() {
		return passwordResetTokenService;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public LoggedUserInformationComponent getLoggedUserInformationComponent() {
		return loggedUserInformationComponent;
	}

	public CommentComponent getCommentComponent() {
		return commentComponent;
	}

	public CommentService getCommentService() {
		return commentService;
	}
}