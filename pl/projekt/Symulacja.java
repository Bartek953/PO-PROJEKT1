package pl.projekt;

import kadra.mapki.pliki.WyjatekSystemuPlikow;
import pl.projekt.cechy.Czas;
import pl.projekt.mapki.TworcaMapek;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.strukturydanych.KolejkaZdarzen;
import pl.projekt.strukturydanych.StertowaKolejkaZdarzen;
import pl.projekt.wczytywanie.Przetwarzacz;
import pl.projekt.wczytywanie.SkanerSymulacji;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieDecyzyjne;
import pl.projekt.zdarzenia.wyciag.ZdarzeniePrzyjazdWyciagu;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class Symulacja {
    private KolejkaZdarzen kolejkaZdarzen;
    private Wezel[] listaWezlow;
    private Wyciag[] listaWyciagow;
    private Trasa[] listaTras;
    private Sportowiec[] listaSportowcow;
    private final Czas czasOtwarcia;
    private final Czas czasZamkniecia;

    public Symulacja(){
        this.kolejkaZdarzen = new StertowaKolejkaZdarzen();
        this.czasOtwarcia = new Czas(9, 0, 0);
        this.czasZamkniecia = new Czas(15, 0, 0);
        Zdarzenie.resetujKolejnosc();
    }

    private void wczytaj(){
        SkanerSymulacji skaner = new SkanerSymulacji();
        Przetwarzacz przetwarzacz = new Przetwarzacz();

        przetwarzacz.przetworz(skaner.wczytajWezly());
        przetwarzacz.przetworz(skaner.wczytajWyciagi());
        przetwarzacz.przetworz(skaner.wczytajTrasy());
        przetwarzacz.przetworz(skaner.wczytajGrupySportowcow());

        listaWezlow = przetwarzacz.dajWezly();
        listaWyciagow = przetwarzacz.dajWyciagi();
        listaTras = przetwarzacz.dajTrasy();
        listaSportowcow = przetwarzacz.dajSportowcow();
    }

    // Przygotowuje kolejkę zdarzeń.
    // W aktualnej implementacji wymaga to zainicjalizowania
    // pojawienia się sportowców na stoku
    // (Zdarzenie decyzyjne w węźle i czasie podanym na wejściu)
    // oraz zainicjalizowania działania wyciągów
    // (Trzeba ustawić pierwszą turę przyjazdów).
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
            // Weź najmniejszy i usuń go z kolejki.

            List<Zdarzenie> listaKolejnych = zdarzenie.wykonaj();

            if (listaKolejnych == null){
                continue;
            }

            for (Zdarzenie kolejne : listaKolejnych){
                // Zdarzenie jest dodawane, jeśli wykona się przed 15:00,
                // nie dotyczy to jednak niektórych zdarzeń, które są
                // końcem jakiejś czynności (np koniec trasy, koniec wjazdu).
                if (kolejne != null && (Czas.mniejszy(kolejne.czas(), czasZamkniecia)
                        || kolejne.wykonajPoZamknieciu())){
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
    public void tworzMapki(String folder) throws WyjatekSystemuPlikow {
        TworcaMapek tworcaMapek = new TworcaMapek(folder);
        tworcaMapek.tworzMapkeParametrow(listaWezlow, listaTras, listaWyciagow);
        tworcaMapek.tworzMapkeStatystyk(listaWezlow, listaTras, listaWyciagow);
        for (Sportowiec sportowiec : listaSportowcow){
            if (sportowiec.sledzony()){
                tworcaMapek.tworzMapeSportowca(sportowiec, listaWezlow, listaTras, listaWyciagow);
            }
        }
    }
}
