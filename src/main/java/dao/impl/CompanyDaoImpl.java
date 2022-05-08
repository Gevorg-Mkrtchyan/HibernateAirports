package dao.impl;

import dao.DaoForAll;
import model.Company;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionFactoryHibernate;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompanyDaoImpl implements DaoForAll<Company> {
    @Override
    public Company findByID(Long id) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Company company = session.find(Company.class, id);
        session.getTransaction().commit();
        session.close();
        return company;
    }

    @Override
    public void create(Company company) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(company);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Company company = session.find(Company.class, id);
        session.delete(company);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void update(int id, Company company) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Company oldCompany = session.find(Company.class, id);
        company.setId(oldCompany.getId());
        company.setCompanyName(oldCompany.getCompanyName());
        company.setFoundingDate(oldCompany.getFoundingDate());
        session.merge(company);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Company> findAll() {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Company> query = session.createQuery("select readAll from Company readAll", Company.class);
        List<Company> companySet =query.getResultList();
        session.getTransaction().commit();
        session.close();
        return companySet;
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        Session session = SessionFactoryHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select c from Company c order by c." + sort + " asc";
        Query query = session.createQuery(sql).setMaxResults(perPage).setFirstResult(offset);
        Set<Company>companySet = new HashSet<>(query.getResultList());
        session.getTransaction().commit();
        session.close();
        return companySet;
    }
}
