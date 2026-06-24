package pl.projekt.osrodek;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Zdarzenie;

public abstract class Polaczenie {
    private final int numer;
    private final Wezel start, koniec;
    private final Czas czasPrzejazdu;
    private int liczbaPrzejazdow;

    public Polaczenie(int numer, Wezel start, Wezel koniec, int czasPrzejazdu){
        this.numer = numer;
        this.start = start;
        this.koniec = koniec;
        this.czasPrzejazdu = new Czas(0, 0, czasPrzejazdu);
        liczbaPrzejazdow = 0;
    }
    public int numer(){
        return numer;
    }
    public Wezel start(){
        return start;
    }
    public Wezel koniec(){
        return koniec;
    }
    public Czas czasPrzejazdu(){
        return czasPrzejazdu;
    }
    public int liczbaPrzejazdow(){
        return liczbaPrzejazdow;
    }
    public void zwiekszLiczbePrzejazdow(){
        liczbaPrzejazdow++;
    }
    public abstract String statystyki();

    public abstract Zdarzenie stworzZdarzenie(Czas czas, Sportowiec sportowiec);

}
