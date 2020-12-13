package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.awt.desktop.QuitResponse;
import java.util.List;
public class HotelRepository {

    public static List<Hotel> getAllHotels(Session session){
        Query query = session.createQuery("from Hotel");
        List<Hotel> hotels = query.list();
        return hotels;
    }
}
