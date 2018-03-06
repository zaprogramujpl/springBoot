package pl.zaprogramuj.spring.boot.webapp.domain.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pl.zaprogramuj.spring.boot.webapp.domain.authority.Authority;
import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;

@Entity
@Table(name = "USER_PROFILE")
public class User implements UserDetails
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "LOGIN", unique = true)
	private String login;
	private String userName;
	private String password;

	@Column(name = "EMAIL", nullable = false)
	private String emailAddress;
	private String phone;
	private boolean enabled = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_PROFILE_ROLE", joinColumns = { @JoinColumn(name = "ID_USER") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_USER_ROLE") })
	private Set<UserRole> roles = new HashSet<>();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private PasswordResetToken passwordResetToken;
	
	@OneToMany(mappedBy = "author")
	private List<Post> posts;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}


	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		Set<GrantedAuthority> authorities = new HashSet<>();
		roles.forEach(userRole -> authorities.add(new Authority("ROLE_" + userRole.getRole())));
		return authorities;
	}

	public Set<UserRole> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<UserRole> roles)
	{
		this.roles = roles;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return enabled;
	}

	public PasswordResetToken getPasswordResetToken()
	{
		return passwordResetToken;
	}

	public void setPasswordResetToken(PasswordResetToken passwordResetToken)
	{
		this.passwordResetToken = passwordResetToken;
	}

	public List<Post> getPosts()
	{
		if(posts == null)
			posts = new ArrayList<>();
		return posts;
	}

	public void setPosts(List<Post> posts)
	{
		this.posts = posts;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", login=" + login + ", userName=" + userName + ", password=" + "XXXXXX"
				+ ", emailAddress=" + emailAddress + ", phone=" + phone + ", enabled=" + enabled + ", roles=" + roles
				+ ", passwordResetToken=" + passwordResetToken + ", posts=" + posts + "]";
	}

	@Override
	public String getUsername()
	{
		return getUserName();
	}
}