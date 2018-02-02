package pl.zaprogramuj.spring.boot.webapp.util;

public final class SystemViewsName {
	public static final String INDEX = "page/index";
	public static final String REGISTER_USER = "page/registerUserPage/registerUserPage";
	public static final String LOGIN_PAGE = "page/loginPage/loginPage";
	public static final String USER_PROFILE = "page/userProfilePage/userProfilePage";

	public static final String ERROR_403 = "page/error/403";
	public static final String REDIRECT_TO_MAIN_PAGE = "redirect:/";
	public static final String PASSWORD_FORGOT = "page/forgotPasswordPage/forgotPasswordPage";
	public static final String PASSWORD_RESET = "page/resetPasswordPage/resetPasswordPage";
	
	public static final String REDIRECT_TO_FORGOT_PASSWORD_PAGE = "redirect:/forgot-password";
	public static final String REDIRECT_AUTHENTICATION = "http://localhost/login";
	
	private SystemViewsName() {

	}
	
}