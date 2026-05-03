package pl.projekt.zdarzenia.wyciag;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.zdarzenia.Zdarzenie;

public abstract class ZdarzenieWyciagu extends Zdarzenie {
    private Wyciag wyciag;

    public ZdarzenieWyciagu(Czas czas, Wyciag wyciag){
        super(czas, 1);
        this.wyciag = wyciag;
    }
    public Wyciag wyciag(){
        return wyciag;
    }
}
