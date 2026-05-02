package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Zdarzenie;

public class ZdarzeniePoczatekWjazdu extends ZdarzenieRaportowe{
    private Wyciag wyciag;

    public ZdarzeniePoczatekWjazdu(Czas czas, Sportowiec sportowiec, Wyciag wyciag){
        super(czas, sportowiec);
        this.wyciag = wyciag;
    }

    @Override
    public String raportuj(){
        return String.format("%s: Sportowiec %d rozpoczął wjazd wyciągiem %d", czas().toString(), sportowiec().numer(), wyciag.numer());
    }

    @Override
    public Zdarzenie[] wykonaj(){
        // Raportowanie:
        super.wykonaj();

        Czas czasKonca = Czas.dodaj(czas(), wyciag.czasPrzejazdu());
        Zdarzenie nastepne = new ZdarzenieKoniecWjazdu(czasKonca, sportowiec(), wyciag);

        return new Zdarzenie[]{nastepne};
    }
}
