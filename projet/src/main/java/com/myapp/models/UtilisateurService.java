package com.myapp.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.myapp.util.HibernateUtil;


public class UtilisateurService {
	
	public UtilisateurService() {}
	public void saveUtilisateur(Utilisateur utilisateur) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        
        transaction = session.beginTransaction();
        
        session.save(utilisateur);
        
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}

	public List < Utilisateur > getUtilisateurs() {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.createQuery("from Utilisateur", Utilisateur.class).list();
	    }
	    }
	
	public boolean ValideUtilisateur(String email, String password) {
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
	
}

