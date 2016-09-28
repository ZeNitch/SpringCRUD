package com.ivan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	 private String firstName;
	 private String lastName;
	 private String dateOfBirth;
	 private String phoneNumber;
	 private String email;
	 
	 public User(){
		 
	 }

	 public User(String firstName, String lastName, String dateOfBirth, String phoneNumber, String email) {
		 this.setFirstName(firstName);
		 this.setLastName(lastName);
		 this.setDateOfBirth(dateOfBirth);
		 this.setPhoneNumber(phoneNumber);
		 this.setEmail(email);
	 }
	 //userID getter/setter
	 public long getUserId() {
	        return id;
	    }
	 public void setUserId(int id) {
	        this.id = id;
	    }
	 //firstName getter/setter
	 public String getFirstName()
	 {
		 return firstName;
	 }
	 public void setFirstName(String firstName)
	 {
		 this.firstName=firstName;
	 }
	 //lastName getter/setter
	 public String getLastName()
	 {
		 return lastName;
	 }
	 public void setLastName(String lastName)
	 {
		 this.lastName=lastName;
	 }
	 
	 //dateOfBirth getter/setter
	 public String getDateOfBirth()
	 {
		 return dateOfBirth;
	 }
	 public void setDateOfBirth(String dateOfBirth)
	 {
		 this.dateOfBirth=dateOfBirth;
	 }
	 //phone getter/setter
	 public String getPhoneNumber()
	 {
		 return phoneNumber;
	 }
	 public void setPhoneNumber(String phoneNumber)
	 {
		 this.phoneNumber=phoneNumber;
	 }
	 //email getter/setter
	 public String getEmail()
	 {
		 return email;
	 }
	 public void setEmail(String email)
	 {
		 this.email=email;
	 }

}
