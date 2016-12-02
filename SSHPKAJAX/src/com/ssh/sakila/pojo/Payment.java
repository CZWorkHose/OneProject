package com.ssh.sakila.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Payment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "payment", catalog = "sakila")
public class Payment implements java.io.Serializable {

	// Fields

	private Short paymentId;
	private Staff staff;
	private Customer customer;
	private Rental rental;
	private Double amount;
	private Timestamp paymentDate;
	private Timestamp lastUpdate;

	// Constructors

	/** default constructor */
	public Payment() {
	}

	/** minimal constructor */
	public Payment(Staff staff, Customer customer, Double amount,
			Timestamp paymentDate, Timestamp lastUpdate) {
		this.staff = staff;
		this.customer = customer;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public Payment(Staff staff, Customer customer, Rental rental,
			Double amount, Timestamp paymentDate, Timestamp lastUpdate) {
		this.staff = staff;
		this.customer = customer;
		this.rental = rental;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.lastUpdate = lastUpdate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "payment_id", unique = true, nullable = false)
	public Short getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Short paymentId) {
		this.paymentId = paymentId;
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
	@JoinColumn(name = "rental_id")
	public Rental getRental() {
		return this.rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	@Column(name = "amount", nullable = false, precision = 5)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "payment_date", nullable = false, length = 19)
	public Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name = "last_update", nullable = false, length = 19)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}