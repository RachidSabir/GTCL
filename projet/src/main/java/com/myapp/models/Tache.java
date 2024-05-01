package com.myapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tache")
public class Tache {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "titre")
	String titre;
	@Column(name = "description")
	String description;
	
	public Tache() {
		
	}
	public Tache(String titre, String description) {
			this.titre=titre;
			this.description=description;
	}
	
	public Tache(int id, String titre, String description) {
		this.titre=titre;
		this.description=description;
		this.id=id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}

}

