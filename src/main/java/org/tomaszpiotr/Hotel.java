package org.tomaszpiotr;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(unique = true)
    private String name;
    private String street;
    private String postalCode;
    private String city;
    private StarRating starRating;
/*    @ManyToMany
    private List<HotelFacilities> hotelFacilities = new ArrayList<>();*/
    @OneToMany
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

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public StarRating getStarRating() {
        return starRating;
    }

    public void setStarRating(StarRating starRating) {
        this.starRating = starRating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", starRating=" + starRating +
                ", rooms=" + rooms +
                '}';
    }

    public int obliczLiczbeMiejsc(){ //TODO wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie miejsc
        return 0;
    }

    public int obliczLiczbeWolnychMiejsc(){ //TODO wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie wolnych miejsc
        return 0;
    }

}
