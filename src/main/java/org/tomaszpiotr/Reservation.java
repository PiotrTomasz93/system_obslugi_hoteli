package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.hibernate.query.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Reservation {

    int numberOfPeople;
    LocalDate dateFrom;
    LocalDate dateTo;
    Room room;


    public void printHotels(Session session){
        Query query = session.createQuery("select name, city from Hotel");
        List<Object[]> hotels = (List<Object[]>) query.list();
        System.out.println("nazwa hotelu (miasto)");
        for (Object[] item: hotels){
            System.out.println(item[0] + " (" + item[1] + ")");
        }
    }

    public boolean checkHotelName(String hotelName, Session session){
        Query query = session.createQuery("select name from Hotel");
        List<String> hotels = query.list();
        if (hotels.contains(hotelName)) {
            return true;
        }
        return false;
    }

    public boolean checkFreeRooms(String hotelName, Session session){
        Query query = session.createQuery("select rooms from Hotel where name = :x");
        query.setParameter("x", hotelName);
//        Query query = session.createQuery(String.format("select rooms from Hotel where name = %s", hotelName));
        List<Room> rooms = query.list();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).isFree()){
                return true;
            }
        }
        return false;
    }

    public void printFreeRooms(String hotelName, Session session) {
        Query query = session.createQuery("select rooms from Hotel where name = :x");
        query.setParameter("x", hotelName);
        List<Room> rooms = query.list();
        rooms = rooms.stream().filter(r -> r.isFree()).collect(Collectors.toList());
        for (Room item: rooms){
            System.out.println(item);
        }
    }

    public void setRoom(String id, Session session){
        Query query = session.createQuery("from Room where id = :x");
        query.setParameter("x", Short.valueOf(id));
        this.room = (Room) query.uniqueResult();
    }

    public boolean checkRoomId(String hotelName, String id, Session session){
        Query query = session.createQuery("select rooms from Hotel where name = :x");
        query.setParameter("x", hotelName);
        List<Room> rooms = query.list();
        rooms = rooms.stream().filter(r -> r.isFree()).collect(Collectors.toList());
        
        for(Room item: rooms){
            if (item.getId() == Short.valueOf(id)){
                return true;
            }
        }
        return false;
    }
}
