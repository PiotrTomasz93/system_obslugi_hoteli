package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {

        Configuration con = new Configuration().configure().addAnnotatedClass(Hotel.class).addAnnotatedClass(Room.class);

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


        


//        DataLoader dataLoader = new DataLoader(session, tx);
//        dataLoader.loadData();

        

        System.out.println("\n\n                   KONIEC");
    }
}
