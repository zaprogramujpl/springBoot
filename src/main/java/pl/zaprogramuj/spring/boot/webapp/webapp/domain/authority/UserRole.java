package pl.zaprogramuj.spring.boot.webapp.webapp.domain.authority;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;

@Entity
@Table(name = "USER_ROLE")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userRoleId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USER")
	private UserProfile user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ROLE")
	private Role role;

	public UserRole(UserProfile user, Role role) {
		this.user = user;
		this.role = role;
	}

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
