package pl.projekt.strukturydanych;

import pl.projekt.sportowcy.Sportowiec;

public class ElementKolejkiOczekujacych {
    private Sportowiec sportowiec;
    private ElementKolejkiOczekujacych nastepny;

    public ElementKolejkiOczekujacych(Sportowiec sportowiec, ElementKolejkiOczekujacych nastepny){
        this.sportowiec = sportowiec;
        this.nastepny = nastepny;
    }
    public Sportowiec sportowiec(){
        return sportowiec;
    }
    public ElementKolejkiOczekujacych nastepny(){
        return nastepny;
    }
    public void ustawNastepny(ElementKolejkiOczekujacych nastepny){
        this.nastepny = nastepny;
    }
    public void usun(){
        sportowiec = null;
        nastepny = null;
    }
}
