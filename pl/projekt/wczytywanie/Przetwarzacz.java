package pl.projekt.wczytywanie;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.FabrykaSportowcow;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.transferdanych.GrupaSportowcowDTO;
import pl.projekt.transferdanych.TrasaDTO;
import pl.projekt.transferdanych.WezelDTO;
import pl.projekt.transferdanych.WyciagDTO;


// Celem przetwarzacza jest utworzenie obiektów, na podstawie danych
// wprowadzonych przez obiekty DTO.
public class Przetwarzacz {
    private Wezel[] wezly;
    private Wyciag[] wyciagi;
    private Trasa[] trasy;
    private Sportowiec[] sportowcy;

    public void przetworz(WezelDTO[] dto) {
        wezly = new Wezel[dto.length];
        for (int i = 0; i < dto.length; i++) {
            WezelDTO d = dto[i];
            wezly[i] = new Wezel(d.numer(), d.wysokosc(), d.x(), d.y(), d.skomunikowany());
        }
    }

    public void przetworz(WyciagDTO[] dto) {
        assert wezly != null : "Najpierw przetworz wezly";
        wyciagi = new Wyciag[dto.length];
        for (int i = 0; i < dto.length; i++) {
            WyciagDTO d = dto[i];
            wyciagi[i] = new Wyciag(d.numer(),
                    wezly[d.numerWezlaPoczatkowego()],
                    wezly[d.numerWezlaKoncowego()],
                    d.czasPrzejazdSekund(),
                    d.odstepCzasuSekund(),
                    d.maksymalnaLiczbaOsob());
        }
    }

    public void przetworz(TrasaDTO[] dto) {
        assert wezly != null : "Najpierw przetworz wezly";
        trasy = new Trasa[dto.length];
        for (int i = 0; i < dto.length; i++) {
            TrasaDTO d = dto[i];
            trasy[i] = new Trasa(d.numer(),
                    wezly[d.numerWezlaPoczatkowego()],
                    wezly[d.numerWezlaKoncowego()],
                    d.czasPrzejazdSekund(),
                    d.poziomTrudnosci(),
                    d.odpornosc(),
                    d.bazowaAtrakcyjnosc());
        }
    }

    public void przetworz(GrupaSportowcowDTO[] dto) {
        assert wezly != null : "Najpierw przetworz wezly";

        int total = 0;
        for (GrupaSportowcowDTO g : dto) total += g.liczba();

        sportowcy = new Sportowiec[total];
        int liczbaSportowcow = 0;

        for (GrupaSportowcowDTO g : dto) {
            Czas czasPrzyjazdu = new Czas(g.czasPrzyjazduPierwszego());
            Czas odstep = new Czas(0, 0, g.odstepCzasuSekund());
            Wezel wezelStart = wezly[g.numerWezlaStartowego()];

            for (int j = 0; j < g.liczba(); j++) {
                sportowcy[liczbaSportowcow] = FabrykaSportowcow.stworzSportowca(
                        liczbaSportowcow,
                        g.poziomZaawansowania(),
                        g.wspSpontanicznosci(),
                        g.wspZnudzenia(),
                        g.wagaTrudnosci(),
                        g.wagaNawierzchni(),
                        g.wagaZnudzenia(),
                        g.sledzeni(),
                        wezelStart,
                        new Czas(czasPrzyjazdu),
                        g.rodzaj()
                );
                liczbaSportowcow++;
                czasPrzyjazdu = Czas.dodaj(czasPrzyjazdu, odstep);
            }
        }
    }

    public Wezel[] dajWezly() { return wezly; }
    public Wyciag[] dajWyciagi() { return wyciagi; }
    public Trasa[] dajTrasy() { return trasy; }
    public Sportowiec[] dajSportowcow() { return sportowcy; }
}
