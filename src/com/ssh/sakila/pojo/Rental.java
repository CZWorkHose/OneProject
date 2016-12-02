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
import javax.persistence.UniqueConstraint;

/**
 * Rental entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rental", catalog = "sakila", uniqueConstraints = @UniqueConstraint(columnNames = {
		"rental_date", "inventory_id", "customer_id" }))
public class Rental implements java.io.Serializable {

	// Fields

	private Integer rentalId;
	private Staff staff;
	private Customer customer;
	private Inventory inventory;
	private Timestamp rentalDate;
	private Timestamp returnDate;
	private Timestamp lastUpdate;
	private Set<Payment> payments = new HashSet<Payment>(0);

	// Constructors

	/** default constructor */
	public Rental() {
	}

	/** minimal constructor */
	public Rental(Staff staff, Customer customer, Inventory inventory,
			Timestamp rentalDate, Timestamp lastUpdate) {
		this.staff = staff;
		this.customer = customer;
		this.inventory = inventory;
		this.rentalDate = rentalDate;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public Rental(Staff staff, Customer customer, Inventory inventory,
			Timestamp rentalDate, Timestamp returnDate, Timestamp lastUpdate,
			Set<Payment> payments) {
		this.staff = staff;
		this.customer = customer;
		this.inventory = inventory;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.lastUpdate = lastUpdate;
		this.payments = payments;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rental_id", unique = true, nullable = false)
	public Integer getRentalId() {
		return this.rentalId;
	}

	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id", nullable = false)
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inventory_id", nullable = false)
	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Column(name = "rental_date", nullable = false, length = 19)
	public Timestamp getRentalDate() {
		return this.rentalDate;
	}

	public void setRentalDate(Timestamp rentalDate) {
		this.rentalDate = rentalDate;
	}

	@Column(name = "return_date", length = 19)
	public Timestamp getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	@Column(name = "last_update", nullable = false, length = 19)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rental")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

}