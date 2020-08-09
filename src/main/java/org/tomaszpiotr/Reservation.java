package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class Reservation {

    LocalDate dateFrom;
    LocalDate dateTo;


    public static boolean checkHotelName(String hotelName, Session session){
        Query query = session.createQuery("select name from Hotel");
        List<String> hotels = query.list();
        if (hotels.contains(hotelName)) {
            return true;
        }
        return false;
    }
}
