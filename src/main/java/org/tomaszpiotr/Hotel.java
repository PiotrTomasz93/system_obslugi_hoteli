package org.tomaszpiotr;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    //@Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    private String name;
    private String street;
    private String postalCode;
    private String city;
    private StarRating starRating;

    @ManyToMany
    private List<Facilities> facilities = new ArrayList<>();

    @ManyToMany
    private List<Room> rooms = new ArrayList<>();


    public int obliczLiczbeMiejsc(){ //wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie miejsc
        return 0;
    }

    public int obliczLiczbeWolnychMiejsc(){ //wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie wolnych miejsc
        return 0;
    }

}
