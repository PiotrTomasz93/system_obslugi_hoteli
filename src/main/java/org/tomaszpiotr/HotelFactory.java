package org.tomaszpiotr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelFactory {

    public static Hotel buildHotel(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz nazwę");
        String name = scanner.nextLine();

        System.out.println("Wpisz ulicę i numer");
        String street = scanner.nextLine();

        System.out.println("Wpisz kod pocztowy");
        String postalCode = scanner.nextLine();

        System.out.println("Wpisz miejscowość");
        String city = scanner.nextLine();

        System.out.println("Wpisz ilosc gwiazdek");
        String starRating = scanner.nextLine();
        StarRating starRating1 = StarRating.values()[Integer.valueOf(starRating) - 1]; //stworzona tablica enumów i wzięta z niej odpowiednia wartość, skastowana ze Stringa na integer - 1


        List<Room> roomList = new ArrayList<>();
        Hotel hotel = new Hotel(name, street, postalCode, city, starRating1, roomList);

        return hotel;
    }
}
