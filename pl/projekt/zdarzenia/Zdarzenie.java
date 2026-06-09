package pl.projekt.zdarzenia;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;
import java.util.List;

public abstract class Zdarzenie implements Comparable<Zdarzenie> {
    private static long aktualnaKolejnosc;
    private final Priorytet priorytet;

    private Czas czas;
    private long kolejnosc;

    public Zdarzenie(Czas czas, Priorytet priorytet){
        this.czas = czas;
        this.kolejnosc = aktualnaKolejnosc;
        aktualnaKolejnosc++;
        this.priorytet = priorytet;
    }
    public static void resetujKolejnosc(){
        aktualnaKolejnosc = 0;
    }
    public long kolejnosc(){
        return kolejnosc;
    }
    public int priorytet(){
        return priorytet.priorytet();
    }

    public boolean wykonajPoZamknieciu(){
        return false;
    }

    public Czas czas(){
        return czas;
    }

    public abstract List<Zdarzenie> wykonaj();

    @Override
    public int compareTo(Zdarzenie inneZdarzenie){
        if (inneZdarzenie == null){
            throw new NullPointerException("Zdarzenie nie może być null");
        }

        if (Czas.mniejszy(this.czas(), inneZdarzenie.czas())){
            return -1;
        }
        else if (Czas.mniejszy(inneZdarzenie.czas(), this.czas())){
            return 1;
        }

        if (this.priorytet() != inneZdarzenie.priorytet()){
            return this.priorytet() > inneZdarzenie.priorytet() ? -1 : 1;
        }

        if (this.kolejnosc() != inneZdarzenie.kolejnosc()){
            return this.kolejnosc() < inneZdarzenie.kolejnosc() ? -1 : 1;
        }

        return 0;
    }
}
