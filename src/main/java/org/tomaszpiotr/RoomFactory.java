package org.tomaszpiotr;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomFactory {

    public static Room buildRoom(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz hotel, do którego należy przypisać pokój\n");
        HotelService.printHotels(session);
        String hotelName = scanner.nextLine();
        while (!HotelService.checkHotelName(hotelName, session)){
            System.out.println("Niepoprawna nazwa hotelu. Wpisz ponownie: ");
            hotelName = scanner.nextLine();
        }

        Hotel hotel = session.load(Hotel.class, hotelName); // TODO do poprawy, trzeba szukac po id a nie po nazwie hotelu

        System.out.println("Wpisz liczbę miejsc");
        String numberOfBeds = scanner.nextLine();

        System.out.println("Wpisz cenę za osobę");
        String price = scanner.nextLine();


        Room room = new Room();

        room.setHotel(hotel);
        room.setFree(true);
        room.setPrice(Integer.valueOf(price));
        room.setPrice(Double.valueOf(numberOfBeds));

        return room;
    }

}
