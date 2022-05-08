package util;

import dao.impl.CompanyDaoImpl;
import dao.impl.PassInTripDaoImpl;
import dao.impl.PassengerDaoImpl;
import dao.impl.TripDaoImpl;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class AddInfo {
    public static void addCompanyInfo() {
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        String file = "src/main/resources/companies.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String s = bufferedReader.readLine();
            String[] rslt = new String[2];
            String[] dt = new String[3];
            while (true) {
                s = bufferedReader.readLine();
                if (s == null) {
                    break;
                }
                rslt = s.split(",");
                dt = rslt[1].split("/");
                Company company = new Company();
                company.setCompanyName(rslt[0]);
                company.setFoundingDate(Date.valueOf(dt[2] + "-" + dt[0] + "-" + dt[1]));
                companyDao.create(company);
            }
        } catch (IOException e) {
            System.out.println("companies.txt file is not found");
        }
    }

    public static Set<Address> addAddressInfo(String path) {
        Set<Address> addressSet = new HashSet<>();
        Address address;
        String[] read;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (true) {
                try {
                    if ((line = br.readLine()) == null) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                line = line.replace("'", "");
                read = line.split(",");
                address = new Address();
                address.setCountry(read[2]);
                address.setCity(read[3]);
                addressSet.add(address);
            }
        } catch (IOException e) {
            System.out.println("passengers.txt file is not found");
        }
        return addressSet;
    }

    public static void addPassengerInfo() {
        String file = "src/main/resources/passengers.txt";
        Set<Address> setAdd = addAddressInfo(file);
        Passenger passenger = new Passenger();
        PassengerDaoImpl passengerDao = new PassengerDaoImpl();
        String line;
        String[] read;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (true) {
                try {
                    if ((line = bufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (line.contains("'")) {
                    line = line.replace("'", "՛");
                }
                read = line.split(",");
                passenger.setName(read[0]);
                passenger.setPhone(read[1]);
                Address address = new Address();
                address.setCountry(read[2]);
                address.setCity(read[3]);
                for (Address address1 : setAdd) {
                    if (address1.equals(address)) {
                        passenger.setAddress(address1);
                        passengerDao.create(passenger);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("passengers.txt file is not found");
        }
    }

    public static void addTripInfo() {
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        TripDaoImpl tripDao = new TripDaoImpl();
        Trip trip = new Trip();
        String file = "src/main/resources/trips.txt";
        String[] read;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                read = line.split(",");
                trip.setId(Long.valueOf(read[0]));
                Company company = companyDao.findByID(Long.parseLong(read[1]));
                trip.setCompany(company);
                trip.setPlane(read[2]);
                trip.setTownFrom(read[3]);
                trip.setTownTo(read[4]);
                trip.setTimeOut(LocalTime.parse(read[5].split(" ")[1]));
                trip.setTimeIn(LocalTime.parse(read[6].split(" ")[1]));
                tripDao.create(trip);
            }
        } catch (IOException e) {
            System.out.println("trips.txt file is not found");
        }
    }
    public static void createPassInTripFromFile() {
        PassInTripDaoImpl passInTripDao = new PassInTripDaoImpl();

        PassInTrip passInTrip = new PassInTrip();
        String file = "src/main/resources/passInTrip.txt";
        String[] words;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (true) {
                try {
                    if ((line = br.readLine()) == null) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                line = line.replace("'", "");
                words = line.split(",");
                passInTrip.setTripId(Long.parseLong(words[0]));
                passInTrip.setPsgId(Long.parseLong(words[1]));
                passInTrip.setDate(LocalDate.parse(words[2],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
                passInTrip.setPlace(words[3]);
                passInTripDao.create(passInTrip);
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
