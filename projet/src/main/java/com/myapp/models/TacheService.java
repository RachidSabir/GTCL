package com.myapp.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.myapp.util.HibernateUtil;

public class TacheService {
    public void saveTache(Tache tache) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tache);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public TacheService() {
		// TODO Auto-generated constructor stub
	}

    public List<Tache> getTache() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Tache", Tache.class).list();
        }
    }
    
    public Tache getTacheById(int tacheId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tache.class, tacheId);
        }
    }

    public void updateTache(Tache tache) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tache);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTache(int tacheId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tache tache = session.get(Tache.class, tacheId);
            if (tache != null) {
                session.delete(tache);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
	