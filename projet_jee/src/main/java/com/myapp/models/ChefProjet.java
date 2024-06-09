package com.myapp.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@DiscriminatorValue("CHEF_PROJET")
public class ChefProjet extends Utilisateur {


    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Projet> projets;

    public ChefProjet() {
        // TODO Auto-generated constructor stub
    }

    public ChefProjet(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }
}
