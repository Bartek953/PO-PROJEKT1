package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

import java.util.ArrayList;
import java.util.List;

public class ZdarzenieKoniecTrasy extends ZdarzenieRaportowe{
    private final Trasa trasa;

    public ZdarzenieKoniecTrasy(Czas czas, Sportowiec sportowiec, Trasa trasa){
        super(czas, sportowiec, Priorytet.ZDARZENIE_NORMALNE);
        this.trasa = trasa;
    }

    @Override
    public String raportuj(){
        return String.format("%s: Sportowiec %d zakończył zjazd trasą %d", czas().toString(), sportowiec().numer(), trasa.numer());
    }

    @Override
    public boolean wykonajPoZamknieciu(){
        return true;
    }

    @Override
    public List<Zdarzenie> wykonaj(){
        // Raportowanie:
        super.wykonaj();
        sportowiec().zjechalTrasa(trasa);

        Zdarzenie nastepne = new ZdarzenieDecyzyjne(czas(), sportowiec(), trasa.koniec());
        List<Zdarzenie> lista = new ArrayList<>();
        lista.add(nastepne);
        return lista;
    }
}
