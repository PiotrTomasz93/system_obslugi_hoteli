package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @OneToOne
    Room room;
    int numberOfPeople;
    LocalDate dateFrom;
    LocalDate dateTo;


    public Room getRoom() {
        return room;
    }

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

    public void updateRoomAvailability(String id, Session session){
        Room room = session.load(Room.class, Short.valueOf(id));
        if (room.isFree()){
            room.setFree(false);
        } else {
            room.setFree(true);
        }
        session.update(room);
    }

    public void setNumberOfPeople(int numberOfPeople){
        this.numberOfPeople = numberOfPeople;
    }

    public void setDateFrom(LocalDate dateFrom){
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo){
        this.dateTo = dateTo;
    }

    public int calculatePrice(){
        int days = (int) ChronoUnit.DAYS.between(dateFrom, dateTo);
        return (int) (numberOfPeople * room.getPrice() * days);
    }

}
