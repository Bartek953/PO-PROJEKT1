package pl.projekt.sportowcy;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzeniePoczatekTrasy;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieUstawienieWKolejce;

import java.util.Random;

public class Sportowiec {
    private static Random generator;

    private int numer;
    private int poziomZaawansowania; //0-10
    private double wspSpontanicznosci;
    private Wagi wagi;
    private boolean sledzony;
    private Wezel wezelStartowy;
    private Czas czasStartu;

    public Sportowiec(int numer, int poziomZaawansowania, double wspSpontanicznosci, Wagi wagi, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        if (generator == null){
            generator = new Random();
        }
        this.numer = numer;
        this.poziomZaawansowania = poziomZaawansowania;
        this.wspSpontanicznosci = wspSpontanicznosci;
        this.wagi = wagi;
        this.sledzony = sledzony;
        this.wezelStartowy = wezelStartowy;
        this.czasStartu = czasStartu;
    }
    public Sportowiec(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wagaTrudnosci, double wagaNawierzchni, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        this(numer, poziomZaawansowania, wspSpontanicznosci, new Wagi(wagaTrudnosci, wagaNawierzchni), sledzony, wezelStartowy, czasStartu);
    }
    // Konstrukor do testów klasy Sportowiec
    public Sportowiec(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wagaTrudnosci, double wagaNawierzchni, boolean sledzony){
        this(numer, poziomZaawansowania, wspSpontanicznosci, new Wagi(wagaTrudnosci, wagaNawierzchni), sledzony, null, null);
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

        return wagi.lacznaAtrakcyjnosc(atrakcyjnoscTrudnosci, atrakcyjnoscNawierzchni);
    }

    public boolean sledzony(){
        return sledzony;
    }

    public Zdarzenie losowaDecyzja(Wezel wezel, Czas czas){
        int n = wezel.trasy().rozmiar() + wezel.wyciagi().rozmiar();
        int wybor = generator.nextInt(0, n);

        if (wybor < wezel.trasy().rozmiar()){
            wezel.trasy().daj(wybor).zwiekszLiczbePrzejazdow();
            return new ZdarzeniePoczatekTrasy(czas, this, wezel.trasy().daj(wybor));
        }
        else {
            int indeks = wybor - wezel.trasy().rozmiar();
            return new ZdarzenieUstawienieWKolejce(czas, this, wezel.wyciagi().daj(indeks));
        }
    }

    // Znajduje najlepszą trasę wychodzącą z danego węzła (tylko z niego - nie patrzy na wyciągi).
    // Zwraca null jeśli węzęł nie ma tras.
    public Trasa wybierzNajlepszaTrase(Wezel wezel){
        Trasa najlepszaTrasa = null;

        for (int i = 0; i < wezel.trasy().rozmiar(); i++){
            Trasa aktTrasa = wezel.trasy().daj(i);

            if (najlepszaTrasa == null || atrakcyjnoscTrasy(najlepszaTrasa) < atrakcyjnoscTrasy(aktTrasa)){
                najlepszaTrasa = aktTrasa;
            }
        }
        return najlepszaTrasa;
    }

    // Podejmuje decyzję o akcji sportowca (wybór wyciągu lub trasy).
    public Zdarzenie decyzja(Wezel wezel, Czas czas){
        // Spontaniczny wybór sportowca:
        if (generator.nextDouble() < wspSpontanicznosci){
            return losowaDecyzja(wezel, czas);
        }

        Trasa najlepszaTrasa = wybierzNajlepszaTrase(wezel);
        Wyciag najlepszyWyciag = null;

        for (int i = 0; i < wezel.wyciagi().rozmiar(); i++){
            Wyciag wyciag = wezel.wyciagi().daj(i);
            Trasa aktTrasa = wybierzNajlepszaTrase(wyciag.koniec());
            if (najlepszaTrasa == null || (aktTrasa != null && atrakcyjnoscTrasy(najlepszaTrasa) < atrakcyjnoscTrasy(aktTrasa))){
                najlepszaTrasa = aktTrasa;
                najlepszyWyciag = wyciag;
            }
        }

        if (najlepszaTrasa == null && wezel.wyciagi().rozmiar() != 0){
            return new ZdarzenieUstawienieWKolejce(czas, this, wezel.wyciagi().daj(0));
        }

        if (najlepszaTrasa == null){
            throw new RuntimeException("Graf nie jest silnie spójny!");
        }

        if (najlepszaTrasa.start() == wezel){
            najlepszaTrasa.zwiekszLiczbePrzejazdow();
            return new ZdarzeniePoczatekTrasy(czas, this, najlepszaTrasa);
        }
        else {
            return new ZdarzenieUstawienieWKolejce(czas, this, najlepszyWyciag);
        }
    }

}
