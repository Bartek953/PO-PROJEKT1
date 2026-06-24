package pl.projekt.sportowcy;

public class Wagi {
    // wagi są w [0, 1]
    private final double dopasowanieTrudnosci;
    private final double wyrownanieNawierzchni;
    private final double wagaZnudzenia;

    public Wagi(double dopasowanieTrudnosci, double wyrownanieNawierzchni, double wagaZnudzenia){
        this.dopasowanieTrudnosci = dopasowanieTrudnosci;
        this.wyrownanieNawierzchni = wyrownanieNawierzchni;
        this.wagaZnudzenia = wagaZnudzenia;
    }
    public double dopasowanieTrudnosci(){
        return dopasowanieTrudnosci;
    }
    public double wyrownanieNawierzchni(){
        return wyrownanieNawierzchni;
    }
    public double wagaZnudzenia() {
        return wagaZnudzenia;
    }

    // Liczy łączną atrakcyjność trasy - [0, 1]
    public double lacznaAtrakcyjnosc(double atrakcyjnoscTrudnosci, double atrakcyjnoscNawierzchni, double znudzenie){
        return this.dopasowanieTrudnosci * atrakcyjnoscTrudnosci
                + wyrownanieNawierzchni * atrakcyjnoscNawierzchni
                + wagaZnudzenia * (1.0 - znudzenie);
    }
}
