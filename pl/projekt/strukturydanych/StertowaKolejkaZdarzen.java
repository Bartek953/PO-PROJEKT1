package pl.projekt.strukturydanych;

import pl.projekt.cechy.Czas;
import pl.projekt.zdarzenia.Zdarzenie;

public class StertowaKolejkaZdarzen implements KolejkaZdarzen{
    // Traktuje liste jako reprezentacje drzewa binarnego
    // Gdzie 1 jest korzeniem, a dziecmi ind jest (2*ind) oraz (2*ind + 1)
    // Niezmiennik: Sciezki od korzenia do liscia maja rosnacy czas
    private ListaZdarzen listaZdarzen;
    private int licznikZdarzen;

    public StertowaKolejkaZdarzen(){
        listaZdarzen = new ListaZdarzen(2, 2);
        listaZdarzen.dodaj(null); // ustawiamy 0
        licznikZdarzen = 0;
    }

    @Override
    public boolean pusta() {
        return licznikZdarzen == 0;
    }

    @Override
    public void dodaj(Zdarzenie zdarzenie) {
        if (licznikZdarzen + 1 == listaZdarzen.rozmiar()){
            listaZdarzen.dodaj(zdarzenie);
        }
        else {
            assert listaZdarzen.daj(licznikZdarzen + 1) == null;
            listaZdarzen.zmien(licznikZdarzen + 1, zdarzenie);
        }
        int pozycja = licznikZdarzen + 1;
        licznikZdarzen++;

        while (pozycja > 1 && Zdarzenie.wczesniejsze(listaZdarzen.daj(pozycja), listaZdarzen.daj(pozycja / 2))){
            listaZdarzen.zamien(pozycja, pozycja / 2);
            pozycja /= 2;
        }
    }

    @Override
    public Zdarzenie dajNajmniejszy() {
        if (pusta()){
            throw new RuntimeException();
        }
        return listaZdarzen.daj(1);
    }

    @Override
    public void usunNajmniejszy() {
        if (pusta()){
            return;
        }

        listaZdarzen.zmien(1, null);
        listaZdarzen.zamien(1, licznikZdarzen);

        licznikZdarzen--;

        int pozycja = 1;
        while (2 * pozycja <= licznikZdarzen) {
            int lewyPozycja = 2 * pozycja;
            int prawyPozycja = 2 * pozycja + 1;
            Zdarzenie lewy = listaZdarzen.daj(lewyPozycja);

            Zdarzenie prawy = null;
            if (prawyPozycja < listaZdarzen.rozmiar()){
                prawy = listaZdarzen.daj(prawyPozycja);
            }

            if (lewy == null && prawy == null) {
                break;
            }
            else if (lewy == null) {
                if (Zdarzenie.wczesniejsze(prawy, listaZdarzen.daj(pozycja))) {
                    listaZdarzen.zamien(pozycja, prawyPozycja);
                    pozycja = prawyPozycja;
                }
                else {
                    break;
                }
            }
            else if (prawy == null) {
                if (Zdarzenie.wczesniejsze(lewy, listaZdarzen.daj(pozycja))) {
                    listaZdarzen.zamien(pozycja, lewyPozycja);
                    pozycja = lewyPozycja;
                }
                else {
                    break;
                }
            }
            else if (Zdarzenie.wczesniejsze(lewy, prawy) && Zdarzenie.wczesniejsze(lewy, listaZdarzen.daj(pozycja))) {
                listaZdarzen.zamien(pozycja, lewyPozycja);
                pozycja = lewyPozycja;
            }
            else if (Zdarzenie.wczesniejsze(prawy, lewy) && Zdarzenie.wczesniejsze(prawy, listaZdarzen.daj(pozycja))){
                listaZdarzen.zamien(pozycja, prawyPozycja);
                pozycja = prawyPozycja;
            }
            else {
                break;
            }
        }
    }
}
