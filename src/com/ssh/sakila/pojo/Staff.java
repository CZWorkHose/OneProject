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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "staff", catalog = "sakila")
public class Staff implements java.io.Serializable {

	// Fields

	private Short staffId;
	private Address address;
	private Store store;
	private String firstName;
	private String lastName;
	private String picture;
	private String email;
	private Boolean active;
	private String username;
	private String password;
	private Timestamp lastUpdate;
	private Set<Rental> rentals = new HashSet<Rental>(0);
	private Set<Payment> payments = new HashSet<Payment>(0);
	private Set<Store> stores = new HashSet<Store>(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(Address address, Store store, String firstName,
			String lastName, Boolean active, String username,
			Timestamp lastUpdate) {
		this.address = address;
		this.store = store;
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
		this.username = username;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public Staff(Address address, Store store, String firstName,
			String lastName, String picture, String email, Boolean active,
			String username, String password, Timestamp lastUpdate,
			Set<Rental> rentals, Set<Payment> payments, Set<Store> stores) {
		this.address = address;
		this.store = store;
		this.firstName = firstName;
		this.lastName = lastName;
		this.picture = picture;
		this.email = email;
		this.active = active;
		this.username = username;
		this.password = password;
		this.lastUpdate = lastUpdate;
		this.rentals = rentals;
		this.payments = payments;
		this.stores = stores;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "staff_id", unique = true, nullable = false)
	public Short getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Short staffId) {
		this.staffId = staffId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "picture")
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "active", nullable = false)
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "username", nullable = false, length = 16)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "last_update", nullable = false, length = 19)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staff")
	public Set<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(Set<Rental> rentals) {
		this.rentals = rentals;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staff")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staff")
	public Set<Store> getStores() {
		return this.stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}

}