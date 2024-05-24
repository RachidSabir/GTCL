package com.myapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Tache")
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'NEW'")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Utilisateur createdBy;
    @ManyToOne
    @JoinColumn(name = "assigned_to", nullable = false)  // Use a unique name for the foreign key column
    private Membre assignedTo;

    @ManyToOne
    @JoinColumn(name = "project_id")  // This column in the 'Tache' table must match the FK to 'Project'
    private Projet project;

    public Tache() {
    }

    public Tache(String titre, String description, Utilisateur createdBy, Membre membre) {
        this.titre = titre;
        this.description = description;
        this.createdBy = createdBy;
        this.assignedTo = membre;
    }

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = TaskStatus.NEW;
        }
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id_tache) {
        this.id = id_tache;
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

    public Membre getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Membre assignedTo) {
        this.assignedTo = assignedTo;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Utilisateur getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Utilisateur createdBy) {
        this.createdBy = createdBy;
    }

    public Projet getProject() {
        return project;
    }

    public void setProject(Projet projet) {
        this.project = projet;
    }
}
