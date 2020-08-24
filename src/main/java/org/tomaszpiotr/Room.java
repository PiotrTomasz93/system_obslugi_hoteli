package org.tomaszpiotr;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @ManyToOne
    private Hotel hotel;
    private int numberOfBeds;
    private boolean isFree;
//    private List<RoomFacilities> roomFacilities;
    private double price;

    public Room(){

    }

    public Room(short id, Hotel hotel, int numberOfBeds, boolean isFree, double price) {

        this.id = id;
        this.hotel = hotel;
        this.numberOfBeds = numberOfBeds;
        this.isFree = isFree;
        this.price = price;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", Number of beds=" + numberOfBeds +
                ", Price per person=" + price +
                '}';
    }
}
