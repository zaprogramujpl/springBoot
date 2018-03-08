package pl.zaprogramuj.spring.boot.webapp.domain.form.user;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;

public class UserProfileForm {
	private String login;
	
	private String userName;

	private String emailAddress;

	private String password;

	private String passwordConfirm;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public User getUser() {
		User user = new User();
		user.setUserName(this.userName);
		user.setLogin(this.login);
		user.setPassword(this.getPassword());
		user.setEmailAddress(this.emailAddress);
		return user;
	}
}
