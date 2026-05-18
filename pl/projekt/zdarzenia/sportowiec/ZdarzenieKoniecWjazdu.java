package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

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
    public Zdarzenie[] wykonaj(){
        // Raportowanie:
        super.wykonaj();

        wyciag.zwiekszLiczbePrzejazdow();
        Zdarzenie nastepne = new ZdarzenieDecyzyjne(czas(), sportowiec(), wyciag.koniec());
        return new Zdarzenie[]{nastepne};
    }
}
