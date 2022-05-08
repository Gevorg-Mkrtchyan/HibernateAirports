package util;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Table;


public class SessionFactoryHibernate {
    private static SessionFactory sessionFactory;

    private SessionFactoryHibernate() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Passenger.class);
            configuration.addAnnotatedClass(PassInTrip.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Trip.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
