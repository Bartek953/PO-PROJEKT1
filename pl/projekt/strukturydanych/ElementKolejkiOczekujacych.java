package pl.projekt.strukturydanych;

import pl.projekt.sportowcy.Sportowiec;

public class ElementKolejkiOczekujacych {
    private Sportowiec sportowiec;
    private ElementKolejkiOczekujacych nastepny;
    private ElementKolejkiOczekujacych poprzedni;

    public ElementKolejkiOczekujacych(Sportowiec sportowiec, ElementKolejkiOczekujacych poprzedni, ElementKolejkiOczekujacych nastepny){
        this.sportowiec = sportowiec;
        this.poprzedni = poprzedni;
        this.nastepny = nastepny;
    }
    public Sportowiec sportowiec(){
        return sportowiec;
    }
    public ElementKolejkiOczekujacych nastepny(){
        return nastepny;
    }
    public ElementKolejkiOczekujacych poprzedni(){
        return poprzedni;
    }
    public void ustawNastepny(ElementKolejkiOczekujacych nastepny){
        this.nastepny = nastepny;
    }
    public void ustawPoprzedni(ElementKolejkiOczekujacych poprzedni){
        this.poprzedni = poprzedni;
    }
    public void usun(){
        sportowiec = null;
        nastepny = null;
        poprzedni = null;
    }
}
