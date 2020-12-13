package org.tomaszpiotr;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationRepository {

    public static List<Reservation> getAllReservations(Session session){
        Query query = session.createQuery("from Reservation");
        List<Reservation> reservations = query.list();
        return reservations;
    }

    public static void deleteReservation(Session session, Short id, Transaction tx){
        Reservation reservation = session.load(Reservation.class, id);
        session.delete(reservation);
        tx.commit();
        reservation.updateRoomAvailability(Short.toString(reservation.getRoom().getId()), session);


    }
}
