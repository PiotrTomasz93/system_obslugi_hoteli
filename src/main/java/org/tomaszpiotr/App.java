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

        System.out.println("Witamy w aplikacji HotelsView. \nWybierz opcję, wpisz odpowiedni numer i zatwierdź klawiszem Enter.");
        System.out.println("1. Rezerwacja pokoju");
        System.out.println("2. Zalogowanie się do systemu.");

        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();

        while (!value.equals("1") && !value.equals("2")){
            System.out.println("Podano nieprawidłową wartość. Wybierz ponownie");
            value = scanner.nextLine();
        }


        if (value.equals("1")) {
            Reservation reservation = new Reservation();

            System.out.println("Wybierz hotel, w którym chcesz zgłosić rezerwację (wpisz nazwę)");
            reservation.printHotels(session);
            String hotelName = scanner.nextLine();
            while (reservation.checkHotelName(hotelName, session) == false){
                System.out.println("Błędna nazwa hotelu. Wpisz ponownie");
                hotelName = scanner.nextLine();
            }
            if (reservation.checkFreeRooms(hotelName, session)){
                System.out.println("Wybierz pokój z obecnie dostępnych (wpisz id)");
                reservation.printFreeRooms(hotelName, session);
                String roomId = scanner.nextLine();
                while (!reservation.checkRoomId(hotelName, roomId, session)){
                    System.out.println("Ten pokój nie jest dostępny. Wpisz ponownie");
                    roomId = scanner.nextLine();
                }
                reservation.setRoom(roomId, session);
                System.out.println("Wybrano pokój: " + reservation.getRoom().toString());

                reservation.updateRoomAvailability(roomId, session);

                System.out.println("Podaj liczbę osób:");
                int numberOfPeople = Integer.valueOf(scanner.nextLine());
                while (numberOfPeople > session.load(Room.class, Short.valueOf(roomId)).getNumberOfBeds()){
                    System.out.println("Liczba osób przekracza liczbę miejsc w pokoju. Wpisz ponownie:");
                    numberOfPeople = scanner.nextInt();
                }
                reservation.setNumberOfPeople(numberOfPeople);
                System.out.println("Dzisiaj jest " + LocalDate.now());

                System.out.println("Podaj datę zameldowania: (w formacie YYYY-MM-DD)");
                String checkInText = scanner.nextLine();
                LocalDate dateFrom = LocalDate.parse(checkInText);

                System.out.println("Podaj datę wymeldowania: (w formacie YYYY-MM-DD)");
                String checkOutText = scanner.nextLine();
                LocalDate dateTo = LocalDate.parse(checkOutText);

                reservation.setDateFrom(dateFrom);
                reservation.setDateTo(dateTo);

                session.save(reservation);
                tx.commit();

                System.out.println("Data zameldowania: " + dateFrom);
                System.out.println("Data wymeldowania: " + dateTo);

                System.out.println("Rezerwacja potwierdzona. Koszt noclegu wyniesie "); //TODO obliczyć koszt noclegu


            } else {
                System.out.println("W hotelu " + hotelName + " nie ma wolnych miejsc.");
            }

        }




        else{
            SystemService systemService = new SystemService();
            System.out.println("Podaj hasło");
            String haslo = scanner.nextLine();
            if (haslo == systemService.getPassword()){
            }
        }
        //List<HotelFacilities> fc = Arrays.asList(HotelFacilities.PARKING, HotelFacilities.RESTAURANT);


        


//        DataLoader dataLoader = new DataLoader(session, tx); // w hibernate.cfg.xml zmienic na create
//        dataLoader.loadData();



        System.out.println("\n\n                   KONIEC");
    }

    public static void checkRoomReservations(){ //TODO sprawdzenie dat wszystkich rezerwacji i zaktualizowanie dostępności pokoi

    }
}
