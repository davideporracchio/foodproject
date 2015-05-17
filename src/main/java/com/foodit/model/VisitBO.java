package com.foodit.model;

import java.time.LocalDateTime;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visit")
public class VisitBO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "date_visit")
	private LocalDateTime dateVisit;

	@Column(name = "money_spent")
	private double moneySpent;

	@Column(name = "point")
	private int point;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "visit_rule", joinColumns = { @JoinColumn(name = "visit_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "rule_id", nullable = false, updatable = false) })
	private List<RuleBO> listRules = new ArrayList<RuleBO>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statement_id")
	private StatementBO statementBO;

	public LocalDateTime getDateVisit() {
		return dateVisit;
	}

	public void setDateVisit(LocalDateTime dateVisit) {
		this.dateVisit = dateVisit;
	}

	public double getMoneySpent() {
		return moneySpent;
	}

	public void setMoneySpent(double moneySpent) {
		this.moneySpent = moneySpent;
	}

	public List<RuleBO> getListRules() {
		return listRules;
	}

	public void setListRules(List<RuleBO> listRules) {
		this.listRules = listRules;
	}

	public StatementBO getStatementBO() {
		return statementBO;
	}

	public void setStatementBO(StatementBO statementBO) {
		this.statementBO = statementBO;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateVisit == null) ? 0 : dateVisit.hashCode());
		result = prime * result + ((listRules == null) ? 0 : listRules.hashCode());
		long temp;
		temp = Double.doubleToLongBits(moneySpent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + point;
		result = prime * result + ((statementBO == null) ? 0 : statementBO.hashCode());
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
		VisitBO other = (VisitBO) obj;
		if (dateVisit == null) {
			if (other.dateVisit != null)
				return false;
		} else if (!dateVisit.equals(other.dateVisit))
			return false;
		if (listRules == null) {
			if (other.listRules != null)
				return false;
		} else if (!listRules.equals(other.listRules))
			return false;
		if (Double.doubleToLongBits(moneySpent) != Double.doubleToLongBits(other.moneySpent))
			return false;
		if (point != other.point)
			return false;
		if (statementBO == null) {
			if (other.statementBO != null)
				return false;
		} else if (!statementBO.equals(other.statementBO))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer note =new StringBuffer( " - Visit on date "+dateVisit.toString() +", spent "+moneySpent +" and earned point "+point);
		note.append("\n\t\tRule applied:");
		listRules.stream().forEach(t->note.append("\n\t\t"+t.toString()));
		return note.toString();
	
	}
	
	

}
