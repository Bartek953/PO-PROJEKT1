package pl.projekt.sportowcy;

import java.util.Random;

public class Sportowiec {
    private static int AKTUALNY_NUMER;
    private static Random generator;

    private int numer;
    private int poziomZaawansowania; //0-10
    private double wspSpontanicznosci;
    private Wagi wagi;
    private boolean sledzony;

    public Sportowiec(int poziomZaawansowania, double wspSpontanicznosci, Wagi wagi, boolean sledzony){
        if (generator == null){
            generator = new Random();
        }
        numer = AKTUALNY_NUMER;
        AKTUALNY_NUMER++;
        this.poziomZaawansowania = poziomZaawansowania;
        this.wspSpontanicznosci = wspSpontanicznosci;
        this.wagi = wagi;
        this.sledzony = sledzony;
    }
    public Sportowiec(int poziomZaawansowania, double wspSpontanicznosci, double wagaTrudnosci, double wagaNawierzchni, boolean sledzony){
        this(poziomZaawansowania, wspSpontanicznosci, new Wagi(wagaTrudnosci, wagaNawierzchni), sledzony);
    }

}
