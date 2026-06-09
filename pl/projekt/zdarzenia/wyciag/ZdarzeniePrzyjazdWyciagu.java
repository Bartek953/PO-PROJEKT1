package pl.projekt.zdarzenia.wyciag;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzeniePoczatekWjazdu;

import java.util.List;
import java.util.ArrayList;

public class ZdarzeniePrzyjazdWyciagu extends ZdarzenieWyciagu{
    public ZdarzeniePrzyjazdWyciagu(Czas czas, Wyciag wyciag){
        super(czas, wyciag, Priorytet.ZDARZENIE_NORMALNE);
    }

    @Override
    public List<Zdarzenie> wykonaj(){
        List<Zdarzenie> lista = new ArrayList<>();

        // Zabieranie sportowców z kolejki.
        int ileNaKanapie = 0;
        while (!wyciag().kolejkaPusta() && ileNaKanapie < wyciag().maksymalnaLiczbaOsob()){
            Sportowiec sportowiec = wyciag().pierwszyWKolejce();
            wyciag().usunPierwszegoZKolejki();
            ileNaKanapie++;

            Zdarzenie zdarzenie = new ZdarzeniePoczatekWjazdu(czas(), sportowiec, wyciag());
            lista.add(zdarzenie);
        }
        Czas nastepnyPrzyjazd = Czas.dodaj(czas(), wyciag().odstepCzasu());
        lista.add(new ZdarzeniePrzyjazdWyciagu(nastepnyPrzyjazd, wyciag()));

        return lista;
    }
}
