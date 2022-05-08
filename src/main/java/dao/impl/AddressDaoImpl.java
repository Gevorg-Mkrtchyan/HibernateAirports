package dao.impl;

import dao.DaoForAll;
import model.Address;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionFactoryHibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressDaoImpl implements DaoForAll<Address> {

    @Override
    public Address findByID(Long id) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Address address = session.find(Address.class, id);
        session.getTransaction().commit();
        session.close();
        return address;
    }

    @Override
    public void create(Address address) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(address);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Address address = session.find(Address.class, id);
        session.delete(address);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(int id, Address address) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Address oldAddress = session.find(Address.class, id);
        address.setId(oldAddress.getId());
        address.setCountry(oldAddress.getCountry());
        address.setCity(oldAddress.getCity());
        session.merge(address);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Address> findAll() {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Address> query = session.createQuery("SELECT readAll from Address readAll", Address.class);
        List<Address> addressSet = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return addressSet;
    }

    @Override
    public Set<Address> get(int offset, int perPage, String sort) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "SELECT c FROM Address c ORDER BY c." + sort + " asc";
        Query query = session.createQuery(sql).
                setMaxResults(perPage).setFirstResult(offset);
        Set<Address> addressSet = new HashSet<>(query.getResultList());
        session.getTransaction().commit();
        session.close();
        return addressSet;
    }
}
