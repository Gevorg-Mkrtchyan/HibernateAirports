import dao.impl.*;
import model.*;
import service.impl.*;

public class Start {
    public static void main(String[] args) {
        for (PassInTrip pass : new PassInTripDaoImpl().get(1, 5, "date")) {
            System.out.println(pass);
        }
    }
}
