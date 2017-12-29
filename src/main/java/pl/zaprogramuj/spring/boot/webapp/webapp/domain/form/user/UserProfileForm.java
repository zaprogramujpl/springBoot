package pl.zaprogramuj.spring.boot.webapp.webapp.domain.form.user;

import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;

public class UserProfileForm {
	private String login;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String password;

	private String passwordConfirm;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public UserProfile getUser() {
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(this.firstName);
		userProfile.setLogin(this.login);
		userProfile.setPassword(this.getPassword());
		userProfile.setEmailAddress(this.emailAddress);
		return userProfile;
	}
}
