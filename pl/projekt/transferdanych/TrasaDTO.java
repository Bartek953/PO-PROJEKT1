package pl.projekt.transferdanych;


// Klasy DTO (transferu danych) służą jako zwykłe "pojemniki na dane".
// Ich celem jest przetrzymanie danych pomiędzy fazą ich wczytywania,
// a ich przetwarzania.
public class TrasaDTO {
    private final int numer;
    private final int numerWezlaPoczatkowego;
    private final int numerWezlaKoncowego;
    private final int poziomTrudnosci;
    private final int czasPrzejazdSekund;
    private final double bazowaAtrakcyjnosc;
    private final double odpornosc;

    public TrasaDTO(int numer, int numerWezlaPoczatkowego, int numerWezlaKoncowego,
                    int poziomTrudnosci, int czasPrzejazdSekund,
                    double bazowaAtrakcyjnosc, double odpornosc) {
        this.numer = numer;
        this.numerWezlaPoczatkowego = numerWezlaPoczatkowego;
        this.numerWezlaKoncowego = numerWezlaKoncowego;
        this.poziomTrudnosci = poziomTrudnosci;
        this.czasPrzejazdSekund = czasPrzejazdSekund;
        this.bazowaAtrakcyjnosc = bazowaAtrakcyjnosc;
        this.odpornosc = odpornosc;
    }

    public int numer() { return numer; }
    public int numerWezlaPoczatkowego() { return numerWezlaPoczatkowego; }
    public int numerWezlaKoncowego() { return numerWezlaKoncowego; }
    public int poziomTrudnosci() { return poziomTrudnosci; }
    public int czasPrzejazdSekund() { return czasPrzejazdSekund; }
    public double bazowaAtrakcyjnosc() { return bazowaAtrakcyjnosc; }
    public double odpornosc() { return odpornosc; }
}
