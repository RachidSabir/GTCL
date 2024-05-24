package com.myapp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Projet")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private ChefProjet chef;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Tache> taches;

    public Projet() {
    }

    public Projet(String titre, String description, ChefProjet chef) {
        this.titre = titre;
        this.description = description;
        this.chef = chef;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int idProjet) {
        this.id = idProjet;
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

    public ChefProjet getChef() {
        return chef;
    }

    public void setChef(ChefProjet chef) {
        this.chef = chef;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}
