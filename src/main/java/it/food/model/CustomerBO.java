package it.food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="customer", 
uniqueConstraints=@UniqueConstraint(columnNames={"fidelity_card_id"}))
public class CustomerBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="fidelity_card_id")
	private String fidelityCardID;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getFidelityCardID() {
		return fidelityCardID;
	}

	public void setFidelityCardID(String fidelityCardID) {
		this.fidelityCardID = fidelityCardID;
	}
	
	@Override
	public String toString() {
		return "fidelity card: "+fidelityCardID;
	}


}
