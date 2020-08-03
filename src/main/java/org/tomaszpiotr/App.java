package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        System.out.println("Witamy w aplikacji HotelsView. \nWybierz opcję, wpisz odpowiedni numer i zatwierdź klawiszem Enter.");
        System.out.println("1. Rezerwacja pokoju");
        System.out.println("2. Zalogowanie się do systemu.");


        List<HotelFacilities> fc = Arrays.asList(HotelFacilities.PARKING, HotelFacilities.RESTAURANT);
        Hotel hotel1 = new Hotel("Fenix", "Pomorska 16", "02-038", "Warszawa", StarRating.THREESTARS, fc, null );

        Configuration con = new Configuration().configure().addAnnotatedClass(Hotel.class).addAnnotatedClass(Room.class).addAnnotatedClass(HotelFacilities.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session =sf.openSession();

        Transaction tx = session.beginTransaction();
        session.save(hotel1);

        tx.commit();

        System.out.println("zapisano hotel do bazy");
    }
}
