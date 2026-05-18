package pl.projekt.zdarzenia.wyciag;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.strukturydanych.ListaZdarzen;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzeniePoczatekWjazdu;

public class ZdarzeniePrzyjazdWyciagu extends ZdarzenieWyciagu{
    public ZdarzeniePrzyjazdWyciagu(Czas czas, Wyciag wyciag){
        super(czas, wyciag, Priorytet.ZDARZENIE_NORMALNE);
    }

    @Override
    public Zdarzenie[] wykonaj(){
        ListaZdarzen lista = new ListaZdarzen(4, 2);

        // Zabieranie sportowcoc z kolejki.
        int ileNaKanapie = 0;
        while (!wyciag().kolejkaPusta() && ileNaKanapie < wyciag().maksymalnaLiczbaOsob()){
            Sportowiec sportowiec = wyciag().pierwszyWKolejce();
            wyciag().usunPierwszegoZKolejki();
            ileNaKanapie++;

            Zdarzenie zdarzenie = new ZdarzeniePoczatekWjazdu(czas(), sportowiec, wyciag());
            lista.dodaj(zdarzenie);
        }
        Czas nastepnyPrzyjazd = Czas.dodaj(czas(), wyciag().odstepCzasu());
        lista.dodaj(new ZdarzeniePrzyjazdWyciagu(nastepnyPrzyjazd, wyciag()));

        return lista.naTablice();
    }
}
