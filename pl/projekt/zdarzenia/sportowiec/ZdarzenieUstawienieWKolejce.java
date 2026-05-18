package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

public class ZdarzenieUstawienieWKolejce extends ZdarzenieRaportowe {
    private Wyciag wyciag;
    public ZdarzenieUstawienieWKolejce(Czas czas, Sportowiec sportowiec, Wyciag wyciag){
        super(czas, sportowiec, Priorytet.ZDARZENIE_NATYCHMIASTOWE);
        this.wyciag = wyciag;
    }

    @Override
    public String raportuj(){
        return String.format("%s: Sportowiec %d ustawił się w kolejce wyciągu %d", czas().toString(), sportowiec().numer(), wyciag.numer());
    }

    @Override
    public Zdarzenie[] wykonaj(){
        // Raportowanie:
        super.wykonaj();

        wyciag.dodajDoKolejki(sportowiec());

        return new Zdarzenie[0];
    }
}
