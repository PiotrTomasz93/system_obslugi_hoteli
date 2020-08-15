package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {

        checkRoomReservations();

        Configuration con = new Configuration().configure().addAnnotatedClass(Hotel.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session =sf.openSession();

        Transaction tx = session.beginTransaction();


        DataLoader dataLoader = new DataLoader(session, tx); // w hibernate.cfg.xml zmienic na create
        dataLoader.loadData();

        System.out.println("Witamy w aplikacji HotelsView. \nWybierz opcję, wpisz odpowiedni numer i zatwierdź klawiszem Enter.");
        System.out.println("1. Rezerwacja pokoju");
        System.out.println("2. Zalogowanie się do systemu.");
        System.out.println("3. Zamknij program");


        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();

        while (!value.equals("1") && !value.equals("2") && !value.equals("3")){
            System.out.println("Podano nieprawidłową wartość. Wybierz ponownie");
            value = scanner.nextLine();
        }

        if (value.equals("1")) {
            Reservation reservation = ReservationFactory.buildReservation(scanner, session, tx);
        } else if (value.equals("2")){
            SystemService systemService = new SystemService();
            System.out.println("Podaj hasło");
            String haslo = scanner.nextLine();
            if (haslo.equals(systemService.getPassword())){
                systemService.showOptions();
            }
        } else {
            System.out.println("\n\n                   KONIEC");
        }

        //List<HotelFacilities> fc = Arrays.asList(HotelFacilities.PARKING, HotelFacilities.RESTAURANT);

    }

    public static void checkRoomReservations(){ //TODO sprawdzenie dat wszystkich rezerwacji i zaktualizowanie dostępności pokoi

    }
}
