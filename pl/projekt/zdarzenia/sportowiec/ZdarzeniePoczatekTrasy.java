package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

public class ZdarzeniePoczatekTrasy extends ZdarzenieRaportowe{
    private Trasa trasa;

    public ZdarzeniePoczatekTrasy(Czas czas, Sportowiec sportowiec, Trasa trasa){
        super(czas, sportowiec, Priorytet.ZDARZENIE_NORMALNE);
        this.trasa = trasa;
    }
    public Trasa trasa(){
        return trasa;
    }

    @Override
    public String raportuj(){
        return String.format("%s: Sportowiec %d rozpoczął zjazd trasą %d", czas().toString(), sportowiec().numer(), trasa.numer());
    }

    @Override
    public Zdarzenie[] wykonaj(){
        // Raportowanie:
        super.wykonaj();

        Czas czasKonca = Czas.dodaj(czas(), trasa.czasPrzejazdu());
        Zdarzenie nastepne = new ZdarzenieKoniecTrasy(czasKonca, sportowiec(), trasa);

        return new Zdarzenie[]{nastepne};
    }
}
