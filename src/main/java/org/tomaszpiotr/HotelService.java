package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class HotelService {

    public static void printHotels(Session session){
        List<Hotel> hotels = HotelRepository.getAllHotels(session);
        System.out.println("nazwa hotelu (miasto)");
        for (Hotel item: hotels){
            System.out.println(item.getName() + " (" + item.getCity() + ")");
        }

//        Query query = session.createQuery("select name, city from Hotel");
//        List<Object[]> hotels = (List<Object[]>) query.list();
//        System.out.println("nazwa hotelu (miasto)");
//        for (Object[] item: hotels){
//            System.out.println(item[0] + " (" + item[1] + ")");
//        }

    }

    public static boolean checkHotelName(String hotelName, Session session){
        List<Hotel> hotels = HotelRepository.getAllHotels(session);
        List<String> hotelsNames = hotels.stream().map(e->e.getName()).collect(Collectors.toList());
        if (hotelsNames.contains(hotelName)) {
            return true;
        }
        return false;
    }


}
