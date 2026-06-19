package pl.projekt.osrodek;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieUstawienieWKolejce;

import java.util.LinkedList;
import java.util.Queue;

public class Wyciag extends Polaczenie{
    private Czas odstepCzasu;
    private int maksymalnaLiczbaOsob;
    private Queue<Sportowiec> kolejkaOczekujacych;
    private int maksymalnaDlugoscKolejki;
    private long potencjalLiczbyPrzewiezionych;
    private Czas ostatniaAktualizacjaKolejki;
    private long sekundyRazyOsobyWKolejce;

    private static final Czas czasOtwarcia = new Czas(9, 0, 0);
    private static final Czas czasZamkniecia = new Czas(15, 0, 0);

    public Wyciag(int numer, Wezel wezel1, Wezel wezel2, int czasPrzejazdu, int odstepCzasu, int maksymalnaLiczbaOsob){
        super(numer, wezel1, wezel2, czasPrzejazdu);

        this.odstepCzasu = new Czas(0, 0, odstepCzasu);
        this.maksymalnaLiczbaOsob = maksymalnaLiczbaOsob;
        kolejkaOczekujacych = new LinkedList<>();
        maksymalnaDlugoscKolejki = 0;
        potencjalLiczbyPrzewiezionych = 0;
        ostatniaAktualizacjaKolejki = czasOtwarcia;
        sekundyRazyOsobyWKolejce = 0;

        start().dodajWyciag(this);
    }

    private void aktualizujSrednia(Czas czas){
        long interwal = Czas.roznica(czas, ostatniaAktualizacjaKolejki);
        sekundyRazyOsobyWKolejce += kolejkaOczekujacych.size() * interwal;
        ostatniaAktualizacjaKolejki = czas;
    }

    public void dodajDoKolejki(Czas czas, Sportowiec sportowiec){
        aktualizujSrednia(czas);

        kolejkaOczekujacych.offer(sportowiec);
        maksymalnaDlugoscKolejki = Math.max(maksymalnaDlugoscKolejki, kolejkaOczekujacych.size());
    }
    public Sportowiec pierwszyWKolejce(){
        return kolejkaOczekujacych.peek();
    }
    public void usunPierwszegoZKolejki(Czas czas){
        aktualizujSrednia(czas);

        kolejkaOczekujacych.poll();
    }
    public boolean kolejkaPusta(){
        return kolejkaOczekujacych.isEmpty();
    }
    public int aktualnaDlugoscKolejki(){
        return kolejkaOczekujacych.size();
    }
    public int maksymalnaLiczbaOsob(){
        return maksymalnaLiczbaOsob;
    }
    public Czas odstepCzasu(){
        return odstepCzasu;
    }

    public long potencjalLiczbyPrzewiezionych(){
        return potencjalLiczbyPrzewiezionych;
    }

    public void zwiekszPotencjalLiczbyPrzewiezionych(){
        potencjalLiczbyPrzewiezionych += maksymalnaLiczbaOsob();
    }

    public int maksymalnaDlugoscKolejki(){
        return maksymalnaDlugoscKolejki;
    }
    public double sredniaDlugoscKolejki(){
        long interwal = Czas.roznica(czasZamkniecia, czasOtwarcia);
        return (double)sekundyRazyOsobyWKolejce / (double)interwal;
    }

    public int procentZajetychMiejsc(){
        return (int)(100L * liczbaPrzejazdow() / potencjalLiczbyPrzewiezionych);
    }

    @Override
    public String statystyki(){
        aktualizujSrednia(czasZamkniecia);
        return String.format("Statystyki wyciągu nr %d:\n" +
                        "1) Maksymalna długość kolejki: %d\n" +
                        "2) Średnia długość kolejki: %.2f\n" +
                        "3) Łączna liczba przewiezionych pasażerów: %d\n" +
                        "4) Procent zajętych miejsc: %d %% \n",
                numer(), maksymalnaDlugoscKolejki(), sredniaDlugoscKolejki(),
                liczbaPrzejazdow(), procentZajetychMiejsc());
    }

    @Override
    public Zdarzenie stworzZdarzenie(Czas czas, Sportowiec sportowiec){
        return new ZdarzenieUstawienieWKolejce(czas, sportowiec, this);
    }
}
