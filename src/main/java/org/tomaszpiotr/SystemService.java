package org.tomaszpiotr;

public class SystemService {
    private final String password = "123";

    public String getPassword() {
        return password;
    }

    public void showOptions(){
        System.out.println("1. Dodaj hotel");
        System.out.println("2. Dodaj pokój");
        System.out.println("3. Dodaj rezerwacje");
    }




}
