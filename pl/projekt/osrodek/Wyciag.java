package pl.projekt.osrodek;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.strukturydanych.ListowaKolejkaOczekujacych;

public class Wyciag extends Polaczenie{
    private static int AKTUALNY_NUMER;
    private Czas odstepCzasu;
    private int maksymalnaLiczbaOsob;
    private ListowaKolejkaOczekujacych kolejkaOczekujacych;

    private static Wezel dajStart(Wezel w1, Wezel w2){
        return w1.wysokosc() < w2.wysokosc() ? w1 : w2;
    }
    private static Wezel dajKoniec(Wezel w1, Wezel w2){
        return w1.wysokosc() > w2.wysokosc() ? w1 : w2;
    }

    public Wyciag(Wezel wezel1, Wezel wezel2, int czasPrzejazdu, int odstepCzasu, int maksymalnaLiczbaOsob){
        super(AKTUALNY_NUMER, TypPolaczenia.WYCIAG, dajStart(wezel1, wezel2), dajKoniec(wezel1, wezel2), czasPrzejazdu);
        AKTUALNY_NUMER++;

        this.odstepCzasu = new Czas(0, 0, odstepCzasu);
        this.maksymalnaLiczbaOsob = maksymalnaLiczbaOsob;
        kolejkaOczekujacych = new ListowaKolejkaOczekujacych();

        start().dodajWyciag(this);
    }

    public void dodajDoKolejki(Sportowiec sportowiec){
        kolejkaOczekujacych.dodajNaKoniec(sportowiec);
    }
    public Sportowiec pierwszyWKolejce(){
        return kolejkaOczekujacych.pierwszy();
    }
    public void usunPierwszegoZKolejki(){
        kolejkaOczekujacych.usunPierwszy();
    }
    public boolean kolejkaPusta(){
        return kolejkaOczekujacych.pusta();
    }
    public int maksymalnaLiczbaOsob(){
        return maksymalnaLiczbaOsob;
    }
    public Czas odstepCzasu(){
        return odstepCzasu;
    }
}
