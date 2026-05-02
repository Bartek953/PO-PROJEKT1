package pl.projekt.osrodek;

import pl.projekt.strukturydanych.ListaPolaczen;

public class Wezel {
    private static int AKTUALNY_NUMER;
    private int numer;
    private int wysokosc;
    private Wspolrzedne wspolrzedne;
    private boolean skomunikowany;
    private ListaPolaczen trasy;
    private ListaPolaczen wyciagi;

    public Wezel(int wysokosc, Wspolrzedne wspolrzedne, boolean skomunikowany){
        this.numer = AKTUALNY_NUMER++;
        this.wysokosc = wysokosc;
        this.wspolrzedne = wspolrzedne;
        this.skomunikowany = skomunikowany;
        this.trasy = new ListaPolaczen();
        this.wyciagi = new ListaPolaczen();
    }
    public Wezel(int wysokosc, int x, int y, boolean skomunikowany){
        this(wysokosc, new Wspolrzedne(x, y), skomunikowany);
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
