package org.nader.io.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.nader.io.annotation.UniqueUsername;
/**
 * because user is a reserved word in some databases I will use Youser instead ! 
 */
@Entity
public class Youser {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 3,message = "Name must be at least 3 characters!")
	@Column(unique= true)
	@UniqueUsername(message ="User name already taken ...!")
	private String name;
	
	@Email
	@Size(min = 1,message = "Invalid email!")
	private String email;
	
	@Size(min = 5,message = "Password must be at least 5 characters!")
	private String password;
	
	private boolean enabled;
	
	@ManyToMany
	@JoinTable
	private List<Role> roles;
	
	@OneToMany(mappedBy = "youser", cascade = CascadeType.REMOVE)
	private List<Blog> blogs;
	
	/**
	 * Default constructor 
	 */
	public Youser() {
	}

	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param roles
	 * @param blogs
	 */
	public Youser(Integer id, String name, String email, String password, List<Role> roles, List<Blog> blogs) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.blogs = blogs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
}
