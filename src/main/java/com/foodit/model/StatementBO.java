package com.foodit.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "statement", uniqueConstraints = @UniqueConstraint(columnNames = { "customer_id", "date" }))
public class StatementBO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "starting_points")
	private int startingPoints;

	@Column(name = "ending_points")
	private int endingPoints;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private CustomerBO customer;

	@Column(name = "date")
	private String stringDate;

	@Column(name = "value_points")
	private double valuePoints;

	@OneToMany(mappedBy = "statementBO", targetEntity = VisitBO.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<VisitBO> listVisits = new ArrayList<VisitBO>();

	public int getStartingPoints() {
		return startingPoints;
	}

	public void setStartingPoints(int startingPoints) {
		this.startingPoints = startingPoints;
	}

	public int getEndingPoints() {
		return endingPoints;
	}

	public void setEndingPoints(int endingPoints) {
		this.endingPoints = endingPoints;
	}

	public double getValuePoints() {
		return valuePoints;
	}

	public void setValuePoints(double valuePoints) {
		this.valuePoints = valuePoints;
	}

	public List<VisitBO> getListVisits() {
		return listVisits;
	}

	public void setListVisits(List<VisitBO> listVisits) {
		this.listVisits = listVisits;
	}

	public CustomerBO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBO customer) {
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + endingPoints;
		result = prime * result + ((listVisits == null) ? 0 : listVisits.hashCode());
		result = prime * result + startingPoints;
		result = prime * result + ((stringDate == null) ? 0 : stringDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valuePoints);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatementBO other = (StatementBO) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (endingPoints != other.endingPoints)
			return false;
		if (listVisits == null) {
			if (other.listVisits != null)
				return false;
		} else if (!listVisits.equals(other.listVisits))
			return false;
		if (startingPoints != other.startingPoints)
			return false;
		if (stringDate == null) {
			if (other.stringDate != null)
				return false;
		} else if (!stringDate.equals(other.stringDate))
			return false;
		if (Double.doubleToLongBits(valuePoints) != Double.doubleToLongBits(other.valuePoints))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer note = new StringBuffer("Statement for user with " + customer);
		note.append("\nVisits in the current month:");
		listVisits.stream().forEach(t -> note.append("\n\t" + t.toString()));
		note.append("\n\tLast month points " + startingPoints);
		note.append("\n\tCurrent points " + endingPoints);
		BigDecimal bd = new BigDecimal(valuePoints);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

		note.append("\n\tValue points " + bd +"\n");
		return note.toString();
	}

}
