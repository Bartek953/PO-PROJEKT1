package pl.projekt.zdarzenia;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;

public abstract class Zdarzenie {
    private static long aktualnaKolejnosc;
    protected Priorytet priorytet;

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

    public Czas czas(){
        return czas;
    }

    public abstract Zdarzenie[] wykonaj();

    public static boolean wczesniejsze(Zdarzenie zdarzenie1, Zdarzenie zdarzenie2){
        if (zdarzenie1 == null || zdarzenie2 == null){
            throw new RuntimeException();
        }
        else if (Czas.mniejszy(zdarzenie1.czas(), zdarzenie2.czas())){
            return true;
        }
        else if (Czas.mniejszy(zdarzenie2.czas(), zdarzenie1.czas())){
            return false;
        }
        else if (zdarzenie1.priorytet() != zdarzenie2.priorytet()){
            return zdarzenie1.priorytet() > zdarzenie2.priorytet();
        }
        else {
            return zdarzenie1.kolejnosc() < zdarzenie2.kolejnosc();
        }
    }
}
