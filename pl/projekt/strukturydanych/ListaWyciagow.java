package pl.projekt.strukturydanych;

import pl.projekt.osrodek.Wyciag;

import static java.lang.System.arraycopy;

public class ListaWyciagow {
    private static final int WSP_REALOKACJI = 2;
    private static final int ROZMIAR_POCZATKOWY = 4;
    private Wyciag[] listaWyciagow;
    private int rozmiar;

    public ListaWyciagow(){
        listaWyciagow = new Wyciag[ROZMIAR_POCZATKOWY];
        rozmiar = 0;
    }

    public int rozmiar(){
        return rozmiar;
    }

    public boolean pusta(){
        return rozmiar == 0;
    }

    private void realokuj(){
        Wyciag[] nowaLista = new Wyciag[listaWyciagow.length * WSP_REALOKACJI];
        arraycopy(listaWyciagow, 0, nowaLista, 0, rozmiar);
        listaWyciagow = nowaLista;
    }

    public void dodaj(Wyciag wyciag){
        if (rozmiar == listaWyciagow.length) realokuj();
        listaWyciagow[rozmiar++] = wyciag;
    }

    public Wyciag daj(int indeks){
        if (indeks < 0 || indeks >= rozmiar) throw new ArrayIndexOutOfBoundsException();
        return listaWyciagow[indeks];
    }
}
