package pl.projekt.sportowcy;

public class Wagi {
    // wagi są w [0, 1]
    private double dopasowanieTrudnosci;
    private double wyrownanieNawierzchni;

    public Wagi(double dopasowanieTrudnosci, double wyrownanieNawierzchni){
        this.dopasowanieTrudnosci = dopasowanieTrudnosci;
        this.wyrownanieNawierzchni = wyrownanieNawierzchni;
    }
    public double dopasowanieTrudnosci(){
        return dopasowanieTrudnosci;
    }
    public double getWyrownanieNawierzchni(){
        return wyrownanieNawierzchni;
    }

    // Liczy łączną atrakcyjność trasy - [0, 1]
    public double lacznaAtrakcyjnosc(double atrakcyjnoscTrudnosci, double atrakcyjnoscNawierzchni){
        return this.dopasowanieTrudnosci * atrakcyjnoscTrudnosci + wyrownanieNawierzchni * atrakcyjnoscNawierzchni;
    }
}
