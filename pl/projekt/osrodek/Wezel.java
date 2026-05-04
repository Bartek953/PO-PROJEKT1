package pl.projekt.osrodek;

import pl.projekt.strukturydanych.ListaPolaczen;

public class Wezel {
    private int numer;
    private int wysokosc;
    private Wspolrzedne wspolrzedne;
    private boolean skomunikowany;
    private ListaPolaczen trasy;
    private ListaPolaczen wyciagi;

    public Wezel(int numer, int wysokosc, Wspolrzedne wspolrzedne, boolean skomunikowany){
        this.numer = numer;
        this.wysokosc = wysokosc;
        this.wspolrzedne = wspolrzedne;
        this.skomunikowany = skomunikowany;
        this.trasy = new ListaPolaczen();
        this.wyciagi = new ListaPolaczen();
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
    public ListaPolaczen trasy(){
        return trasy;
    }
    public ListaPolaczen wyciagi(){
        return wyciagi;
    }
}
