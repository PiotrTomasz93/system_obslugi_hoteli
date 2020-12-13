package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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


        Configuration con = new Configuration().configure().addAnnotatedClass(Hotel.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session =sf.openSession();

        Transaction tx = session.beginTransaction();


//        DataLoader dataLoader = new DataLoader(session, tx); // w hibernate.cfg.xml zmienic na create
//        dataLoader.loadData();

        checkRoomReservations(session);

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


        if (value.equals("1")) { // REZERWACJA POKOJU PRZEZ KLIENTA
            Reservation reservation = ReservationFactory.buildReservation(scanner, session, tx);


        } else if (value.equals("2")){  // OBSŁUGA SYSTEMU PRZEZ PRACOWNIKA
            SystemService systemService = new SystemService();
            System.out.println("Podaj hasło");
            String haslo = scanner.nextLine();
            if (haslo.equals(systemService.getPassword())){
                systemService.showOptions();
            }
            value = scanner.nextLine();
            switch (value){
                case "1":
                    Hotel hotel = HotelFactory.buildHotel();
                    session.save(hotel);
                    tx.commit();
                    break;
                case "2":
                    Room room  = RoomFactory.buildRoom(session);
                    session.save(room);
                    tx.commit();
                    break;
                case "3":
                    Reservation reservation = ReservationFactory.buildReservation(scanner, session, tx);
                case "4":
                    ReservationService.printAllReservations(session);
            }


        } else {
            System.out.println("\n\n                   KONIEC");
        }

        //List<HotelFacilities> fc = Arrays.asList(HotelFacilities.PARKING, HotelFacilities.RESTAURANT);

    }

    public static void checkRoomReservations(Session session){ //TODO sprawdzenie dat wszystkich rezerwacji i zaktualizowanie dostępności pokoi
        List<Reservation> reservations = ReservationRepository.getAllReservations(session);

        for (Reservation reservation: reservations){
            if(reservation.getDateTo().isBefore(LocalDate.now())){
                ReservationRepository.deleteReservation(session, reservation.getId());

            }
        }


    }
}
