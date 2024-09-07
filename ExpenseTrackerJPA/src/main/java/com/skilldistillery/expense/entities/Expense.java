package com.skilldistillery.expense.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double amount;
	private String description;

	@CreationTimestamp
	@Column(name = "create_time")
	private LocalDateTime createTime;

	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	// ( each expense - related to 1 user )
	@JsonIgnoreProperties({"expenses"})
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnoreProperties({"expenses"})
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@JsonIgnoreProperties({"expenses"})
	@ManyToOne
	@JoinColumn(name = "payment_method_id")
	private PaymentMethod paymentMethod;

	public Expense() {
		super(); 
	}

	public Expense(int id, double amount, String description, LocalDateTime createTime, LocalDateTime updateTime,
			User user, Category category, PaymentMethod paymentMethod) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.user = user;
		this.category = category;
		this.paymentMethod = paymentMethod;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expense [id=").append(id).append(", amount=").append(amount).append(", description=")
				.append(description).append(", createTime=").append(createTime).append(", updateTime=")
				.append(updateTime).append("]");
		return builder.toString();
	}

}
