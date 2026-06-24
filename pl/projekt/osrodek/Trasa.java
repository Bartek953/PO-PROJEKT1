package pl.projekt.osrodek;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzeniePoczatekTrasy;

public class Trasa extends Polaczenie {
    private final int poziomTrudnosci; // 0-10
    private final double odpornosc; // (0, 1]
    private final double bazowaAtrakcyjnosc; // [0, 1]

    public Trasa(int numer, Wezel wezel1, Wezel wezel2, int czasPrzejazdu, int poziomTrudnosci, double odpornosc, double bazowaAtrakcyjnosc){
        super(numer, wezel1, wezel2, czasPrzejazdu);

        this.poziomTrudnosci = poziomTrudnosci;
        this.odpornosc = odpornosc;
        this.bazowaAtrakcyjnosc = bazowaAtrakcyjnosc;

        start().dodajTrase(this);
    }
    public int poziomTrudnosci(){
        return poziomTrudnosci;
    }
    public double bazowaAtrakcyjnosc(){
        return bazowaAtrakcyjnosc;
    }
    public double odpornosc(){
        return odpornosc;
    }

    // [0, 1]
    public double atrakcyjnoscNawierzchni(){
        return bazowaAtrakcyjnosc + (1 - bazowaAtrakcyjnosc) * Math.pow(odpornosc, liczbaPrzejazdow());
    }

    @Override
    public String statystyki(){
        return String.format("Statystyki trasy nr %d:\n" +
                        "1) Łączna liczba zjazdów: %d\n" +
                        "2) Wyrównanie nawierzchni na koniec dnia to: %.2f\n",
                numer(), liczbaPrzejazdow(), atrakcyjnoscNawierzchni());
    }

    @Override
    public Zdarzenie stworzZdarzenie(Czas czas, Sportowiec sportowiec){
        zwiekszLiczbePrzejazdow();
        return new ZdarzeniePoczatekTrasy(czas, sportowiec, this);
    }

}
