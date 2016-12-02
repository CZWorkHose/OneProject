package com.ssh.sakila.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "category", catalog = "sakila")
public class Category implements java.io.Serializable {

	// Fields

	private Short categoryId;
	private String name;
	private Timestamp lastUpdate;
	private Set<FilmCategory> filmCategories = new HashSet<FilmCategory>(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(String name, Timestamp lastUpdate) {
		this.name = name;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public Category(String name, Timestamp lastUpdate,
			Set<FilmCategory> filmCategories) {
		this.name = name;
		this.lastUpdate = lastUpdate;
		this.filmCategories = filmCategories;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "category_id", unique = true, nullable = false)
	public Short getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Short categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "name", nullable = false, length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "last_update", nullable = false, length = 19)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	public Set<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(Set<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

}