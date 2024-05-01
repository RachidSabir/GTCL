package com.myapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private int id;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name")
	    private String lastName;

	    @Column(name = "email")
	    private String email;
	    
	    @Column(name = "password")
	    private String password;
	    
	    public Utilisateur() {}
	    
	    public Utilisateur(String firstName, String lastName, String email, String password) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.password = password;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		public boolean equal(String e,String p) {
			if (this.getEmail().equals(e) && this.getEmail().equals(p))
				return true;
			return false;
		}
	}



