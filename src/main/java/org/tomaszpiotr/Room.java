package org.tomaszpiotr;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    private short hotelId;
    private short numberOfBeds;
    private boolean isFree;
    @ManyToMany
    private List<RoomFacilities> roomFacilities;
    private double price;





}
