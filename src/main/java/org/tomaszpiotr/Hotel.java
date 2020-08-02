package org.tomaszpiotr;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private short id;
    private String nazwa;
    private String ulica;
    private String kodPocztowy;
    private String miasto;
    private Gwiazdki liczbaGwiazdek;
    private List<Udogodnienia> udogodnienia = new ArrayList<>();


    public int obliczLiczbeMiejsc(){ //wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie miejsc
        return 0;
    }

    public int obliczLiczbeWolnychMiejsc(){ //wyciągnięcie z bazy wszystkich pokoi z danego hotelu i zsumowanie wolnych miejsc
        return 0;
    }

}
