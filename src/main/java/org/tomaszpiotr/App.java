package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.*;

public class App
{
    public static void main( String[] args ) {


        Configuration con = new Configuration().configure().addAnnotatedClass(Hotel.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session =sf.openSession();



//        DataLoader dataLoader = new DataLoader(session); // w hibernate.cfg.xml zmienic na create
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
            Reservation reservation = ReservationFactory.buildReservation(scanner, session);


        } else if (value.equals("2")){  // OBSŁUGA SYSTEMU PRZEZ PRACOWNIKA
            SystemService systemService = new SystemService();
            System.out.println("Podaj hasło");
            String haslo = scanner.nextLine();
            if (haslo.equals(systemService.getPassword())){
                systemService.showOptions();
            }
            value = scanner.nextLine();


            switch (value) {
                case "1":
                    {Hotel hotel = HotelFactory.buildHotel();
                    Transaction tx = session.beginTransaction();
                    session.save(hotel);
                    tx.commit();
                    break;}
                case "2":
                    {Room room = RoomFactory.buildRoom(session);
                    Transaction tx = session.beginTransaction();
                    session.save(room);
                    tx.commit();
                    break;}
                case "3":
                    Reservation reservation = ReservationFactory.buildReservation(scanner, session);
                case "4":
                    ReservationService.printAllReservations(session);
                    System.out.println("Podaj ID rezerwacji do usunięcia:");

                    String id = scanner.nextLine();
//                    boolean x = ReservationRepository.getAllReservations(session).stream().filter(e -> e.getId() == Short.valueOf(id)).findFirst().equals(Optional.empty());

                    while (!ReservationService.checkReservationId(id, session)) {
                        System.out.println("Podano niewłaściwy id rezerwacji. \nPodaj ponownie ID rezerwacji");
                        id = scanner.nextLine();
                    }
                    ReservationRepository.deleteReservation(session, Short.valueOf(id));
                }


        } else {
            System.out.println("\n\n                   KONIEC");
        }

        //List<HotelFacilities> fc = Arrays.asList(HotelFacilities.PARKING, HotelFacilities.RESTAURANT);

}

    public static void checkRoomReservations(Session session){
        List<Reservation> reservations = ReservationRepository.getAllReservations(session);

        for (Reservation reservation: reservations) {
            if (reservation.getDateTo().isBefore(LocalDate.now())) {
                ReservationRepository.deleteReservation(session, reservation.getId());
            }
        }
    }
}
