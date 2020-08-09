package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private Session session;
    private Transaction tx;

    public DataLoader(Session session, Transaction tx) {
        this.session = session;
        this.tx = tx;
    }

    public void loadData(){

        List<Room> roomList1 = new ArrayList<>();
        List<Room> roomList2 = new ArrayList<>();
        List<Room> roomList3 = new ArrayList<>();
        List<Room> roomList4 = new ArrayList<>();
        Hotel hotel1 = new Hotel("Fenix", "Pomorska 16", "02-038", "Warszawa", StarRating.THREESTARS, roomList1);
        Hotel hotel2 = new Hotel("Novotel", "Ogrodowa 29", "02-207", "Warszawa", StarRating.FIVESTARS, roomList2 );
        Hotel hotel3 = new Hotel("Hilton", "Kwiatowa 14", "77-420", "Szczecin", StarRating.TWOSTARS, roomList3 );
        Hotel hotel4 = new Hotel("Amber", "Wawelska 84", "45-815", "Gdańsk", StarRating.THREESTARS, roomList4 );
        Room room1 = new Room();
        room1.setFree(true);
        room1.setNumberOfBeds(3);
        room1.setPrice(60);
        room1.setHotel(hotel1);

        Room room2 = new Room();
        room2.setFree(false);
        room2.setNumberOfBeds(2);
        room2.setPrice(70);
        room2.setHotel(hotel1);

        Room room3 = new Room();
        room3.setFree(true);
        room3.setNumberOfBeds(1);
        room3.setPrice(100);
        room3.setHotel(hotel1);

        hotel1.getRooms().add(room1);
        hotel1.getRooms().add(room2);
        hotel1.getRooms().add(room3);

        session.save(hotel1);
        session.save(room1);
        session.save(room2);
        session.save(room3);


        session.save(hotel2);
        session.save(hotel3);
        session.save(hotel4);



        tx.commit();

    }

}

