package com.myapp.service;

import com.myapp.models.Projet;
import com.myapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProjetService {

    public ProjetService() {
    }

    public List<Projet> getProjets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Projet", Projet.class).list();
        }
    }


    public void ajouterProjet(Projet projet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(projet);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateProjet(Projet projet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(projet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Boolean deleteProject(int projetId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Projet projet = session.get(Projet.class, projetId);
            if (projet != null) {
                session.remove(projet);
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

    public Projet getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Projet.class, id);
        }
    }

    public List<Projet> getListProjectManagedBy(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Projet> query = session.createQuery("from Projet where chef.id = :userId", Projet.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

    public long totalProjet() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from Projet p ", Long.class);
            return query.uniqueResult();  // returns a single result as a long
        }
    }
}
