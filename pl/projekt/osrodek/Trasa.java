package pl.projekt.osrodek;

public class Trasa extends Polaczenie {
    private static int AKTUALNY_NUMER;
    private int poziomTrudnosci; // 0-10
    private double odpornosc; // (0, 1]
    private double bazowaAtrakcyjnosc; // [0, 1]

    private static Wezel dajStart(Wezel w1, Wezel w2){
        return w1.wysokosc() > w2.wysokosc() ? w1 : w2;
    }
    private static Wezel dajKoniec(Wezel w1, Wezel w2){
        return w1.wysokosc() < w2.wysokosc() ? w1 : w2;
    }
    public Trasa(Wezel wezel1, Wezel wezel2, int czasPrzejazdu, int poziomTrudnosci, double odpornosc, double bazowaAtrakcyjnosc){
        super(AKTUALNY_NUMER, TypPolaczenia.TRASA, dajStart(wezel1, wezel2), dajKoniec(wezel1, wezel2), czasPrzejazdu);
        AKTUALNY_NUMER++;

        this.poziomTrudnosci = poziomTrudnosci;
        this.odpornosc = odpornosc;
        this.bazowaAtrakcyjnosc = bazowaAtrakcyjnosc;

        start().dodajTrase(this);
    }
    public int poziomTrudnosci(){
        return poziomTrudnosci;
    }

    // [0, 1]
    public double atrakcyjnoscNawierzchni(){
        return bazowaAtrakcyjnosc + (1 - bazowaAtrakcyjnosc) * Math.pow(odpornosc, liczbaPrzejazdow());
    }

}
