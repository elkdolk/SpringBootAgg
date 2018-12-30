package org.nader.io.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length=1000)
	private String title;
	
	@Lob
	@Column(length=Integer.MAX_VALUE)
	private String description;
	
	private Date publishedDate;
	
	@Column(length=1000)
	private String link;
	
	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;
	
	/**
	 * Default constructor
	 */
	public Item() {
	}
		
	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param publishedDate
	 * @param link
	 * @param blog
	 */
	public Item(Integer id, String title, String description, Date publishedDate, String link, Blog blog) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.publishedDate = publishedDate;
		this.link = link;
		this.blog = blog;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
}
