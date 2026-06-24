package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

import java.util.ArrayList;
import java.util.List;

public class ZdarzeniePoczatekWjazdu extends ZdarzenieRaportowe{
    private final Wyciag wyciag;

    public ZdarzeniePoczatekWjazdu(Czas czas, Sportowiec sportowiec, Wyciag wyciag){
        super(czas, sportowiec, Priorytet.ZDARZENIE_NORMALNE);
        this.wyciag = wyciag;
    }

    @Override
    public String raportuj(){
        return String.format("%s: Sportowiec %d rozpoczął wjazd wyciągiem %d", czas().toString(), sportowiec().numer(), wyciag.numer());
    }

    @Override
    public List<Zdarzenie> wykonaj(){
        // Raportowanie:
        super.wykonaj();

        Czas czasKonca = Czas.dodaj(czas(), wyciag.czasPrzejazdu());
        Zdarzenie nastepne = new ZdarzenieKoniecWjazdu(czasKonca, sportowiec(), wyciag);
        List<Zdarzenie> lista = new ArrayList<>();
        lista.add(nastepne);
        return lista;
    }
}
