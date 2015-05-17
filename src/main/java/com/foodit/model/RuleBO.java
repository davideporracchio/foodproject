package com.foodit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
public class RuleBO {

	public enum RuleType {
		ONDAYANDTIME, ONVISIT, ONTOTAL
	};

	public RuleBO() {
	}

	public RuleBO(RuleType ruleType, int sumPoints, int moltiplyPoints, String dayAndTime, int numberVisit, int amountSpent, String description, boolean activeRule) {
		super();
		this.ruleType = ruleType;
		this.sumPoints = sumPoints;
		this.moltiplyPoints = moltiplyPoints;
		this.dayAndTime = dayAndTime;
		this.numberVisit = numberVisit;
		this.amountSpent = amountSpent;
		this.description = description;
		this.activeRule = activeRule;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "rule_type")
	private RuleType ruleType;
	
	@Column(name = "sum_points")
	private int sumPoints;
	
	@Column(name = "moltiply_points")
	private int moltiplyPoints;
	
	@Column(name = "day_time")
	private String dayAndTime;
	
	@Column(name = "number_visit")
	private int numberVisit;
	
	@Column(name = "amount_spent")
	private int amountSpent;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "active_rule")
	private boolean activeRule;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSumPoints() {
		return sumPoints;
	}

	public void setSumPoints(int sumPoints) {
		this.sumPoints = sumPoints;
	}

	public int getMoltiplyPoints() {
		return moltiplyPoints;
	}

	public void setMoltiplyPoints(int moltiplyPoints) {
		this.moltiplyPoints = moltiplyPoints;
	}

	public String getDayAndTime() {
		return dayAndTime;
	}

	public void setDayAndTime(String dayAndTime) {
		this.dayAndTime = dayAndTime;
	}

	public int getNumberVisit() {
		return numberVisit;
	}

	public void setNumberVisit(int numberVisit) {
		this.numberVisit = numberVisit;
	}

	public int getAmountSpent() {
		return amountSpent;
	}

	public void setAmountSpent(int amountSpent) {
		this.amountSpent = amountSpent;
	}

	
	

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public boolean isActiveRule() {
		return activeRule;
	}

	public void setActiveRule(boolean activeRule) {
		this.activeRule = activeRule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activeRule ? 1231 : 1237);
		result = prime * result + amountSpent;
		result = prime * result + ((dayAndTime == null) ? 0 : dayAndTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + moltiplyPoints;
		result = prime * result + numberVisit;
		result = prime * result + ((ruleType == null) ? 0 : ruleType.hashCode());
		result = prime * result + sumPoints;
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
		RuleBO other = (RuleBO) obj;
		if (activeRule != other.activeRule)
			return false;
		if (amountSpent != other.amountSpent)
			return false;
		if (dayAndTime == null) {
			if (other.dayAndTime != null)
				return false;
		} else if (!dayAndTime.equals(other.dayAndTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (moltiplyPoints != other.moltiplyPoints)
			return false;
		if (numberVisit != other.numberVisit)
			return false;
		if (ruleType != other.ruleType)
			return false;
		if (sumPoints != other.sumPoints)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return description;
	}

	
	
}
