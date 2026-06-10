package pl.projekt.strukturydanych;

import pl.projekt.zdarzenia.Zdarzenie;

public interface KolejkaZdarzen {
    boolean pusta();
    void dodaj(Zdarzenie zdarzenie);
    Zdarzenie dajNajmniejszy();
    void usunNajmniejszy();
}