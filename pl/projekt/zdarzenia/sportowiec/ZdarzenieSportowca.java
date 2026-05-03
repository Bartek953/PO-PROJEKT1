package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wezel;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Zdarzenie;

// Zdarzenia dotyczące sportowca.
public abstract class ZdarzenieSportowca extends Zdarzenie {
    private Sportowiec sportowiec;

    public ZdarzenieSportowca(Czas czas, Sportowiec sportowiec){
        super(czas, 10);
        this.sportowiec = sportowiec;
    }
    public Sportowiec sportowiec(){
        return sportowiec;
    }
}
