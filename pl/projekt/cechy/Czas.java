package pl.projekt.cechy;


public class Czas {
    private int godzina;
    private int minuta;
    private int sekunda;

    public Czas(int godzina, int minuta, int sekunda){
        if (godzina < 0 || minuta < 0 || sekunda < 0){
            throw new RuntimeException("Niepoprawny format czasu");
        }
        minuta += sekunda / 60;
        this.sekunda = sekunda % 60;

        godzina += minuta / 60;
        this.minuta = minuta % 60;

        if (godzina > 23){
            throw new RuntimeException("Niepoprawny format czasu");
        }
        this.godzina = godzina;
    }
    // Konstruktor od formatu hh:mm:ss
    public Czas(String napis){
        //format HH:MM:SS
        this(Integer.parseInt(napis.split(":")[0]),
             Integer.parseInt(napis.split(":")[1]),
             Integer.parseInt(napis.split(":")[2]));
    }

    public Czas(Czas czas){
        this(czas.godzina(), czas.minuta(), czas.sekunda());
    }

    public int godzina(){
        return godzina;
    }
    public int minuta(){
        return minuta;
    }
    public int sekunda(){
        return sekunda;
    }

    // Podaje czas w sekundach od 00:00:00
    public int naSekundy(){
        return sekunda() + 60 * (minuta() + 60 * godzina());
    }


    // Wypisuje czas w formacie hh:mm:ss
    @Override
    public String toString(){
        StringBuilder wynik = new StringBuilder("");

        if (godzina() < 10){
            wynik.append(0);
        }
        wynik.append(godzina());
        wynik.append(":");

        if (minuta() < 10){
            wynik.append(0);
        }
        wynik.append(minuta());
        wynik.append(":");

        if (sekunda() < 10){
            wynik.append(0);
        }
        wynik.append(sekunda());

        return wynik.toString();
    }

    public static boolean rowny(Czas czas1, Czas czas2){
        return czas1.naSekundy() == czas2.naSekundy();
    }
    public static boolean mniejszy(Czas czas1, Czas czas2){
        return czas1.naSekundy() < czas2.naSekundy();
    }
    public static Czas dodaj(Czas czas1, Czas czas2){
        return new Czas(
                czas1.godzina() + czas2.godzina(),
                czas1.minuta() + czas2.minuta(),
                czas1.sekunda() + czas2.sekunda()
        );
    }

}
