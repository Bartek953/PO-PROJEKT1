package pl.projekt.sportowcy;

import pl.projekt.osrodek.Polaczenie;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;

import java.util.*;

// Klasa zapewnia metody przeszukiwania grafu dla sportowców planujących
// Wymaga implementacji porownaj(Trasa trasa1, int odl1, Trasa trasa2, int odl2)
// przez sportowcow planujacych
public class BFS {
    private int wezelDocelowy;
    private Polaczenie ostatniePolaczenie;

    private KomparatorTras komparator;
    private Wezel wezelStartowy;
    private Trasa najlepszaTrasa;
    private int odlegloscNajlepszejTrasy;
    private Queue<Polaczenie> kolejka;
    private record StanPolaczenia(int odleglosc, Polaczenie poprzednik){};
    private Map<Polaczenie, StanPolaczenia> mapaStanow;

    // Do wyszukiwania trasy
    public BFS(Wezel wezelStartowy, KomparatorTras komparator){
        this.wezelStartowy = wezelStartowy;
        this.komparator = komparator;
        this.kolejka = new LinkedList<>();
        this.mapaStanow = new HashMap<>();
        this.najlepszaTrasa = null;
        ostatniePolaczenie = null;
    }

    //Do wyszukiwania trasy do celu
    public BFS(Wezel wezelStartowy, int wezelDocelowy){
        this(wezelStartowy, null);
        this.wezelDocelowy = wezelDocelowy;
    }

    private void dodajNastepne(int odleglosc, Wezel wezel, Polaczenie poprzednik){
        List<Trasa> listaTras = wezel.trasy();
        List<Wyciag> listaWyciagow = wezel.wyciagi();

        for (Trasa trasa : listaTras){
            if (mapaStanow.containsKey(trasa)){
                // Trasa została już odwiedzona.
                continue;
            }
            mapaStanow.put(trasa, new StanPolaczenia(odleglosc, poprzednik));
            kolejka.offer(trasa);
            if (komparator != null && (najlepszaTrasa == null ||
                    komparator.porownaj(trasa, odleglosc, najlepszaTrasa, odlegloscNajlepszejTrasy) >= 0)){
                najlepszaTrasa = trasa;
                odlegloscNajlepszejTrasy = odleglosc;
            }
            if (komparator == null && trasa.koniec().numer() == wezelDocelowy && ostatniePolaczenie == null){
                ostatniePolaczenie = trasa;
            }
        }
        for (Wyciag wyciag : listaWyciagow){
            if (mapaStanow.containsKey(wyciag)){
                // Wyciąg został już owiedzony.
                continue;
            }
            mapaStanow.put(wyciag, new StanPolaczenia(odleglosc, poprzednik));
            kolejka.offer(wyciag);
            if (komparator == null && wyciag.koniec().numer() == wezelDocelowy && ostatniePolaczenie == null){
                ostatniePolaczenie = wyciag;
            }
        }
    }

    private Trasa znajdzNajlepszaTrase(){
        dodajNastepne(0, wezelStartowy, null);

        while (!kolejka.isEmpty()){
            Polaczenie polaczenie = kolejka.poll();
            StanPolaczenia stan = mapaStanow.get(polaczenie);
            dodajNastepne(stan.odleglosc + 1, polaczenie.koniec(), polaczenie);
        }
        return najlepszaTrasa;
    }

    private Trasa najlepszaTrasa(){
        if (najlepszaTrasa == null){
            throw new RuntimeException("Pytanie o najlepszą trasę bez wcześniejszego jej znalezienia");
        }
        return najlepszaTrasa;
    }
    private Polaczenie ostatniePolaczenie(){
        if (ostatniePolaczenie == null){
            throw new RuntimeException("Nie znaleziono ostatniego połączenia");
        }
        return ostatniePolaczenie;
    }

    private Stack<Polaczenie> budujPlan(){
        Stack<Polaczenie> plan = new Stack<>();
        Polaczenie polaczenie = null;

        if (komparator != null){
            polaczenie = najlepszaTrasa;
        }
        else {
            polaczenie = ostatniePolaczenie;
        }

        while (polaczenie != null){
            plan.push(polaczenie);
            StanPolaczenia stan = mapaStanow.get(polaczenie);
            polaczenie = stan.poprzednik;
        }
        return plan;
    }

    public Stack<Polaczenie> znajdzPlan(){
        if (komparator == null && wezelStartowy.numer() == wezelDocelowy){
            return new Stack<>();
        }
        znajdzNajlepszaTrase();
        return budujPlan();
    }
}
