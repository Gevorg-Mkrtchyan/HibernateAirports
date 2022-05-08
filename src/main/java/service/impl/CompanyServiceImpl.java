package service.impl;

import dao.impl.CompanyDaoImpl;
import model.Company;
import service.ServiceCrudForAll;

import java.util.List;
import java.util.Set;

public class CompanyServiceImpl implements ServiceCrudForAll<Company> {
    CompanyDaoImpl companyDao = new CompanyDaoImpl();

    @Override
    public Company findByID(Long id) {
        return companyDao.findByID(id);
    }

    @Override
    public void create(Company company) {
        companyDao.create(company);
    }

    @Override
    public void deleteById(int id) {
        companyDao.deleteById(id);
    }

    @Override
    public void update(int id, Company company) {
        companyDao.update(id, company);
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        return companyDao.get(offset, perPage, sort);
    }
}
