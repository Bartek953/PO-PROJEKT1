package pl.projekt.zdarzenia;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;

public abstract class Zdarzenie {
    private Czas czas;

    public Zdarzenie(Czas czas){
        this.czas = czas;
    }

    public Czas czas(){
        return czas;
    }

    public abstract Zdarzenie[] wykonaj();
}
