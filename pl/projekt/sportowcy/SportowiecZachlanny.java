package pl.projekt.sportowcy;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Polaczenie;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;

import java.util.LinkedList;

public class SportowiecZachlanny extends SportowiecPlanujacy {
    public SportowiecZachlanny(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, Wagi wagi, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        super(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, wagi, sledzony, wezelStartowy, czasStartu);
    }
    public SportowiecZachlanny(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, double wagaTrudnosci, double wagaNawierzchni, double wagaZnudzenia, boolean sledzony, Wezel wezelStartowy, Czas czasStartu) {
        this(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, new Wagi(wagaTrudnosci, wagaNawierzchni, wagaZnudzenia), sledzony, wezelStartowy, czasStartu);
    }

    @Override
    protected int porownaj(Trasa trasa1, int odl1, Trasa trasa2, int odl2){
        return atrakcyjnoscTrasy(trasa1) < atrakcyjnoscTrasy(trasa2) ? -1 : 1;
        // W treści jest napisane, że w razie remisu wybiera się dowolny.
    }
}
