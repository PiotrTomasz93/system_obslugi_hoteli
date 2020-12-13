package org.tomaszpiotr;

import org.hibernate.Session;

import java.util.List;

public class ReservationService {

    public static void printAllReservations(Session session){
        List<Reservation> reservations = ReservationRepository.getAllReservations(session);
        for (Reservation item: reservations){
            System.out.println(item.getId() + ". Pokój nr " + item.getRoom().getId() + " od " + item.getDateFrom() + " do " + item.getDateTo() + ", ilosc osób: " + item.getNumberOfPeople());
        }

    }
}
