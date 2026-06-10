package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

import java.util.ArrayList;
import java.util.List;

public class ZdarzenieKoniecWjazdu extends ZdarzenieRaportowe{
    private Wyciag wyciag;

    public ZdarzenieKoniecWjazdu(Czas czas, Sportowiec sportowiec, Wyciag wyciag){
        super(czas, sportowiec, Priorytet.ZDARZENIE_NORMALNE);
        this.wyciag = wyciag;
    }

    @Override
    public String raportuj(){
        return String.format("%s: Sportowiec %d zakończył wjazd wyciągiem %d", czas().toString(), sportowiec().numer(), wyciag.numer());
    }

    @Override
    public boolean wykonajPoZamknieciu(){
        return true;
    }

    @Override
    public List<Zdarzenie> wykonaj(){
        // Raportowanie:
        super.wykonaj();
        sportowiec().wjechalWyciagiem(wyciag);

        wyciag.zwiekszLiczbePrzejazdow();
        Zdarzenie nastepne = new ZdarzenieDecyzyjne(czas(), sportowiec(), wyciag.koniec());
        List<Zdarzenie> lista = new ArrayList<>();
        lista.add(nastepne);
        return lista;
    }
}
