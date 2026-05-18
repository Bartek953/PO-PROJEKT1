package pl.projekt.wczytywanie;

import pl.projekt.transferdanych.GrupaSportowcowDTO;
import pl.projekt.transferdanych.TrasaDTO;
import pl.projekt.transferdanych.WezelDTO;
import pl.projekt.transferdanych.WyciagDTO;

import java.util.Locale;
import java.util.Scanner;


// Celem skanera jest wczytanie wejścia i załadowanie go do obiektów DTO.
// Skaner nie przetwarza danych - tym zajmie się przetwarzacz.
public class SkanerSymulacji {
    private final Scanner skanerWejscia;

    public SkanerSymulacji() {
        this.skanerWejscia = new Scanner(System.in);
    }

    // Pomija puste linie miedzy sekcjami i zwraca skaner pierwszej linii z liczba calkowita
    private Scanner naglowekSekcji() {
        String linia = skanerWejscia.nextLine();
        Scanner s = new Scanner(linia);
        while (!s.hasNextInt()) {
            linia = skanerWejscia.nextLine();
            s = new Scanner(linia);
        }
        return s;
    }

    private Scanner skanerLinii() {
        return new Scanner(skanerWejscia.nextLine());
    }

    private Scanner skanerLiniiAngielski() {
        Scanner s = new Scanner(skanerWejscia.nextLine());
        s.useLocale(Locale.ENGLISH);
        return s;
    }

    public WezelDTO[] wczytajWezly() {
        int n = naglowekSekcji().nextInt();
        WezelDTO[] wynik = new WezelDTO[n];

        for (int i = 0; i < n; i++) {
            Scanner s = skanerLinii();
            int wysokosc = s.nextInt();
            int x = s.nextInt();
            int y = s.nextInt();
            boolean skomunikowany = s.hasNext() && s.next().equals("s");
            wynik[i] = new WezelDTO(i, wysokosc, x, y, skomunikowany);
        }
        return wynik;
    }

    public WyciagDTO[] wczytajWyciagi() {
        int n = naglowekSekcji().nextInt();
        WyciagDTO[] wynik = new WyciagDTO[n];

        for (int i = 0; i < n; i++) {
            Scanner s = skanerLinii();
            int poczatek = s.nextInt();
            int koniec = s.nextInt();
            int odstep = s.nextInt();
            int maksOsob = s.nextInt();
            int czasPrzejazdu = s.nextInt();
            wynik[i] = new WyciagDTO(i, poczatek, koniec, odstep, maksOsob, czasPrzejazdu);
        }
        return wynik;
    }

    public TrasaDTO[] wczytajTrasy() {
        int n = naglowekSekcji().nextInt();
        TrasaDTO[] wynik = new TrasaDTO[n];

        for (int i = 0; i < n; i++) {
            Scanner s = skanerLiniiAngielski();
            int poczatek = s.nextInt();
            int koniec = s.nextInt();
            int trudnosc = s.nextInt();
            int czasPrzejazdu = s.nextInt();
            double bazowaAtrakcyjnosc = s.nextDouble();
            double odpornosc = s.nextDouble();
            wynik[i] = new TrasaDTO(i, poczatek, koniec, trudnosc, czasPrzejazdu, bazowaAtrakcyjnosc, odpornosc);
        }
        return wynik;
    }

    public GrupaSportowcowDTO[] wczytajGrupySportowcow() {
        int n = naglowekSekcji().nextInt();
        GrupaSportowcowDTO[] wynik = new GrupaSportowcowDTO[n];

        for (int i = 0; i < n; i++) {
            Scanner s1 = skanerLiniiAngielski();
            int liczba = s1.nextInt();
            int poziom = s1.nextInt();
            double wspSpontanicznosci = s1.nextDouble();
            boolean sledzeni = s1.hasNext() && s1.next().equals("s");

            Scanner s2 = skanerLiniiAngielski();
            double wagaTrudnosci = s2.nextDouble();
            double wagaNawierzchni = s2.nextDouble();

            Scanner s3 = skanerLinii();
            int numerWezla = s3.nextInt();
            String czasStr = s3.next();
            int odstep = (liczba > 1 && s3.hasNextInt()) ? s3.nextInt() : 0;

            wynik[i] = new GrupaSportowcowDTO(liczba, poziom, wspSpontanicznosci, sledzeni,
                    wagaTrudnosci, wagaNawierzchni, numerWezla, czasStr, odstep);
        }
        return wynik;
    }
}
