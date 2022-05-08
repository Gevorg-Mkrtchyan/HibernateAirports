package dao.impl;

import dao.DaoForAll;
import model.PassInTrip;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionFactoryHibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassInTripDaoImpl {
    public void create(PassInTrip passInTrip) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(passInTrip);
        session.getTransaction().commit();
        session.close();
    }
    public List<PassInTrip> findAll() {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM PassInTrip ");
        List<PassInTrip> passInTripList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return passInTripList;
    }
    public Set<PassInTrip> get(int offset, int perPage, String sort) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select c from PassInTrip c order by c." + sort + " asc";
        Query query = session.createQuery(sql).setMaxResults(perPage).setFirstResult(offset);
        Set<PassInTrip> passInTripSet = new HashSet<>(query.getResultList());
        session.getTransaction().commit();
        session.close();
        return passInTripSet;
    }
}