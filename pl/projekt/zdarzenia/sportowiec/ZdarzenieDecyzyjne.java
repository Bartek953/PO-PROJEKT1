package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wezel;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Zdarzenie;


// Odpowiada za przybycie sportowca do wezla i wybor nastepnej trasy, nie jest raportowane
public class ZdarzenieDecyzyjne extends ZdarzenieSportowca {
    private Wezel wezel;

    public ZdarzenieDecyzyjne(Czas czas, Sportowiec sportowiec, Wezel wezel){
        super(czas, sportowiec);
        this.wezel = wezel;
    }

    @Override
    public Zdarzenie[] wykonaj(){
        Zdarzenie decyzja = sportowiec().decyzja(wezel);

        return new Zdarzenie[]{ decyzja };
    }
}
