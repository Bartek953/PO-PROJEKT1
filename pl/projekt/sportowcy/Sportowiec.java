package pl.projekt.sportowcy;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Polaczenie;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzeniePoczatekTrasy;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieUstawienieWKolejce;

import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public abstract class Sportowiec {
    private static Random generator;

    private int numer;
    private int poziomZaawansowania; //0-10
    private double wspSpontanicznosci;
    private double wspZnudzenia;
    private Wagi wagi;
    private boolean sledzony;
    private Wezel wezelStartowy;
    private Czas czasStartu;
    private int licznikZjazdow;
    private Map<Polaczenie, ArrayList<Integer>> mapaPrzejazdow;
    private int numerPrzejazdu;

    // Rekord do hashmapy
    private record StanZnudzenia(int indeksOstatniegoZjazdu, double wartoscZnudzenia) {};
    // klucz: nr_trasy
    private Map<Integer, StanZnudzenia> mapaZnudzenia;

    public Sportowiec(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, Wagi wagi, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        if (generator == null){
            generator = new Random();
        }
        this.numer = numer;
        this.poziomZaawansowania = poziomZaawansowania;
        this.wspSpontanicznosci = wspSpontanicznosci;
        this.wspZnudzenia = wspZnudzenia;
        this.wagi = wagi;
        this.sledzony = sledzony;
        this.wezelStartowy = wezelStartowy;
        this.czasStartu = czasStartu;
        this.licznikZjazdow = 0;
        mapaZnudzenia = new HashMap<>();

        if (sledzony){
            numerPrzejazdu = 1;
            mapaPrzejazdow = new HashMap<>();
        }
    }
    public Sportowiec(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, double wagaTrudnosci, double wagaNawierzchni, double wagaZnudzenia, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        this(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, new Wagi(wagaTrudnosci, wagaNawierzchni, wagaZnudzenia), sledzony, wezelStartowy, czasStartu);
    }
    public int numer(){
        return numer;
    }
    public int poziomZaawansowania(){
        return poziomZaawansowania;
    }
    public Wezel wezelStartowy(){
        return wezelStartowy;
    }
    public Czas czasStartu(){
        return czasStartu;
    }

    // Zwraca poziom znudzenia daną trasą: double w [0, 1]
    public double poziomZnudzenia(Trasa trasa){
        if (!mapaZnudzenia.containsKey(trasa.numer())){
            return 0;
        }
        StanZnudzenia stan = mapaZnudzenia.get(trasa.numer());

        if (stan.indeksOstatniegoZjazdu == licznikZjazdow){
            return stan.wartoscZnudzenia;
        }
        else {
            double wykladnik = licznikZjazdow - stan.indeksOstatniegoZjazdu;
            double noweZnudzenie = stan.wartoscZnudzenia * Math.pow(1 - wspZnudzenia, wykladnik);
            return noweZnudzenie;
        }
    }
    // Wywoływane po przejechaniu daną trasą, by aktualizować jej wartość w hashmapie oraz lizcbe zjazdów.
    private void aktualizujZnudzenie(Trasa trasa){
        double stareZnudzenie = poziomZnudzenia(trasa);
        licznikZjazdow++;
        //xt = 1
        double noweZnudzenie = wspZnudzenia + (1 - wspZnudzenia) * stareZnudzenie;
        mapaZnudzenia.put(trasa.numer(), new StanZnudzenia(licznikZjazdow, noweZnudzenie));
    }

    // Umożliwia klasom nadrzędnym modyfikacje zachowania końca zjazdu.
    protected void akcjaPoZjezdzie(Trasa trasa){
        return;
    }

    private void aktualizujMapePrzejazdow(Polaczenie polaczenie){
        if (!sledzony()){
            throw new RuntimeException("Nieśledzony sportowiec wywołał metodę sportowca śledzonego");
        }
        if (!mapaPrzejazdow.containsKey(polaczenie)){
            mapaPrzejazdow.put(polaczenie, new ArrayList<>());
        }
        mapaPrzejazdow.get(polaczenie).add(numerPrzejazdu);
        numerPrzejazdu++;
    }
    public int liczbaPrzejazdow(Polaczenie polaczenie){
        if (!sledzony()){
            throw new RuntimeException("Nieśledzony sportowiec wywołał metodę sportowca śledzonego");
        }
        if (!mapaPrzejazdow.containsKey(polaczenie)){
            return 0;
        }
        return mapaPrzejazdow.get(polaczenie).size();
    }
    public ArrayList<Integer> listaPrzejazdow(Polaczenie polaczenie){
        if (!sledzony()){
            throw new RuntimeException("Nieśledzony sportowiec wywołał metodę sportowca śledzonego");
        }
        return mapaPrzejazdow.getOrDefault(polaczenie, null);
    }

    // Wywoływane po zjechaniu daną trasą. Udostępnia sportowcowi interfejs
    // do wykonywania akcji wywoływanych przez koniec zjazdu.
    public void zjechalTrasa(Trasa trasa){
        aktualizujZnudzenie(trasa);
        akcjaPoZjezdzie(trasa);
        if (sledzony()){
            aktualizujMapePrzejazdow(trasa);
        }
    }
    public void wjechalWyciagiem(Wyciag wyciag){
        if (sledzony()){
            aktualizujMapePrzejazdow(wyciag);
        }
    }

    // Zwraca liczbę z [0, 1]
    private double dopasowanieTrudnosci(Trasa trasa){
        if (trasa.poziomTrudnosci() >= poziomZaawansowania() + 5){
            return 0;
        }
        else if (poziomZaawansowania() > trasa.poziomTrudnosci()){
            return Math.max((double)0.2, (1.0 - (double)(poziomZaawansowania() - trasa.poziomTrudnosci()) / 7.0));
        }
        else {
            return 1.0 - (double)(trasa.poziomTrudnosci() - poziomZaawansowania()) / 5.0;
        }
    }

    // Zwraca liczbę z [0, 1]
    public double atrakcyjnoscTrasy(Trasa trasa){
        double atrakcyjnoscTrudnosci = dopasowanieTrudnosci(trasa);
        double atrakcyjnoscNawierzchni = trasa.atrakcyjnoscNawierzchni();
        double znudzenie = poziomZnudzenia(trasa);

        return wagi.lacznaAtrakcyjnosc(atrakcyjnoscTrudnosci, atrakcyjnoscNawierzchni, znudzenie);
    }

    public boolean sledzony(){
        return sledzony;
    }

    public Polaczenie losowePolaczenie(Wezel wezel){
        int n = wezel.trasy().size() + wezel.wyciagi().size();
        int wybor = generator.nextInt(0, n);

        if (wybor < wezel.trasy().size()){
            return wezel.trasy().get(wybor);
        }
        else {
            wybor -= wezel.trasy().size();
            return wezel.wyciagi().get(wybor);
        }
    }

    public Zdarzenie losowaDecyzja(Wezel wezel, Czas czas){
        return losowePolaczenie(wezel).stworzZdarzenie(czas, this);
    }

    public boolean losujDecyzje(){
        return generator.nextDouble() < wspSpontanicznosci;
    }

    // Podejmuje decyzję o akcji sportowca (wybór wyciągu lub trasy).
    public abstract Zdarzenie decyzja(Wezel wezel, Czas czas);

}
