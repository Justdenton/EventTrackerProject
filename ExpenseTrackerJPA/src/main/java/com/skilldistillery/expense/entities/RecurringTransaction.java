package com.skilldistillery.expense.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recurring_transaction")
public class RecurringTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@CreationTimestamp
	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	// EX: Weekly, Bi-weekly, Monthly, etc.
	@Column(name = "recur_period")
	private String recurPeriod;

	@Column(name = "next_recur_date")
	private LocalDateTime nextRecurDate;

	@Column(name = "enabled")
	private boolean enabled = true;

	@JsonIgnore
	@OneToOne(mappedBy = "recurringTransaction")
	private Expense expense;

	public RecurringTransaction() {
		super();
	}

	public RecurringTransaction(int id, LocalDateTime startDate, LocalDateTime endDate, String recurPeriod,
			LocalDateTime nextRecurDate, boolean enabled, Expense expense) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.recurPeriod = recurPeriod;
		this.nextRecurDate = nextRecurDate;
		this.enabled = enabled;
		this.expense = expense;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getRecurPeriod() {
		return recurPeriod;
	}

	public void setRecurPeriod(String recurPeriod) {
		this.recurPeriod = recurPeriod;
	}

	public LocalDateTime getNextRecurDate() {
		return nextRecurDate;
	}

	public void setNextRecurDate(LocalDateTime nextRecurDate) {
		this.nextRecurDate = nextRecurDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
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
		RecurringTransaction other = (RecurringTransaction) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecurringTransaction [id=").append(id).append(", startDate=").append(startDate)
				.append(", endDate=").append(endDate).append(", recurPeriod=").append(recurPeriod)
				.append(", nextRecurDate=").append(nextRecurDate).append(", enabled=").append(enabled).append("]");
		return builder.toString();
	}

}
