package pl.projekt.transferdanych;

public class WyciagDTO {
    private final int numer;
    private final int numerWezlaPoczatkowego;
    private final int numerWezlaKoncowego;
    private final int odstepCzasuSekund;
    private final int maksymalnaLiczbaOsob;
    private final int czasPrzejazdSekund;

    public WyciagDTO(int numer, int numerWezlaPoczatkowego, int numerWezlaKoncowego,
                     int odstepCzasuSekund, int maksymalnaLiczbaOsob, int czasPrzejazdSekund) {
        this.numer = numer;
        this.numerWezlaPoczatkowego = numerWezlaPoczatkowego;
        this.numerWezlaKoncowego = numerWezlaKoncowego;
        this.odstepCzasuSekund = odstepCzasuSekund;
        this.maksymalnaLiczbaOsob = maksymalnaLiczbaOsob;
        this.czasPrzejazdSekund = czasPrzejazdSekund;
    }

    public int numer() { return numer; }
    public int numerWezlaPoczatkowego() { return numerWezlaPoczatkowego; }
    public int numerWezlaKoncowego() { return numerWezlaKoncowego; }
    public int odstepCzasuSekund() { return odstepCzasuSekund; }
    public int maksymalnaLiczbaOsob() { return maksymalnaLiczbaOsob; }
    public int czasPrzejazdSekund() { return czasPrzejazdSekund; }
}
