package pl.projekt.sportowcy;

import pl.projekt.osrodek.Trasa;

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
    public int numer(){
        return numer;
    }
    public int poziomZaawansowania(){
        return poziomZaawansowania;
    }

    // [0, 1]
    private double dopasowanieTrudnosci(Trasa trasa){
        if (trasa.poziomTrudnosci() >= poziomZaawansowania() + 5){
            return 0;
        }
        else if (poziomZaawansowania() > trasa.poziomTrudnosci()){
            return Math.max((double)0.2, (1.0 - (double)(poziomZaawansowania() - trasa.poziomTrudnosci()) / 7.0));
        }
        else {
            return 1.0 - (double)(trasa.poziomTrudnosci() - poziomZaawansowania) / 5.0;
        }
    }

    // [0, 1]
    public double atrakcyjnoscTrasy(Trasa trasa){
        double atrakcyjnoscTrudnosci = dopasowanieTrudnosci(trasa);
        double atrakcyjnoscNawierzchni = trasa.atrakcyjnoscNawierzchni();

        return wagi.lacznaAtrakcyjnosc(atrakcyjnoscTrudnosci, atrakcyjnoscNawierzchni);
    }

}
