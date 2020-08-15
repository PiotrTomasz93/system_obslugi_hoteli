package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class ReservationFactory {


    public static Reservation buildReservation(Scanner scanner, Session session, Transaction tx) {
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
                numberOfPeople = Integer.valueOf(scanner.nextLine());
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

            tx = session.beginTransaction();
            session.save(reservation);
            tx.commit();

            System.out.println("Data zameldowania: " + dateFrom);
            System.out.println("Data wymeldowania: " + dateTo);


            System.out.println("Rezerwacja potwierdzona. Koszt noclegu wyniesie " + reservation.calculatePrice() + "zł.");


        } else {
            System.out.println("W hotelu " + hotelName + " nie ma wolnych miejsc.");
        }

        return reservation;

    }
}
