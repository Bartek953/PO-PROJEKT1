package pl.projekt.testy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.projekt.osrodek.Polaczenie;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;
import pl.projekt.sportowcy.BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/*
Uwaga, ponieważ w treści bfs szuka trasy, nie wierzchołka, to mój bfs również szuka tras.
(W wirtualnym grafie węzły to krawędzie, połączenia to wierzchołki).
Wymagało to delikatnej modyfikacji tryby testowania - sprawdzam krawędzie którymi przechodzi.
 */
public class TestBFS {
    private List<Wezel> wierzcholki;
    private List<Polaczenie> krawedzie;

    private void stworzWierzcholki(int liczba){
        for (int i = 0; i < liczba; i++){
            wierzcholki.add(new Wezel(i, 0, 0, 0, false));
        }
    }

    /*
    Typy:
        0 - trasa
        1 - wyciag
     */
    private void stworzKrawedz(int typ, int start, int koniec, int nr){
        if (typ == 0){
            krawedzie.add(new Trasa(nr, wierzcholki.get(start), wierzcholki.get(koniec), 0, 0, 0, 0));
        }
        else {
            krawedzie.add(new Wyciag(nr, wierzcholki.get(start), wierzcholki.get(koniec), 0, 0, 1));
        }
    }

    @BeforeEach
    void budujGraf(){
        wierzcholki = new ArrayList<>();
        krawedzie = new ArrayList<>();
        stworzWierzcholki(6);

        int[][] polaczenia = {
                {0, 1, 0}, //0 trasa 1->0
                {1, 0, 1}, //1 wyciag 0->1
                {0, 1, 2}, //2 trasa 1->2
                {0, 2, 0}, //3 trasa 2->0
                {1, 2, 4}, //4 wyciag 2->4
                {1, 2, 3}, //5 wyyciag 2->3
                {0, 3, 1}, //6 trasa 3->1
                {0, 3, 4}, //7 trasa 3->4
                {1, 4, 5}, //8 wyciag 4->5
                {0, 5, 3}, //9 trasa 5->3
                {0, 5, 3} //10 trasa 5->3
        };
        for (int i = 0; i < polaczenia.length; i++){
            stworzKrawedz(polaczenia[i][0], polaczenia[i][1], polaczenia[i][2], i);
        }
    }


    /*
    Test 1
    Ścieżkę z wierzchołka 0 do 4: prowadzącą poprzez 0, 1, 2,
    czyli od węzła 0 do wyciagu 4->5 (nr 8), przez połączenia 1, 2, 4
    4, odległość 3.
     */
    @Test
    void test1(){
        BFS bfs = new BFS(wierzcholki.get(0), 4);
        Stack<Polaczenie> plan = bfs.znajdzPlan();

        for (int nr_polaczenia : new int[]{1, 2, 4}){
            assertFalse(plan.isEmpty());
            Polaczenie polaczenie = plan.pop();
            assertEquals(nr_polaczenia, polaczenie.numer());
        }
        assertTrue(plan.isEmpty());
    }

    /*
    Test 2
    Bezpośrednią ścieżkę z 3 do 1.
     */
    @Test
    void test2(){
        BFS bfs = new BFS(wierzcholki.get(3), 1);
        Stack<Polaczenie> plan = bfs.znajdzPlan();

        assertFalse(plan.isEmpty());
        assertEquals(6, plan.pop().numer());
        assertTrue(plan.isEmpty());
    }

    /*
    Test 3
    Pustą ścieżkę z 2 do samego siebie (odległość 0).
     */
    @Test
    void test3(){
        BFS bfs = new BFS(wierzcholki.get(2), 2);
        Stack<Polaczenie> plan = bfs.znajdzPlan();
        assertTrue(plan.isEmpty());
    }

    /*
    Test 4
    Ścieżkę z 4 do 3: algorytm wybiera jedną z dwóch możliwych
    ścieżek długości 2.
     */
    @Test
    void test4(){
        BFS bfs = new BFS(wierzcholki.get(4), 3);
        Stack<Polaczenie> plan = bfs.znajdzPlan();

        // sprawdzam tylko odległość, bo ścieżka nie jest jednoznaczna
        for (int i = 0; i < 2; i++){
            assertFalse(plan.isEmpty());
            plan.pop();
        }
        assertTrue(plan.isEmpty());
    }
}
