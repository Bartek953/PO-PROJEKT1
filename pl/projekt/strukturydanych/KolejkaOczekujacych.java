package pl.projekt.strukturydanych;

import pl.projekt.sportowcy.Sportowiec;

public interface KolejkaOczekujacych {
    void dodajNaKoniec(Sportowiec sportowiec);
    Sportowiec pierwszy();
    void usunPierwszy();
    boolean pusta();
}
