package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wezel;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

import java.util.ArrayList;
import java.util.List;


// Odpowiada za przybycie sportowca do wezla i wybor nastepnej trasy, nie jest raportowane
public class ZdarzenieDecyzyjne extends ZdarzenieSportowca {
    private Wezel wezel;

    public ZdarzenieDecyzyjne(Czas czas, Sportowiec sportowiec, Wezel wezel){
        super(czas, sportowiec, Priorytet.ZDARZENIE_NATYCHMIASTOWE);
        this.wezel = wezel;
    }

    @Override
    public List<Zdarzenie> wykonaj(){
        Zdarzenie decyzja = sportowiec().decyzja(wezel, czas());
        List<Zdarzenie> lista = new ArrayList<>();
        lista.add(decyzja);

        return lista;
    }
}
