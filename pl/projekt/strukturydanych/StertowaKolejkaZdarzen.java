package pl.projekt.strukturydanych;

import pl.projekt.zdarzenia.Zdarzenie;

// Implementacja sterty (heap).
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
    public int rozmiar(){
        return licznikZdarzen;
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

        // Zachowanie niezmiennika - trzeba naprawić trasę od korzenia do miejsca wstawienia:
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

        // Zachowanie niezmiennika:
        int pozycja = 1;
        while (2 * pozycja <= licznikZdarzen) {
            // Wybieramy czy lewy czy prawy sen staje się nowym "lokalnym korzeniem":
            int lewyPozycja = 2 * pozycja;
            int prawyPozycja = 2 * pozycja + 1;
            Zdarzenie lewy = listaZdarzen.daj(lewyPozycja);

            Zdarzenie prawy = null;
            if (prawyPozycja <= licznikZdarzen){
                prawy = listaZdarzen.daj(prawyPozycja);
            }

            int minPozycja = pozycja;
            if (lewy == null && prawy == null) {
                break;
            }
            else if (lewy == null) {
                minPozycja = prawyPozycja;
            }
            else if (prawy == null) {
                minPozycja = lewyPozycja;
            }
            else if (Zdarzenie.wczesniejsze(lewy, prawy)) {
                minPozycja = lewyPozycja;
            }
            else {
                minPozycja = prawyPozycja;
            }

            if (Zdarzenie.wczesniejsze(listaZdarzen.daj(minPozycja), listaZdarzen.daj(pozycja))){
                listaZdarzen.zamien(minPozycja, pozycja);
                pozycja = minPozycja;
            }
            else {
                break;
            }
        }
    }
}
