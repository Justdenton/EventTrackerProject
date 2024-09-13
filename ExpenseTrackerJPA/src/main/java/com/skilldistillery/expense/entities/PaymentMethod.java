package com.skilldistillery.expense.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "method_name")
	private String name;

//	@CreationTimestamp
//	@Column(name = "create_time")
//	private LocalDateTime createTime;

	@JsonIgnore
	@OneToMany(mappedBy = "paymentMethod")
	private List<Expense> expenses;

	@Column(name = "enabled")
	private Boolean enabled = true;

	public PaymentMethod() {
		super();
	}

	public PaymentMethod(int id, String name, List<Expense> expenses, Boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.expenses = expenses;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public LocalDateTime getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(LocalDateTime createTime) {
//		this.createTime = createTime;
//	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public Boolean getEnabled() {
		return enabled;
	} 

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
		PaymentMethod other = (PaymentMethod) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentMethod [id=").append(id).append(", name=").append(name).append(", enabled=")
				.append(enabled).append("]");
		return builder.toString();
	}

}