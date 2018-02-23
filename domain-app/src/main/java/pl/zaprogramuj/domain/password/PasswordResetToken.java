package pl.zaprogramuj.domain.password;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pl.zaprogramuj.domain.user.User;

@Entity
@Table(name = "PASSWORD_RESET_TOKEN")
public class PasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String token;
	
	@OneToOne
	private User user;
	
	@Column(nullable = false)
	private Date expiryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public void setExpiryDate(int minutes){
		LocalDateTime dateTime = LocalDateTime.now();
		dateTime = dateTime.plusMinutes(minutes);
		this.expiryDate = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
    }

	@Override
	public String toString() {
		return "PasswordResetToken [id=" + id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate
				+ "]";
	}
}
