package com.myapp.service;

import com.myapp.models.*;
import com.myapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class UtilisateurService {

    private TacheService tacheService;

    public UtilisateurService() {
        this.tacheService = new TacheService();
    }

    public List<Utilisateur> getUtilisateurs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Utilisateur> utilisateurList = session.createQuery("from Utilisateur", Utilisateur.class).list();
            for (Utilisateur user : utilisateurList) {
                user.setRole(this.getUserType(user));
            }
            return utilisateurList;
        }
    }

    public List<Membre> getMembres() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // This query ensures that only objects that are exactly instances of Membre are returned
            return session.createQuery("from Membre v WHERE TYPE(v) = Membre", Membre.class).list();
        }
    }

    public List<ChefProjet> getProjectManager() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // This query ensures that only objects that are exactly instances of Membre are returned
            return session.createQuery("from ChefProjet v WHERE TYPE(v) = ChefProjet", ChefProjet.class).list();
        }
    }

    public boolean valideUtilisateur(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Utilisateur> query = session.createQuery("from Utilisateur where email = :email and password = :password", Utilisateur.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Utilisateur getUtilisateurByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Utilisateur where email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    public void saveUtilisateur(Utilisateur utilisateur) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(utilisateur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private String getUserType(Utilisateur utilisateur) {
        if (utilisateur instanceof Admin) {
            return "Admin";
        } else if (utilisateur instanceof ChefProjet) {
            return "Chef Projet";
        } else if (utilisateur instanceof Membre) {
            return "Membre";
        } else {
            return "Unknown";
        }
    }

    public List<Utilisateur> getUtilisateur() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Utilisateur ", Utilisateur.class).list();
        }
    }


    public void ajouterUtilisateur(Utilisateur utilisateur) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(utilisateur);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateUtilisateur(Utilisateur utilisateur) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(utilisateur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Boolean deleteUtilisateur(int userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Utilisateur utilisateur = session.get(Utilisateur.class, userId);
            List<Tache> tacheCreatedByList = tacheService.getTachesByCreator(userId);
            for (Tache tache : tacheCreatedByList) {
                tache.setCreatedBy(null);
                tacheService.updateTache(tache);
            }
            if (utilisateur != null) {
                session.remove(utilisateur);
                transaction.commit();
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Utilisateur getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Utilisateur utilisateur = session.get(Utilisateur.class, id);
            utilisateur.setRole(this.getUserType(utilisateur));
            return utilisateur;
        }
    }

    public long totalMembres() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from Membre p ", Long.class);
            return query.uniqueResult();  // returns a single result as a long
        }
    }

    public long totalProjectManagers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from ChefProjet p ", Long.class);
            return query.uniqueResult();  // returns a single result as a long
        }
    }
}

