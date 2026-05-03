package pl.projekt.zdarzenia;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;

public abstract class Zdarzenie {
    private static long AKTUALNA_KOLEJNOSC;

    private Czas czas;
    private long kolejnosc;

    public Zdarzenie(Czas czas){
        this.czas = czas;
        this.kolejnosc = AKTUALNA_KOLEJNOSC;
        AKTUALNA_KOLEJNOSC++;
    }
    public long kolejnosc(){
        return kolejnosc;
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
        else {
            return zdarzenie1.kolejnosc() < zdarzenie2.kolejnosc();
        }
    }
}
