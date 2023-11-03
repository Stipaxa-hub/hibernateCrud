package org.example.dao.impl;

import org.example.dao.CountryDao;
import org.example.model.Country;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CountryDaoImpl implements CountryDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public Country add(Country country) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save country" + country + " to DB: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Country country = session.get(Country.class, id);
            return Optional.ofNullable(country);
        } catch (Exception e) {
            throw new RuntimeException("Can't get country with id: " + id, e);
        }
    }

    @Override
    public List<Country> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Country ", Country.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all countries ", e);
        }
    }

    @Override
    public Country updateById(Long id, Country actor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "UPDATE Country SET name = :name WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("name", actor.getName());
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't update the country: " + actor, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return actor;
    }

    @Override
    public void remove(Country country) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't delete the country: " + country, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
