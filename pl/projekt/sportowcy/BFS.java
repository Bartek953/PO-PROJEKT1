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
    private SportowiecPlanujacy sportowiec;
    private Wezel wezelStartowy;
    private Trasa najlepszaTrasa;
    private int odlegloscNajlepszejTrasy;
    private Queue<Polaczenie> kolejka;
    private record StanPolaczenia(int odleglosc, Polaczenie poprzednik){};
    private Map<Polaczenie, StanPolaczenia> mapaStanow;

    public BFS(Wezel wezelStartowy, SportowiecPlanujacy sportowiec){
        this.wezelStartowy = wezelStartowy;
        this.sportowiec = sportowiec;
        this.kolejka = new LinkedList<>();
        this.mapaStanow = new HashMap<>();
        this.najlepszaTrasa = null;
    }

    private void dodajNastepne(int odleglosc, Wezel wezel, Polaczenie poprzednik){
        List<Trasa> listaTras = wezel.trasy();
        List<Wyciag> listaWyciagow = wezel.wyciagi();

        for (Trasa trasa : listaTras){
            if (mapaStanow.containsKey(trasa)){
                // Trasa została już owiedzona.
                continue;
            }
            mapaStanow.put(trasa, new StanPolaczenia(odleglosc, poprzednik));
            kolejka.offer(trasa);
            if (najlepszaTrasa == null ||
                    sportowiec.porownaj(trasa, odleglosc, najlepszaTrasa, odlegloscNajlepszejTrasy) >= 0){
                najlepszaTrasa = trasa;
                odlegloscNajlepszejTrasy = odleglosc;
            }
        }
        for (Wyciag wyciag : listaWyciagow){
            if (mapaStanow.containsKey(wyciag)){
                // Wyciąg został już owiedzony.
                continue;
            }
            mapaStanow.put(wyciag, new StanPolaczenia(odleglosc, poprzednik));
            kolejka.offer(wyciag);
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

    private void zapiszPlan(){
        Polaczenie polaczenie = najlepszaTrasa();

        while (polaczenie != null){
            sportowiec.dodajDoPlanu(polaczenie);
            StanPolaczenia stan = mapaStanow.get(polaczenie);
            polaczenie = stan.poprzednik;
        }
    }

    public void znajdzIZapiszPlan(){
        znajdzNajlepszaTrase();
        zapiszPlan();
    }
}
