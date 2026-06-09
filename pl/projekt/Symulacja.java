package pl.projekt;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.wczytywanie.Przetwarzacz;
import pl.projekt.wczytywanie.SkanerSymulacji;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieDecyzyjne;
import pl.projekt.zdarzenia.wyciag.ZdarzeniePrzyjazdWyciagu;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class Symulacja {
    private Queue<Zdarzenie> kolejkaZdarzen;
    private Wyciag[] listaWyciagow;
    private Trasa[] listaTras;
    private Sportowiec[] listaSportowcow;
    private final Czas czasOtwarcia;
    private final Czas czasZamkniecia;

    public Symulacja(){
        this.kolejkaZdarzen = new PriorityQueue<>();
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
            kolejkaZdarzen.offer(new ZdarzenieDecyzyjne(
                    sportowiec.czasStartu(), sportowiec, sportowiec.wezelStartowy()));
        }
        for (Wyciag wyciag : listaWyciagow){
            kolejkaZdarzen.offer(new ZdarzeniePrzyjazdWyciagu(czasOtwarcia, wyciag));
        }
    }

    public void symuluj(){
        wczytaj();
        inicjalizujKolejke();

        while (!kolejkaZdarzen.isEmpty()){
            Zdarzenie zdarzenie = kolejkaZdarzen.poll();
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
                    kolejkaZdarzen.offer(kolejne);
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
