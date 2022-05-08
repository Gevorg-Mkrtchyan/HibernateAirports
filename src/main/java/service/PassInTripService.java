package service;

import dao.impl.PassInTripDaoImpl;
import model.PassInTrip;

import java.util.List;
import java.util.Set;

public class PassInTripService {
    PassInTripDaoImpl passInTripDao = new PassInTripDaoImpl();
    public void create(PassInTrip passInTrip) {
        passInTripDao.create(passInTrip);
    }
    public List<PassInTrip> findAll() {
        return passInTripDao.findAll();
    }
    public Set<PassInTrip> get(int offset, int perPage, String sort){
        return passInTripDao.get(offset,perPage,sort);
    }
}
