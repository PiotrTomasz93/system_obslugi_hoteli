package org.tomaszpiotr;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    @Column(unique = true)
    private String name;
    private String street;
    private String postalCode;
    private String city;
    private StarRating starRating;
/*    @ManyToMany
    private List<HotelFacilities> hotelFacilities = new ArrayList<>();*/
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<Room>();

    public Hotel(String name, String street, String postalCode, String city, StarRating starRating, List<Room> rooms) {
        this.name = name;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.starRating = starRating;
//        this.hotelFacilities = hotelFacilities;
        this.rooms = rooms;
    }

    public int obliczLiczbeMiejsc(){ //TODO wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie miejsc
        return 0;
    }

    public int obliczLiczbeWolnychMiejsc(){ //TODO wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie wolnych miejsc
        return 0;
    }

}
