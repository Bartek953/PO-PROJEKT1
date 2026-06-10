package pl.projekt.transferdanych;


// Klasy DTO (transferu danych) służą jako zwykłe "pojemniki na dane".
// Ich celem jest przetrzymanie danych pomiędzy fazą ich wczytywania,
// a ich przetwarzania.
public class GrupaSportowcowDTO {
    private final int liczba;
    private final int poziomZaawansowania;
    private final double wspSpontanicznosci;
    private final double wspZnudzenia;
    private final boolean sledzeni;
    private final double wagaTrudnosci;
    private final double wagaNawierzchni;
    private final double wagaZnudzenia;
    private final int numerWezlaStartowego;
    private final String czasPrzyjazduPierwszego;
    private final int odstepCzasuSekund;
    private final String rodzaj;

    public GrupaSportowcowDTO(int liczba, int poziomZaawansowania, double wspSpontanicznosci,
                               double wspZnudzenia, boolean sledzeni, double wagaTrudnosci,
                               double wagaNawierzchni, double wagaZnudzenia,
                               int numerWezlaStartowego, String czasPrzyjazduPierwszego,
                               int odstepCzasuSekund, String rodzaj) {
        this.liczba = liczba;
        this.poziomZaawansowania = poziomZaawansowania;
        this.wspSpontanicznosci = wspSpontanicznosci;
        this.wspZnudzenia = wspZnudzenia;
        this.sledzeni = sledzeni;
        this.wagaTrudnosci = wagaTrudnosci;
        this.wagaNawierzchni = wagaNawierzchni;
        this.wagaZnudzenia = wagaZnudzenia;
        this.numerWezlaStartowego = numerWezlaStartowego;
        this.czasPrzyjazduPierwszego = czasPrzyjazduPierwszego;
        this.odstepCzasuSekund = odstepCzasuSekund;
        this.rodzaj = rodzaj;
    }

    public int liczba() { return liczba; }
    public int poziomZaawansowania() { return poziomZaawansowania; }
    public double wspSpontanicznosci() { return wspSpontanicznosci; }
    public double wspZnudzenia() { return wspZnudzenia; }
    public boolean sledzeni() { return sledzeni; }
    public double wagaTrudnosci() { return wagaTrudnosci; }
    public double wagaNawierzchni() { return wagaNawierzchni; }
    public double wagaZnudzenia() { return wagaZnudzenia; }
    public int numerWezlaStartowego() { return numerWezlaStartowego; }
    public String czasPrzyjazduPierwszego() { return czasPrzyjazduPierwszego; }
    public int odstepCzasuSekund() { return odstepCzasuSekund; }
    public String rodzaj() { return rodzaj; }
}
