package pl.projekt.transferdanych;

public class GrupaSportowcowDTO {
    private final int liczba;
    private final int poziomZaawansowania;
    private final double wspSpontanicznosci;
    private final boolean sledzeni;
    private final double wagaTrudnosci;
    private final double wagaNawierzchni;
    private final int numerWezlaStartowego;
    private final String czasPrzyjazduPierwszego;
    private final int odstepCzasuSekund;

    public GrupaSportowcowDTO(int liczba, int poziomZaawansowania, double wspSpontanicznosci,
                               boolean sledzeni, double wagaTrudnosci, double wagaNawierzchni,
                               int numerWezlaStartowego, String czasPrzyjazduPierwszego,
                               int odstepCzasuSekund) {
        this.liczba = liczba;
        this.poziomZaawansowania = poziomZaawansowania;
        this.wspSpontanicznosci = wspSpontanicznosci;
        this.sledzeni = sledzeni;
        this.wagaTrudnosci = wagaTrudnosci;
        this.wagaNawierzchni = wagaNawierzchni;
        this.numerWezlaStartowego = numerWezlaStartowego;
        this.czasPrzyjazduPierwszego = czasPrzyjazduPierwszego;
        this.odstepCzasuSekund = odstepCzasuSekund;
    }

    public int liczba() { return liczba; }
    public int poziomZaawansowania() { return poziomZaawansowania; }
    public double wspSpontanicznosci() { return wspSpontanicznosci; }
    public boolean sledzeni() { return sledzeni; }
    public double wagaTrudnosci() { return wagaTrudnosci; }
    public double wagaNawierzchni() { return wagaNawierzchni; }
    public int numerWezlaStartowego() { return numerWezlaStartowego; }
    public String czasPrzyjazduPierwszego() { return czasPrzyjazduPierwszego; }
    public int odstepCzasuSekund() { return odstepCzasuSekund; }
}
