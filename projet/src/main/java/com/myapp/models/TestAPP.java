package com.myapp.models;

import java.util.List;


public class TestAPP {
    public static void main(String[] args) {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur("Rachid", "Sabir", "rachid@gmail.com","root");
        utilisateurService.saveUtilisateur(utilisateur);
        List < Utilisateur > utilisateurs = utilisateurService.getUtilisateurs();
        utilisateurs.forEach(s -> System.out.println(s.getFirstName()));
        
        
        TacheService tacheService = new TacheService();
        Tache tache = new Tache("tache1", "description");
        tacheService.saveTache(tache);
        List < Tache > taches = tacheService.getTache();
        taches.forEach(s -> System.out.println(s.getTitre()));
    }
}
