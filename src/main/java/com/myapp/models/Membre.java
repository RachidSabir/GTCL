package com.myapp.models;

import com.myapp.util.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.List;

@Entity
@DiscriminatorValue("MEMBRE")
public class Membre extends Utilisateur {

    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Tache> taches;

    public Membre() {
    }

    public Membre(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public List<Membre> getMembres() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Membre", Membre.class).list();
        }
    }


    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}
