package org.tomaszpiotr;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    @ManyToOne
    private Hotel hotel;
    //private short hotelId;
    private short numberOfBeds;
    private boolean isFree;
//    private List<RoomFacilities> roomFacilities;
    private double price;





}
