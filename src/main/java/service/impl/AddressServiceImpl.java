package service.impl;

import dao.impl.AddressDaoImpl;
import model.Address;
import service.ServiceCrudForAll;

import java.util.List;
import java.util.Set;

public class AddressServiceImpl implements ServiceCrudForAll<Address> {
    AddressDaoImpl addressDao = new AddressDaoImpl();

    @Override
    public Address findByID(Long id) {
        return addressDao.findByID(id);
    }

    @Override
    public void create(Address address) {
        addressDao.create(address);
    }

    @Override
    public void deleteById(int id) {
        addressDao.deleteById(id);
    }

    @Override
    public void update(int id, Address address) {
        addressDao.update(id, address);
    }

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public Set<Address> get(int offset, int perPage, String sort) {
        return addressDao.get(offset, perPage, sort);
    }
}
