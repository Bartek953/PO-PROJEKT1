package pl.projekt.strukturydanych;

import pl.projekt.zdarzenia.Zdarzenie;

import static java.lang.System.arraycopy;

public class ListaZdarzen {
    private int wspRealokacji;
    private Zdarzenie[] listaZdarzen;
    private int rozmiar;

    public ListaZdarzen(int rozmiarPoczatkowy, int wspRealokacji){
        listaZdarzen = new Zdarzenie[rozmiarPoczatkowy];
        rozmiar = 0;
        this.wspRealokacji = wspRealokacji;
    }

    public int rozmiar(){
        return rozmiar;
    }

    public boolean pusta(){
        return rozmiar() == 0;
    }

    private void realokuj(){
        int nowyRozmiar = listaZdarzen.length * wspRealokacji;

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

    public void zmien(int indeks, Zdarzenie noweZdarzenie){
        if (indeks < 0 || indeks >= rozmiar){
            throw new ArrayIndexOutOfBoundsException();
        }
        listaZdarzen[indeks] = noweZdarzenie;
    }
    public void zamien(int indeks1, int indeks2){
        Zdarzenie akumulator = daj(indeks1);
        zmien(indeks1, daj(indeks2));
        zmien(indeks2, akumulator);
    }
}
