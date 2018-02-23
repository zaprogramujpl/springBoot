package pl.zaprogramuj.domain.form.user;

import pl.zaprogramuj.domain.user.User;

public class UserProfileForm
{
	private String login;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String password;

	private String passwordConfirm;

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPasswordConfirm()
	{
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm)
	{
		this.passwordConfirm = passwordConfirm;
	}

	public User getUser()
	{
		User user = new User();
		user.setFirstName(this.firstName);
		user.setLogin(this.login);
		user.setPassword(this.getPassword());
		user.setEmailAddress(this.emailAddress);
		return user;
	}
}