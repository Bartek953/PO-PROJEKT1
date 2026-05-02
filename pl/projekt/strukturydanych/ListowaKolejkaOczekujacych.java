package pl.projekt.strukturydanych;

import pl.projekt.sportowcy.Sportowiec;

public class ListowaKolejkaOczekujacych implements KolejkaOczekujacych{
    private ElementKolejkiOczekujacych poczatek, koniec;

    public ListowaKolejkaOczekujacych(){
        poczatek = null;
        koniec = null;
    }
    @Override
    public void dodajNaKoniec(Sportowiec sportowiec) {
        ElementKolejkiOczekujacych nowyKoniec = new ElementKolejkiOczekujacych(sportowiec, null);

        if (pusta()){
            poczatek = nowyKoniec;
            koniec = nowyKoniec;
        }
        else {
            koniec.ustawNastepny(nowyKoniec);
            koniec = nowyKoniec;
        }
    }

    @Override
    public Sportowiec pierwszy() {
        if (pusta()){
            throw new RuntimeException("Pusta kolejka oczekujących");
        }
        return poczatek.sportowiec();
    }

    @Override
    public void usunPierwszy() {
        if (pusta()){
            return;
        }
        if (poczatek == koniec){
            poczatek = null;
            koniec = null;
        }
        else {
            ElementKolejkiOczekujacych nowyPoczatek = poczatek.nastepny();
            poczatek.usun();
            poczatek = nowyPoczatek;
        }
    }

    @Override
    public boolean pusta() {
        return poczatek == null;
    }
}
