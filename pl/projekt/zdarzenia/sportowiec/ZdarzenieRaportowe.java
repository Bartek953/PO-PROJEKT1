package pl.projekt.zdarzenia.sportowiec;

import pl.projekt.cechy.Czas;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

public abstract class ZdarzenieRaportowe extends ZdarzenieSportowca {
    public ZdarzenieRaportowe(Czas czas, Sportowiec sportowiec, Priorytet priorytet) {
        super(czas, sportowiec, priorytet);
    }

    public abstract String raportuj();

    @Override
    public Zdarzenie[] wykonaj(){
        if (sportowiec().sledzony()){
            System.out.println(raportuj());
        }
        return new Zdarzenie[0];
    }
}
