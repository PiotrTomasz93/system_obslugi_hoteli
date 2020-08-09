package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader { //TODO zrobić funkcje które będą automatycznie zapełniać tebele losowymi wartościami dla zadanej listy hoteli
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
        List<Room> roomList5 = new ArrayList<>();
        Hotel hotel1 = new Hotel("Fenix", "Pomorska 16", "02-038", "Warszawa", StarRating.THREESTARS, roomList1);
        Hotel hotel2 = new Hotel("Novotel", "Kościuszki 2", "02-207", "Warszawa", StarRating.FIVESTARS, roomList2 );
        Hotel hotel3 = new Hotel("Hilton", "Kwiatowa 14", "77-420", "Szczecin", StarRating.TWOSTARS, roomList3 );
//        Hotel hotel4 = new Hotel("Amber", "Wawelska 84", "45-815", "Gdańsk", StarRating.THREESTARS, roomList4 );
//        Hotel hotel5 = new Hotel("Radisson", "Ogrodowa 29", "23-015", "Kraków", StarRating.THREESTARS, roomList5 );

//        List<Hotel> hotels = Arrays.asList(hotel1, hotel2, hotel3, hotel4, hotel5);


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



        Room room4 = new Room();
        room4.setFree(true);
        room4.setNumberOfBeds(2);
        room4.setPrice(160);
        room4.setHotel(hotel2);

        Room room5 = new Room();
        room5.setFree(false);
        room5.setNumberOfBeds(4);
        room5.setPrice(80);
        room5.setHotel(hotel2);

        Room room6 = new Room();
        room6.setFree(false);
        room6.setNumberOfBeds(1);
        room6.setPrice(90);
        room6.setHotel(hotel2);

        Room room7 = new Room();
        room7.setFree(true);
        room7.setNumberOfBeds(2);
        room7.setPrice(60);
        room7.setHotel(hotel2);

        hotel2.getRooms().add(room4);
        hotel2.getRooms().add(room5);
        hotel2.getRooms().add(room6);
        hotel2.getRooms().add(room7);

        session.save(hotel2);
        session.save(room4);
        session.save(room5);
        session.save(room6);
        session.save(room7);



        Room room8 = new Room();
        room8.setFree(true);
        room8.setNumberOfBeds(2);
        room8.setPrice(40);
        room8.setHotel(hotel3);

        Room room9 = new Room();
        room9.setFree(false);
        room9.setNumberOfBeds(6);
        room9.setPrice(50);
        room9.setHotel(hotel3);

        Room room10 = new Room();
        room10.setFree(false);
        room10.setNumberOfBeds(3);
        room10.setPrice(65);
        room10.setHotel(hotel3);

        Room room11 = new Room();
        room11.setFree(false);
        room11.setNumberOfBeds(2);
        room11.setPrice(85);
        room11.setHotel(hotel3);

        Room room12 = new Room();
        room12.setFree(true);
        room12.setNumberOfBeds(3);
        room12.setPrice(75);
        room12.setHotel(hotel3);

        hotel3.getRooms().add(room8);
        hotel3.getRooms().add(room9);
        hotel3.getRooms().add(room10);
        hotel3.getRooms().add(room11);
        hotel3.getRooms().add(room12);

        session.save(hotel3);
        session.save(room8);
        session.save(room9);
        session.save(room10);
        session.save(room11);
        session.save(room12);

//        session.save(hotel4);
//        session.save(hotel5);



        tx.commit();

    }

}

