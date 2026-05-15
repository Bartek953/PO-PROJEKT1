package pl.projekt;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.strukturydanych.StertowaKolejkaZdarzen;
import pl.projekt.wczytywanie.Przetwarzacz;
import pl.projekt.wczytywanie.SkanerSymulacji;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieDecyzyjne;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieKoniecTrasy;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieKoniecWjazdu;
import pl.projekt.zdarzenia.wyciag.ZdarzeniePrzyjazdWyciagu;

public class Symulacja {
    private StertowaKolejkaZdarzen kolejkaZdarzen;
    private Wyciag[] listaWyciagow;
    private Trasa[] listaTras;
    private Sportowiec[] listaSportowcow;
    private final Czas czasOtwarcia;
    private final Czas czasZamkniecia;

    public Symulacja(){
        this.kolejkaZdarzen = new StertowaKolejkaZdarzen();
        this.czasOtwarcia = new Czas(9, 0, 0);
        this.czasZamkniecia = new Czas(15, 0, 0);
    }

    private void wczytaj(){
        SkanerSymulacji skaner = new SkanerSymulacji();
        Przetwarzacz przetwarzacz = new Przetwarzacz();

        przetwarzacz.przetworz(skaner.wczytajWezly());
        przetwarzacz.przetworz(skaner.wczytajWyciagi());
        przetwarzacz.przetworz(skaner.wczytajTrasy());
        przetwarzacz.przetworz(skaner.wczytajGrupySportowcow());

        listaWyciagow = przetwarzacz.dajWyciagi();
        listaTras = przetwarzacz.dajTrasy();
        listaSportowcow = przetwarzacz.dajSportowcow();
    }

    private void inicjalizujKolejke(){
        for (Sportowiec sportowiec : listaSportowcow){
            kolejkaZdarzen.dodaj(new ZdarzenieDecyzyjne(
                    sportowiec.czasStartu(), sportowiec, sportowiec.wezelStartowy()));
        }
        for (Wyciag wyciag : listaWyciagow){
            kolejkaZdarzen.dodaj(new ZdarzeniePrzyjazdWyciagu(czasOtwarcia, wyciag));
        }
    }

    public void symuluj(){
        wczytaj();
        inicjalizujKolejke();

        while (!kolejkaZdarzen.pusta()){
            Zdarzenie zdarzenie = kolejkaZdarzen.dajNajmniejszy();
            kolejkaZdarzen.usunNajmniejszy();

            Zdarzenie[] listaKolejnych = zdarzenie.wykonaj();

            for (Zdarzenie kolejne : listaKolejnych){
                if (kolejne != null && (Czas.mniejszy(kolejne.czas(), czasZamkniecia)
                        || kolejne instanceof ZdarzenieKoniecTrasy
                        || kolejne instanceof ZdarzenieKoniecWjazdu)){
                    kolejkaZdarzen.dodaj(kolejne);
                }
            }
        }

        System.out.println("\nStatystyki wyciagow");
        for (Wyciag wyciag : listaWyciagow){
            System.out.println(wyciag.statystyki());
        }

        System.out.println("\nStatystyki tras");
        for (Trasa trasa : listaTras){
            System.out.println(trasa.statystyki());
        }
    }
}
