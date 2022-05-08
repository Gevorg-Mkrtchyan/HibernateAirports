package service.impl;

import dao.impl.PassengerDaoImpl;
import model.Passenger;
import service.ServiceCrudForAll;

import java.util.List;
import java.util.Set;

public class PassengerServiceImpl implements ServiceCrudForAll<Passenger> {
    PassengerDaoImpl passengerDao = new PassengerDaoImpl();

    @Override
    public Passenger findByID(Long id) {
        return passengerDao.findByID(id);
    }

    @Override
    public void create(Passenger passenger) {
        passengerDao.create(passenger);
    }

    @Override
    public void deleteById(int id) {
        passengerDao.deleteById(id);
    }

    @Override
    public void update(int id, Passenger passenger) {
        passengerDao.update(id, passenger);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerDao.findAll();
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {
        return passengerDao.get(offset, perPage, sort);
    }
}
