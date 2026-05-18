package pl.projekt.transferdanych;


// Klasy DTO (transferu danych) służą jako zwykłe "pojemniki na dane".
// Ich celem jest przetrzymanie danych pomiędzy fazą ich wczytywania,
// a ich przetwarzania.
public class WezelDTO {
    private final int numer;
    private final int wysokosc;
    private final int x;
    private final int y;
    private final boolean skomunikowany;

    public WezelDTO(int numer, int wysokosc, int x, int y, boolean skomunikowany) {
        this.numer = numer;
        this.wysokosc = wysokosc;
        this.x = x;
        this.y = y;
        this.skomunikowany = skomunikowany;
    }

    public int numer() { return numer; }
    public int wysokosc() { return wysokosc; }
    public int x() { return x; }
    public int y() { return y; }
    public boolean skomunikowany() { return skomunikowany; }
}
