package org.tomaszpiotr;

public class SystemService {
    private final String password = "abc123";

    public String getPassword() {
        return password;
    }

    public void showOptions(){
        System.out.println("1. Wyświetl liczbę wolnych miejsc w hotelach");
        System.out.println("2. Dodaj hotel");
        System.out.println("3. Dodaj pokój");
        System.out.println("4. Dodaj rezerwacje");
    }
}
