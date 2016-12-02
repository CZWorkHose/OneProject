package com.ssh.sakila.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Film entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "film", catalog = "sakila")
public class Film implements java.io.Serializable {

	// Fields

	private Short filmId;
	private Language languageByOriginalLanguageId;
	private Language languageByLanguageId;
	private String title;
	private String description;
	private Date releaseYear;
	private Short rentalDuration;
	private Double rentalRate;
	private Short length;
	private Double replacementCost;
	private String rating;
	private String specialFeatures;
	private Timestamp lastUpdate;
	private Set<FilmActor> filmActors = new HashSet<FilmActor>(0);
	private Set<FilmCategory> filmCategories = new HashSet<FilmCategory>(0);
	private Set<Inventory> inventories = new HashSet<Inventory>(0);

	// Constructors

	/** default constructor */
	public Film() {
	}

	/** minimal constructor */
	public Film(Language languageByLanguageId, String title,
			Short rentalDuration, Double rentalRate, Double replacementCost,
			Timestamp lastUpdate) {
		this.languageByLanguageId = languageByLanguageId;
		this.title = title;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public Film(Language languageByOriginalLanguageId,
			Language languageByLanguageId, String title, String description,
			Date releaseYear, Short rentalDuration, Double rentalRate,
			Short length, Double replacementCost, String rating,
			String specialFeatures, Timestamp lastUpdate,
			Set<FilmActor> filmActors, Set<FilmCategory> filmCategories,
			Set<Inventory> inventories) {
		this.languageByOriginalLanguageId = languageByOriginalLanguageId;
		this.languageByLanguageId = languageByLanguageId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.lastUpdate = lastUpdate;
		this.filmActors = filmActors;
		this.filmCategories = filmCategories;
		this.inventories = inventories;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "film_id", unique = true, nullable = false)
	public Short getFilmId() {
		return this.filmId;
	}

	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "original_language_id")
	public Language getLanguageByOriginalLanguageId() {
		return this.languageByOriginalLanguageId;
	}

	public void setLanguageByOriginalLanguageId(
			Language languageByOriginalLanguageId) {
		this.languageByOriginalLanguageId = languageByOriginalLanguageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id", nullable = false)
	public Language getLanguageByLanguageId() {
		return this.languageByLanguageId;
	}

	public void setLanguageByLanguageId(Language languageByLanguageId) {
		this.languageByLanguageId = languageByLanguageId;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "release_year", length = 0)
	public Date getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}

	@Column(name = "rental_duration", nullable = false)
	public Short getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(Short rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	@Column(name = "rental_rate", nullable = false, precision = 4)
	public Double getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	@Column(name = "length")
	public Short getLength() {
		return this.length;
	}

	public void setLength(Short length) {
		this.length = length;
	}

	@Column(name = "replacement_cost", nullable = false, precision = 5)
	public Double getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	@Column(name = "rating", length = 5)
	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Column(name = "special_features", length = 54)
	public String getSpecialFeatures() {
		return this.specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	@Column(name = "last_update", nullable = false, length = 19)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "film")
	public Set<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(Set<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "film")
	public Set<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(Set<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "film")
	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

}