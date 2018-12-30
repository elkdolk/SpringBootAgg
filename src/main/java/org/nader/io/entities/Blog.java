package org.nader.io.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

@Entity
public class Blog {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 1, message = "Name should be at least one character!")
	private String name;
	
	@Size(min = 1, message = "Invalid url!")
	@URL
	@Column(length=1000)
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "youser_id")
	private Youser youser;
	
	@OneToMany(mappedBy = "blog",cascade=CascadeType.REMOVE)
	private List<Item> items;
	
	/**
	 * Default constructor
	 */
	public Blog() {
	}
	
	/**
	 * @param id
	 * @param name
	 * @param url
	 * @param youser
	 * @param items
	 */
	public Blog(Integer id, String name, String url, Youser youser, List<Item> items) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.youser = youser;
		this.items = items;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Youser getYouser() {
		return youser;
	}

	public void setYouser(Youser youser) {
		this.youser = youser;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
