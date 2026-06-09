package pl.projekt.osrodek;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;

import java.util.LinkedList;
import java.util.Queue;

public class Wyciag extends Polaczenie{
    private Czas odstepCzasu;
    private int maksymalnaLiczbaOsob;
    private Queue<Sportowiec> kolejkaOczekujacych;

    public Wyciag(int numer, Wezel wezel1, Wezel wezel2, int czasPrzejazdu, int odstepCzasu, int maksymalnaLiczbaOsob){
        super(numer, wezel1, wezel2, czasPrzejazdu);

        this.odstepCzasu = new Czas(0, 0, odstepCzasu);
        this.maksymalnaLiczbaOsob = maksymalnaLiczbaOsob;
        kolejkaOczekujacych = new LinkedList<>();

        start().dodajWyciag(this);
    }

    public void dodajDoKolejki(Sportowiec sportowiec){
        kolejkaOczekujacych.offer(sportowiec);
    }
    public Sportowiec pierwszyWKolejce(){
        return kolejkaOczekujacych.peek();
    }
    public void usunPierwszegoZKolejki(){
        kolejkaOczekujacych.poll();
    }
    public boolean kolejkaPusta(){
        return kolejkaOczekujacych.isEmpty();
    }
    public int maksymalnaLiczbaOsob(){
        return maksymalnaLiczbaOsob;
    }
    public Czas odstepCzasu(){
        return odstepCzasu;
    }

    @Override
    public String statystyki(){
        return String.format("Liczba wjazdów wyciągiem %d to %d", numer(), liczbaPrzejazdow());
    }
}
