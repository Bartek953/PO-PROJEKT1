package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

import java.util.ArrayList;
import java.util.List;

/*
Odniosę się jeszcze tutaj do komentarza z code review pierwszego projektu.
Zwiększanie licznika zjazdów nie jest wykonywane tutaj, bo zgodnie z
treścią pierwszego forum, zaraz po decyzji sportowca inni sportowcy muszą
o niej wiedzieć. By upewnić się, że tak będzie, zwiększam licznik przejazdu od
razu w momencie podjęcia decyzji.
 */

public class ZdarzeniePoczatekTrasy extends ZdarzenieRaportowe{
    private final Trasa trasa;

    public ZdarzeniePoczatekTrasy(Czas czas, Sportowiec sportowiec, Trasa trasa){
        super(czas, sportowiec, Priorytet.ZDARZENIE_NORMALNE);
        this.trasa = trasa;
    }
    public Trasa trasa(){
        return trasa;
    }

    @Override
    public String raportuj(){
        return String.format(
                "%s: Sportowiec %d rozpoczął zjazd trasą %d",
                czas().toString(), sportowiec().numer(), trasa.numer()
        );
    }

    @Override
    public List<Zdarzenie> wykonaj(){
        // Raportowanie:
        super.wykonaj();

        Czas czasKonca = Czas.dodaj(czas(), trasa.czasPrzejazdu());
        Zdarzenie nastepne = new ZdarzenieKoniecTrasy(czasKonca, sportowiec(), trasa);
        List<Zdarzenie> lista = new ArrayList<>();
        lista.add(nastepne);
        return lista;
    }
}
