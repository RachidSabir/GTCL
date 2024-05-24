package com.myapp.service;

import com.myapp.models.Tache;
import com.myapp.models.TaskStatus;
import com.myapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TacheService {
    public TacheService() {
    }

    public void saveTache(Tache tache) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(tache);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Tache> getAllTaches() {
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
            session.merge(tache);
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
                session.remove(tache);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // New method to retrieve tasks by creator
    public List<Tache> getTachesByCreator(int creatorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = session.createQuery("from Tache where createdBy.id = :creatorId", Tache.class);
            query.setParameter("creatorId", creatorId);
            return query.list();
        }
    }

    // New method to retrieve tasks by assignee
    public List<Tache> getTachesByAssignee(int assigneeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = session.createQuery("from Tache where assignedTo.id = :assigneeId", Tache.class);
            query.setParameter("assigneeId", assigneeId);
            return query.list();
        }
    }

    public long totalTasksAssignedTo(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from Tache p where p.assignedTo.id =:id", Long.class);
            query.setParameter("id", id);
            return query.uniqueResult();  // returns a single result as a long
        }
    }

    public long totalTasksOnHold(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from Tache p where p.assignedTo.id =:id and  p.status = :status", Long.class);
            query.setParameter("status", TaskStatus.ON_HOLD);
            query.setParameter("id", id);
            return query.uniqueResult();  // returns a single result as a long
        }
    }

    public long totalTasksNEW(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from Tache p where p.assignedTo.id =:id and  p.status = :status ", Long.class);
            query.setParameter("status", TaskStatus.NEW);
            query.setParameter("id", id);
            return query.uniqueResult();  // returns a single result as a long
        }
    }

    public long totalTasksInProgress(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from Tache p where p.assignedTo.id =:id and  p.status = :status ", Long.class);
            query.setParameter("status", TaskStatus.IN_PROGRESS);
            query.setParameter("id", id);
            return query.uniqueResult();  // returns a single result as a long
        }
    }

    public long totalTasksDone(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from Tache p where p.assignedTo.id =:id and  p.status = :status ", Long.class);
            query.setParameter("status", TaskStatus.DONE);
            query.setParameter("id", id);
            return query.uniqueResult();  // returns a single result as a long
        }
    }
}
