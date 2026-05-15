package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Zdarzenie;

public class ZdarzenieKoniecTrasy extends ZdarzenieRaportowe{
    private Trasa trasa;

    public ZdarzenieKoniecTrasy(Czas czas, Sportowiec sportowiec, Trasa trasa){
        super(czas, sportowiec);
        this.trasa = trasa;
    }

    @Override
    public String raportuj(){
        return String.format("%s: Sportowiec %d zakończył zjazd trasą %d", czas().toString(), sportowiec().numer(), trasa.numer());
    }

    @Override
    public Zdarzenie[] wykonaj(){
        // Raportowanie:
        super.wykonaj();
        Zdarzenie nastepne = new ZdarzenieDecyzyjne(czas(), sportowiec(), trasa.koniec());
        return new Zdarzenie[]{nastepne};
    }
}
