package pl.projekt.zdarzenia.wyciag;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.zdarzenia.Priorytet;
import pl.projekt.zdarzenia.Zdarzenie;

public abstract class ZdarzenieWyciagu extends Zdarzenie {
    private final Wyciag wyciag;

    public ZdarzenieWyciagu(Czas czas, Wyciag wyciag, Priorytet priorytet){
        super(czas, priorytet);
        this.wyciag = wyciag;
    }
    public Wyciag wyciag(){
        return wyciag;
    }
}
