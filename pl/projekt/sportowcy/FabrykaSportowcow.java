package pl.projekt.sportowcy;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wezel;

public class FabrykaSportowcow {
    public static Sportowiec stworzSportowca(int numer,
                                             int poziomZaawansowania,
                                             double wspSpontanicznosci,
                                             double wspZnudzenia,
                                             double wagaTrudnosci,
                                             double wagaNawierzchni,
                                             double wagaZnudzenia,
                                             boolean sledzony,
                                             Wezel wezelStartowy,
                                             Czas czasStartu,
                                             String rodzaj){
        return switch (rodzaj){
            case "L" -> new SportowiecLokalny(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, wagaTrudnosci,
                        wagaNawierzchni, wagaZnudzenia, sledzony, wezelStartowy, czasStartu);
            case "Z" -> new SportowiecZachlanny(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, wagaTrudnosci,
                        wagaNawierzchni, wagaZnudzenia, sledzony, wezelStartowy, czasStartu);
            case "K" -> new SportowiecKolekcjoner(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, wagaTrudnosci,
                        wagaNawierzchni, wagaZnudzenia, sledzony, wezelStartowy, czasStartu);
            default -> throw new IllegalArgumentException("Nieznany rodzaj sportowca: " + rodzaj);
        };
    }
}
