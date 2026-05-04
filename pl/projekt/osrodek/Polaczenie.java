package pl.projekt.osrodek;

import pl.projekt.cechy.Czas;

public abstract class Polaczenie {
    private int numer;
    private TypPolaczenia typPolaczenia;
    private Wezel start, koniec;
    private Czas czasPrzejazdu;
    private int liczbaPrzejazdow;

    public Polaczenie(int numer, TypPolaczenia typPolaczenia, Wezel start, Wezel koniec, int czasPrzejazdu){
        this.numer = numer;
        this.typPolaczenia = typPolaczenia;
        this.start = start;
        this.koniec = koniec;
        this.czasPrzejazdu = new Czas(0, 0, czasPrzejazdu);
        liczbaPrzejazdow = 0;
    }
    public int numer(){
        return numer;
    }
    public TypPolaczenia typPolaczenia(){
        return typPolaczenia;
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

}
