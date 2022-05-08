package service.impl;

import dao.impl.TripDaoImpl;
import model.Trip;
import service.ServiceCrudForAll;

import java.util.List;
import java.util.Set;

public class TripServiceImpl implements ServiceCrudForAll<Trip> {
    TripDaoImpl tripDao = new TripDaoImpl();

    @Override
    public Trip findByID(Long id) {
        return tripDao.findByID(id);
    }

    @Override
    public void create(Trip trip) {
        tripDao.create(trip);
    }

    @Override
    public void deleteById(int id) {
        tripDao.deleteById(id);
    }

    @Override
    public void update(int id, Trip trip) {
        tripDao.update(id, trip);
    }

    @Override
    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        return tripDao.get(offset, perPage, sort);
    }

    public List<Trip> getTripsTo(String city) {
        return tripDao.getTripsTo(city);
    }

    public List<Trip> getTripsFrom(String city) {
        return tripDao.getTripsFrom(city);
    }
}
