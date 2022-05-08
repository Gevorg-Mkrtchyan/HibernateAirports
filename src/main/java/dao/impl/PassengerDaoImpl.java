package dao.impl;

import dao.DaoForAll;

import model.Passenger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionFactoryHibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerDaoImpl implements DaoForAll<Passenger> {
    @Override
    public Passenger findByID(Long id) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Passenger passenger = session.find(Passenger.class, id);
        session.getTransaction().commit();
        session.close();
        return passenger;
    }

    @Override
    public void create(Passenger passenger) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(passenger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Passenger passenger = session.find(Passenger.class, id);
        session.delete(passenger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(int id, Passenger passenger) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Passenger oldPassenger = session.find(Passenger.class, id);
        oldPassenger.setId(passenger.getId());
        oldPassenger.setName(passenger.getName());
        oldPassenger.setPhone(passenger.getPhone());
        oldPassenger.setAddress(passenger.getAddress());
        session.merge(passenger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Passenger> findAll() {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Passenger> query = session.createQuery("select c from Passenger c", Passenger.class);
        List<Passenger> passengerList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return passengerList;
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select c from Passenger c order by c." + sort + " asc";
        Query query = session.createQuery(sql).setMaxResults(perPage).setFirstResult(offset);
        Set<Passenger> passengerSet = new HashSet<>(query.getResultList());
        session.getTransaction().commit();
        session.close();
        return passengerSet;

    }
}
