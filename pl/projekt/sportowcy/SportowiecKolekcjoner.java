package pl.projekt.sportowcy;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Polaczenie;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;

import java.util.HashMap;
import java.util.Map;

public class SportowiecKolekcjoner extends SportowiecPlanujacy{
    private final Map<Trasa, Integer> mapaLiczbyZjazdow;
    public SportowiecKolekcjoner(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, Wagi wagi, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        super(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, wagi, sledzony, wezelStartowy, czasStartu);
        mapaLiczbyZjazdow = new HashMap<>();
    }
    public SportowiecKolekcjoner(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, double wagaTrudnosci, double wagaNawierzchni, double wagaZnudzenia, boolean sledzony, Wezel wezelStartowy, Czas czasStartu) {
        this(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, new Wagi(wagaTrudnosci, wagaNawierzchni, wagaZnudzenia), sledzony, wezelStartowy, czasStartu);
    }

    @Override
    protected void akcjaPoZjezdzie(Trasa trasa){
        int liczbaZjazdow = mapaLiczbyZjazdow.getOrDefault(trasa, 0) + 1;
        mapaLiczbyZjazdow.put(trasa, liczbaZjazdow);
    }

    @Override
    protected int porownaj(Trasa trasa1, int odl1, Trasa trasa2, int odl2){
        int liczbaZjazdow1 = mapaLiczbyZjazdow.getOrDefault(trasa1, 0);
        int liczbaZjazdow2 = mapaLiczbyZjazdow.getOrDefault(trasa2, 0);

        if (liczbaZjazdow1 != liczbaZjazdow2){
            return liczbaZjazdow1 < liczbaZjazdow2 ? 1 : -1;
        }
        if (odl1 != odl2){
            return odl1 < odl2 ? 1 : -1;
        }
        return atrakcyjnoscTrasy(trasa1) < atrakcyjnoscTrasy(trasa2) ? -1 : 1;
    }
}
