package org.nader.io.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<Youser> yousers;
	
	/**
	 * Default constructor
	 */
	public Role() {
	}
	
	/**
	 * @param id
	 * @param name
	 * @param yousers
	 */
	public Role(Integer id, String name, List<Youser> yousers) {
		super();
		this.id = id;
		this.name = name;
		this.yousers = yousers;
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

	public List<Youser> getYousers() {
		return yousers;
	}

	public void setYousers(List<Youser> yousers) {
		this.yousers = yousers;
	}
}
