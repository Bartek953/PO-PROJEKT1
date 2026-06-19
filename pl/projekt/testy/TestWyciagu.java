package pl.projekt.testy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.Sportowiec;
import pl.projekt.sportowcy.SportowiecLokalny;
import pl.projekt.zdarzenia.Zdarzenie;
import pl.projekt.zdarzenia.sportowiec.ZdarzeniePoczatekWjazdu;
import pl.projekt.zdarzenia.sportowiec.ZdarzenieUstawienieWKolejce;
import pl.projekt.zdarzenia.wyciag.ZdarzeniePrzyjazdWyciagu;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
1. Jest wyciąg o pojemności 3 z pustą kolejką. Ustawia się do niego czterech sportowców.
Następuje odjazd wagonika. Należy sprawdzić, że przejazd wykonało trzech sportowców
(jeden z nich się nie zmieścił) - licznik odjazdów wyciągu zwiększył się o 3.
2. Do wyciągu o pojemności 3 ustawia się dwóch sportowców, następuje odjazd wagonika.
Należy sprawdzić że obydwaj odjechali.
3. Do wyciągu ustawia się kolejno czterech sportowców. Odjeżdża wagonik, a następnie
ustawia się jeszcze jeden. Należy sprawdzić że poprawnie obliczono maksymalną długość
kolejki - 4.
 */

public class TestWyciagu {
    private Wyciag stworzWyciagTestowy(int maksLiczbaOsob){
        Wezel start = new Wezel(0, 0, 0, 0, false);
        Wezel koniec = new Wezel(1, 1, 1, 1, false);
        return new Wyciag(0, start, koniec, 600, 60, maksLiczbaOsob);
    }
    private Sportowiec stworzSportowcaTestowego(int numer){
        return new SportowiecLokalny(numer, 5, 0.5, 0.5, 0.5,
                0.5, 0.5, false, null, null);
    }

    /*
    Test 1:
    Jest wyciąg o pojemności 3 z pustą kolejką. Ustawia się do niego czterech sportowców.
    Następuje odjazd wagonika. Należy sprawdzić, że przejazd wykonało trzech sportowców
    (jeden z nich się nie zmieścił) - licznik odjazdów wyciągu zwiększył się o 3.

    Test 2:
    Do wyciągu o pojemności 3 ustawia się dwóch sportowców, następuje odjazd wagonika.
    Należy sprawdzić że obydwaj odjechali.

    Parametry: pojemność wyciągu, liczba sportowców
     */
    @ParameterizedTest
    @CsvSource({
            "3, 4",
            "3, 2"
    })
    void testOdjazdu(int pojemnoscWyciagu, int liczbaSportowcow){
        Wyciag wyciag = stworzWyciagTestowy(pojemnoscWyciagu);
        Sportowiec[] sportowcy = new Sportowiec[liczbaSportowcow];

        //ustawienie sportowców w kolejce
        for (int i = 0; i < liczbaSportowcow; i++){
            sportowcy[i] = stworzSportowcaTestowego(i);
            ZdarzenieUstawienieWKolejce ustawienieWKolejce =
                    new ZdarzenieUstawienieWKolejce(new Czas(9, 0, 0), sportowcy[i], wyciag);

            ustawienieWKolejce.wykonaj();
        }
        assertEquals(liczbaSportowcow, wyciag.aktualnaDlugoscKolejki());

        // symulacja odjazdu wyciagu
        ZdarzeniePrzyjazdWyciagu przyjazd = new ZdarzeniePrzyjazdWyciagu(new Czas(9, 10, 0), wyciag);

        List<Zdarzenie> odjazdy = przyjazd.wykonaj();

        for (Zdarzenie zdarzenie : odjazdy){
            if (zdarzenie instanceof ZdarzeniePoczatekWjazdu){
                List<Zdarzenie> nastepne = zdarzenie.wykonaj();
                nastepne.get(0).wykonaj();
            }
        }


        assertEquals(Math.max(liczbaSportowcow - pojemnoscWyciagu, 0), wyciag.aktualnaDlugoscKolejki());
        assertEquals(Math.min(pojemnoscWyciagu, liczbaSportowcow), wyciag.liczbaPrzejazdow());
    }

    /*
    Test 3:
    Do wyciągu ustawia się kolejno czterech sportowców. Odjeżdża wagonik, a następnie
    ustawia się jeszcze jeden. Należy sprawdzić że poprawnie obliczono maksymalną długość
    kolejki - 4.
     */
    @Test
    void testDlugosciKolejki(){
        Wyciag wyciag = stworzWyciagTestowy(3);
        assertEquals(0, wyciag.maksymalnaDlugoscKolejki());

        // Ustawienie 4 sportowców
        for (int i = 0; i < 4; i++){
            Sportowiec sportowiec = stworzSportowcaTestowego(i);
            Zdarzenie ustawienie = new ZdarzenieUstawienieWKolejce(new Czas(9, 0, 0), sportowiec, wyciag);
            ustawienie.wykonaj();
        }
        // Odjazd kolejki
        Zdarzenie odjazd = new ZdarzeniePrzyjazdWyciagu(new Czas(9, 10, 0), wyciag);
        odjazd.wykonaj();

        // Ustawienie kolejnego sportowca
        Sportowiec sportowiec = stworzSportowcaTestowego(4);
        Zdarzenie ustawienie = new ZdarzenieUstawienieWKolejce(new Czas(9, 11, 0), sportowiec, wyciag);
        ustawienie.wykonaj();

        assertEquals(4, wyciag.maksymalnaDlugoscKolejki());
    }
}
