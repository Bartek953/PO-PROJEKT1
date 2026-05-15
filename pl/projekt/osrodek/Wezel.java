package pl.projekt.osrodek;

import pl.projekt.strukturydanych.ListaTras;
import pl.projekt.strukturydanych.ListaWyciagow;

public class Wezel {
    private int numer;
    private int wysokosc;
    private Wspolrzedne wspolrzedne;
    private boolean skomunikowany;
    private ListaTras trasy;
    private ListaWyciagow wyciagi;

    public Wezel(int numer, int wysokosc, Wspolrzedne wspolrzedne, boolean skomunikowany){
        this.numer = numer;
        this.wysokosc = wysokosc;
        this.wspolrzedne = wspolrzedne;
        this.skomunikowany = skomunikowany;
        this.trasy = new ListaTras();
        this.wyciagi = new ListaWyciagow();
    }
    public Wezel(int numer, int wysokosc, int x, int y, boolean skomunikowany){
        this(numer, wysokosc, new Wspolrzedne(x, y), skomunikowany);
    }

    public void dodajTrase(Trasa trasa){
        trasy.dodaj(trasa);
    }
    public void dodajWyciag(Wyciag wyciag){
        wyciagi.dodaj(wyciag);
    }
    public int numer(){
        return numer;
    }
    public int wysokosc(){
        return wysokosc;
    }
    public ListaTras trasy(){
        return trasy;
    }
    public ListaWyciagow wyciagi(){
        return wyciagi;
    }
}
