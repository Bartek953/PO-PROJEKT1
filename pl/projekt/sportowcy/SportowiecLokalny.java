package pl.projekt.sportowcy;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieUstawienieWKolejce;

public class SportowiecLokalny extends Sportowiec{
    public SportowiecLokalny(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, Wagi wagi, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        super(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, wagi, sledzony, wezelStartowy, czasStartu);
    }
    public SportowiecLokalny(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, double wagaTrudnosci, double wagaNawierzchni, double wagaZnudzenia, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        this(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, new Wagi(wagaTrudnosci, wagaNawierzchni, wagaZnudzenia), sledzony, wezelStartowy, czasStartu);
    }

    // Znajduje najlepszą trasę wychodzącą z danego węzła (tylko z niego - nie patrzy na wyciągi).
    // Zwraca null jeśli węzeł nie ma tras.
    public Trasa wybierzNajlepszaTrase(Wezel wezel){
        Trasa najlepszaTrasa = null;

        for (int i = 0; i < wezel.trasy().size(); i++){
            Trasa aktTrasa = wezel.trasy().get(i);

            if (najlepszaTrasa == null || atrakcyjnoscTrasy(najlepszaTrasa) < atrakcyjnoscTrasy(aktTrasa)){
                najlepszaTrasa = aktTrasa;
            }
        }
        return najlepszaTrasa;
    }

    // Podejmuje decyzję o akcji sportowca (wybór wyciągu lub trasy).
    @Override
    public Zdarzenie decyzja(Wezel wezel, Czas czas){
        // Spontaniczny wybór sportowca:
        if (losujDecyzje()){
            return losowaDecyzja(wezel, czas);
        }

        Trasa najlepszaTrasa = wybierzNajlepszaTrase(wezel);
        Wyciag najlepszyWyciag = null;

        for (int i = 0; i < wezel.wyciagi().size(); i++){
            Wyciag wyciag = wezel.wyciagi().get(i);
            Trasa aktTrasa = wybierzNajlepszaTrase(wyciag.koniec());
            if (najlepszaTrasa == null || (aktTrasa != null && atrakcyjnoscTrasy(najlepszaTrasa) < atrakcyjnoscTrasy(aktTrasa))){
                najlepszaTrasa = aktTrasa;
                najlepszyWyciag = wyciag;
            }
        }

        if (najlepszaTrasa == null && wezel.wyciagi().size() != 0){
            //return new ZdarzenieUstawienieWKolejce(czas, this, wezel.wyciagi().get(0));
            return wezel.wyciagi().get(0).stworzZdarzenie(czas, this);
        }
        else if (najlepszaTrasa == null){
            throw new RuntimeException("Graf nie jest silnie spójny!");
        }

        if (najlepszaTrasa.start() == wezel){
            return najlepszaTrasa.stworzZdarzenie(czas, this);
        }
        else {
            return najlepszyWyciag.stworzZdarzenie(czas, this);
        }
    }

}
