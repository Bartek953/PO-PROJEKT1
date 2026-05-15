package pl.projekt.strukturydanych;

import pl.projekt.osrodek.Trasa;

import static java.lang.System.arraycopy;

public class ListaTras {
    private static final int WSP_REALOKACJI = 2;
    private static final int ROZMIAR_POCZATKOWY = 4;
    private Trasa[] listaTras;
    private int rozmiar;

    public ListaTras(){
        listaTras = new Trasa[ROZMIAR_POCZATKOWY];
        rozmiar = 0;
    }

    public int rozmiar(){
        return rozmiar;
    }

    public boolean pusta(){
        return rozmiar == 0;
    }

    private void realokuj(){
        Trasa[] nowaLista = new Trasa[listaTras.length * WSP_REALOKACJI];
        arraycopy(listaTras, 0, nowaLista, 0, rozmiar);
        listaTras = nowaLista;
    }

    public void dodaj(Trasa trasa){
        if (rozmiar == listaTras.length) realokuj();
        listaTras[rozmiar++] = trasa;
    }

    public Trasa daj(int indeks){
        if (indeks < 0 || indeks >= rozmiar) throw new ArrayIndexOutOfBoundsException();
        return listaTras[indeks];
    }
}
