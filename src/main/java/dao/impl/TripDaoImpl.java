package dao.impl;

import dao.DaoForAll;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionFactoryHibernate;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripDaoImpl implements DaoForAll<Trip> {
    @Override
    public Trip findByID(Long id) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Trip trip = session.find(Trip.class, id);
        session.getTransaction().commit();
        session.close();
        return trip;
    }

    @Override
    public void create(Trip trip) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(trip);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Trip trip = session.find(Trip.class, id);
        session.delete(trip);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(int id, Trip trip) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Trip oldTrip = session.find(Trip.class, id);
        trip.setId(oldTrip.getId());
        trip.setPlane((oldTrip.getPlane()));
        trip.setTimeIn(oldTrip.getTimeIn());
        trip.setTimeOut(oldTrip.getTimeOut());
        trip.setTownTo(oldTrip.getTownTo());
        trip.setTownFrom(trip.getTownFrom());
        session.merge(trip);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Trip> findAll() {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Trip");
        List<Trip> trips = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return trips;
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select c from Trip c order by c." + sort + " asc";
        Query query = session.createQuery(sql).setMaxResults(perPage).setFirstResult(offset);
        Set<Trip> tripSet = new HashSet<>(query.getResultList());
        session.getTransaction().commit();
        session.close();
        return tripSet;
    }

    public List<Trip> getTripsFrom(String city) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Query <Trip> query = session.createQuery("from Trip where townFrom ='" + city + "'");
        List<Trip> tripSet = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripSet;
    }

    public List<Trip> getTripsTo(String city) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Trip> query = session.createQuery("from Trip where townTo ='" + city + "'");
        List<Trip> tripSet = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripSet;
    }

}
