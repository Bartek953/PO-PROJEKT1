package pl.projekt.osrodek;

import java.util.List;
import java.util.ArrayList;

public class Wezel {
    private int numer;
    private int wysokosc;
    private Wspolrzedne wspolrzedne;
    private boolean skomunikowany;
    private List<Trasa> trasy;
    private List<Wyciag> wyciagi;

    public Wezel(int numer, int wysokosc, Wspolrzedne wspolrzedne, boolean skomunikowany){
        this.numer = numer;
        this.wysokosc = wysokosc;
        this.wspolrzedne = wspolrzedne;
        this.skomunikowany = skomunikowany;
        this.trasy = new ArrayList<>();
        this.wyciagi = new ArrayList<>();
    }
    public Wezel(int numer, int wysokosc, int x, int y, boolean skomunikowany){
        this(numer, wysokosc, new Wspolrzedne(x, y), skomunikowany);
    }

    public void dodajTrase(Trasa trasa){
        trasy.add(trasa);
    }
    public void dodajWyciag(Wyciag wyciag){
        wyciagi.add(wyciag);
    }
    public int numer(){
        return numer;
    }
    public int wysokosc(){
        return wysokosc;
    }
    public List<Trasa> trasy(){
        return trasy;
    }
    public List<Wyciag> wyciagi(){
        return wyciagi;
    }
    public Wspolrzedne wspolrzedne(){
        return wspolrzedne;
    }
    public boolean skomunikowany(){
        return skomunikowany;
    }
}
