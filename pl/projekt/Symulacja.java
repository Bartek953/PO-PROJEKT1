package pl.projekt;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.strukturydanych.StertowaKolejkaZdarzen;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieDecyzyjne;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieKoniecTrasy;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieKoniecWjazdu;
import pl.projekt.zdarzenia.wyciag.ZdarzeniePrzyjazdWyciagu;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.arraycopy;

public class Symulacja {
    private StertowaKolejkaZdarzen kolejkaZdarzen;
    private Wezel[] listaWezlow;
    private Wyciag[] listaWyciagow;
    private Trasa[] listaTras;
    private Sportowiec[] listaSportowcow;
    private int liczbaSportowcow;
    private Scanner skanerWejscia;
    private Czas czasOtwarcia;
    private Czas czasZamkniecia;

    public Symulacja(){
        // Sportowiec.resetuj_numer()....
        this.kolejkaZdarzen = new StertowaKolejkaZdarzen();
        this.skanerWejscia = new Scanner(System.in);
        czasOtwarcia = new Czas(9, 0, 0);
        czasZamkniecia = new Czas(15, 0, 0);
    }

    public void wczytajWezly(){
        String linia = skanerWejscia.nextLine();
        Scanner skanerLinii = new Scanner(linia);
        while(!skanerLinii.hasNextInt()){
            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
        }
        int n = skanerLinii.nextInt();
        listaWezlow = new Wezel[n];

        for (int i = 0; i < n; i++){
            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
            int wysokosc = skanerLinii.nextInt();
            int x = skanerLinii.nextInt();
            int y = skanerLinii.nextInt();
            boolean s = false;
            if (skanerLinii.hasNext()){
                s = (skanerLinii.next().equals("s"));
            }
            listaWezlow[i] = new Wezel(i, wysokosc, x, y, s);
        }
    }

    public void wczytajWyciagi(){
        String linia = skanerWejscia.nextLine();
        Scanner skanerLinii = new Scanner(linia);
        while(!skanerLinii.hasNextInt()){
            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
        }
        int n = skanerLinii.nextInt();
        listaWyciagow = new Wyciag[n];

        for (int i = 0; i < n; i++){
            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);

            int poczatek = skanerLinii.nextInt();
            int koniec = skanerLinii.nextInt();
            int odstepCzasu = skanerLinii.nextInt();
            int maksymalnyZaladunek = skanerLinii.nextInt();
            int czasPrzejazdu = skanerLinii.nextInt();

            listaWyciagow[i] = new Wyciag(i, listaWezlow[poczatek], listaWezlow[koniec], czasPrzejazdu, odstepCzasu, maksymalnyZaladunek);
            Zdarzenie startWyciagu = new ZdarzeniePrzyjazdWyciagu(czasOtwarcia, listaWyciagow[i]);
            kolejkaZdarzen.dodaj(startWyciagu);
        }
    }
    public void wczytajTrasy(){
        String linia = skanerWejscia.nextLine();
        Scanner skanerLinii = new Scanner(linia);
        while(!skanerLinii.hasNextInt()){
            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
        }
        int n = skanerLinii.nextInt();
        listaTras = new Trasa[n];

        for (int i = 0; i < n; i++){
            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
            skanerLinii.useLocale(Locale.ENGLISH);

            int poczatek = skanerLinii.nextInt();
            int koniec = skanerLinii.nextInt();
            int poziomTrudnosci = skanerLinii.nextInt();
            int czasPrzejazdu = skanerLinii.nextInt();
            double bazowaAtrakcyjnosc = skanerLinii.nextDouble();
            double odpornosc = skanerLinii.nextDouble();

            listaTras[i] = new Trasa(i, listaWezlow[poczatek], listaWezlow[koniec],czasPrzejazdu, poziomTrudnosci, odpornosc, bazowaAtrakcyjnosc);
        }
    }

    public void wczytajSportowcow() {
        String linia = skanerWejscia.nextLine();
        Scanner skanerLinii = new Scanner(linia);
        while(!skanerLinii.hasNextInt()){
            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
        }
        int n = skanerLinii.nextInt();
        liczbaSportowcow = 0;
        listaSportowcow = new Sportowiec[n];

        for (int i = 0; i < n; i++) {
            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
            skanerLinii.useLocale(Locale.ENGLISH);

            int liczba = skanerLinii.nextInt();
            int poziomZaawansowania = skanerLinii.nextInt();
            double wspSpontanicznosci = skanerLinii.nextDouble();

            boolean s = false;
            if (skanerLinii.hasNext()) {
                s = (skanerLinii.next().equals("s"));
            }

            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
            skanerLinii.useLocale(Locale.ENGLISH);

            double wagaTrudnosci = skanerLinii.nextDouble();
            double wagaNawierzchni = skanerLinii.nextDouble();

            linia = skanerWejscia.nextLine();
            skanerLinii = new Scanner(linia);
            skanerLinii.useLocale(Locale.ENGLISH);

            int start = skanerLinii.nextInt();
            Czas czasPrzyjazdu = new Czas(skanerLinii.next());
            int odstepInt = (liczba > 1 ? skanerLinii.nextInt() : 0);
            Czas odstepCzasu = new Czas(0, 0, odstepInt);

            for (int j = 0; j < liczba; j++) {
                if (liczbaSportowcow == listaSportowcow.length) {
                    Sportowiec[] nowaLista = new Sportowiec[2 * liczbaSportowcow];
                    arraycopy(listaSportowcow, 0, nowaLista, 0, liczbaSportowcow);
                    listaSportowcow = nowaLista;
                }
                listaSportowcow[liczbaSportowcow] = new Sportowiec(liczbaSportowcow, poziomZaawansowania, wspSpontanicznosci, wagaTrudnosci, wagaNawierzchni, s);
                Zdarzenie poczatekDnia = new ZdarzenieDecyzyjne(czasPrzyjazdu, listaSportowcow[liczbaSportowcow], listaWezlow[start]);
                kolejkaZdarzen.dodaj(poczatekDnia);
                czasPrzyjazdu = Czas.dodaj(czasPrzyjazdu, odstepCzasu);
                liczbaSportowcow++;
            }
        }
    }
    public void wczytaj(){
        wczytajWezly();
        wczytajWyciagi();
        wczytajTrasy();
        wczytajSportowcow();
    }

    public void symuluj(){
        wczytaj();

        while (!kolejkaZdarzen.pusta()){
            Zdarzenie zdarzenie = kolejkaZdarzen.dajNajmniejszy();
            kolejkaZdarzen.usunNajmniejszy();

            Zdarzenie[] listaKolejnych = zdarzenie.wykonaj();

            for (Zdarzenie kolejne : listaKolejnych){
                if (kolejne != null && (Czas.mniejszy(kolejne.czas(), czasZamkniecia)
                        || kolejne instanceof ZdarzenieKoniecTrasy || kolejne instanceof ZdarzenieKoniecWjazdu)){
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
