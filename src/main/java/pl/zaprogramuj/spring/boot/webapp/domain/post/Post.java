package pl.zaprogramuj.spring.boot.webapp.domain.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;

@Entity
@Table(name = "POST")
public class Post
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	private String title;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CATAGORIES_ASSIGNED_TO_POST", joinColumns = {
			@JoinColumn(name = "ID_POST") }, inverseJoinColumns = { @JoinColumn(name = "ID_CATEGORY") })
	private List<PostCategory> categories = new ArrayList<>();

	private String description;

	private String content;

	@Column(unique = true)
	private String urlAddress;


	private LocalDateTime creationDate;

	@ManyToOne
	@JoinColumn(name = "id_author")
	private User author;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public List<PostCategory> getCategories()
	{
		return categories;
	}

	public void setCategories(List<PostCategory> categories)
	{
		this.categories = categories;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getUrlAddress()
	{
		return urlAddress;
	}

	public void setUrlAddress(String urlAddress)
	{
		this.urlAddress = urlAddress;
	}

	public LocalDateTime getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate)
	{
		this.creationDate = creationDate;
	}

	public User getAuthor()
	{
		return author;
	}

	public void setAuthor(User author)
	{
		this.author = author;
	}

	public List<Comment> getComments()
	{
		if(comments == null)
			comments = new ArrayList<>();
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}

	@Override
	public String toString()
	{
		return "Post [id=" + id + ", title=" + title + ", categories=" + categories + ", description=" + description
				+ ", content=" + content + ", urlAddress=" + urlAddress + ", additionalCSS= X" + ", creationDate="
				+ creationDate + ", author=" + author + "]";
	}

}
