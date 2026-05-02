package pl.projekt.strukturydanych;

import pl.projekt.zdarzenia.Zdarzenie;

import static java.lang.System.arraycopy;

public class ListaZdarzen {
    private static final int WSP_REALOKACJI = 2;
    private static final int ROZMIAR_POCZATKOWY = 4;
    private Zdarzenie[] listaZdarzen;
    private int rozmiar;

    public ListaZdarzen(){
        listaZdarzen = new Zdarzenie[ROZMIAR_POCZATKOWY];
        rozmiar = 0;
    }

    public int rozmiar(){
        return rozmiar;
    }

    public boolean pusta(){
        return rozmiar() == 0;
    }

    private void realokuj(){
        int nowyRozmiar = listaZdarzen.length * WSP_REALOKACJI;

        // potencjalnie blad przydzialu pamieci
        Zdarzenie[] nowaLista = new Zdarzenie[nowyRozmiar];
        arraycopy(listaZdarzen, 0, nowaLista, 0, rozmiar);

        listaZdarzen = nowaLista;
    }

    public void dodaj(Zdarzenie zdarzenie){
        if (rozmiar == listaZdarzen.length){
            realokuj();
        }
        listaZdarzen[rozmiar] = zdarzenie;
        rozmiar++;
    }

    public Zdarzenie daj(int indeks){
        if (indeks < 0 || indeks >= rozmiar){
            throw new ArrayIndexOutOfBoundsException();
        }
        return listaZdarzen[indeks];
    }

    public Zdarzenie[] naTablice(){
        Zdarzenie[] wynik = new Zdarzenie[rozmiar];
        arraycopy(listaZdarzen, 0, wynik, 0, rozmiar);

        return wynik;
    }
}
