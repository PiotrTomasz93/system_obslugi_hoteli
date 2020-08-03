package org.tomaszpiotr;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    private String name;
    private String street;
    private String postalCode;
    private String city;
    private StarRating starRating;
    @ManyToMany
    private List<HotelFacilities> hotelFacilities = new ArrayList<>();
    @OneToMany
    private List<Room> rooms = new ArrayList<>();



    public int obliczLiczbeMiejsc(){ //TODO wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie miejsc
        return 0;
    }

    public int obliczLiczbeWolnychMiejsc(){ //TODO wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie wolnych miejsc
        return 0;
    }

}
