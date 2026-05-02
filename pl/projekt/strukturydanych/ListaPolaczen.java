package pl.projekt.strukturydanych;

import pl.projekt.osrodek.Polaczenie;
import static java.lang.System.arraycopy;

public class ListaPolaczen {
    private static final int WSP_REALOKACJI = 2;
    private static final int ROZMIAR_POCZATKOWY = 4;
    private Polaczenie[] listaPolaczen;
    private int rozmiar;

    public ListaPolaczen(){
        listaPolaczen = new Polaczenie[ROZMIAR_POCZATKOWY];
        rozmiar = 0;
    }

    public int rozmiar(){
        return rozmiar;
    }

    public boolean pusta(){
        return rozmiar() == 0;
    }

    private void realokuj(){
        int nowyRozmiar = listaPolaczen.length * WSP_REALOKACJI;

        // potencjalnie blad przydzialu pamieci
        Polaczenie[] nowaLista = new Polaczenie[nowyRozmiar];
        arraycopy(listaPolaczen, 0, nowaLista, 0, rozmiar);

        listaPolaczen = nowaLista;
    }

    public void dodaj(Polaczenie polaczenie){
        if (rozmiar == listaPolaczen.length){
            realokuj();
        }
        listaPolaczen[rozmiar] = polaczenie;
        rozmiar++;
    }

    public Polaczenie daj(int indeks){
        if (indeks < 0 || indeks >= rozmiar){
            throw new ArrayIndexOutOfBoundsException();
        }
        return listaPolaczen[indeks];
    }
}
