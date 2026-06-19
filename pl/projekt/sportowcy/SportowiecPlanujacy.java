package pl.projekt.sportowcy;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Polaczenie;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.zdarzenia.Zdarzenie;

import java.util.Stack;

public abstract class SportowiecPlanujacy extends Sportowiec{
    private Stack<Polaczenie> aktualnyPlan;

    public SportowiecPlanujacy(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, Wagi wagi, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        super(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, wagi, sledzony, wezelStartowy, czasStartu);
        aktualnyPlan = new Stack<>();
    }
    public SportowiecPlanujacy(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, double wagaTrudnosci, double wagaNawierzchni, double wagaZnudzenia, boolean sledzony, Wezel wezelStartowy, Czas czasStartu) {
        this(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, new Wagi(wagaTrudnosci, wagaNawierzchni, wagaZnudzenia), sledzony, wezelStartowy, czasStartu);
    }

    public boolean wykonujePlan(){
        return !aktualnyPlan.isEmpty();
    }
    private void stworzNowyPlan(Czas czas, Wezel wezel){
        BFS bfs = new BFS(wezel, this::porownaj);
        aktualnyPlan = bfs.znajdzPlan();
    }

    @Override
    public Zdarzenie decyzja(Wezel wezel, Czas czas){
        if (!wykonujePlan()){
            if (losujDecyzje()){
                return losowaDecyzja(wezel, czas);
            }
            else {
                stworzNowyPlan(czas, wezel);
            }
        }

        if (aktualnyPlan.isEmpty()){
            throw new RuntimeException("Pusta Kolejka Planu");
        }

        Polaczenie nastepnePolaczenie = aktualnyPlan.pop();

        if (nastepnePolaczenie == null || nastepnePolaczenie.start() != wezel){
            throw new RuntimeException("Niespełniony niezmiennik kolejki planu sportowca planującego");
        }
        return nastepnePolaczenie.stworzZdarzenie(czas, this);
    }

    // Porównywarka tras do bfs'a i tworzenia nowego planu
    // Zwraca:
    //  -1 jeśli trasa 1 jest gorsza od trasy 2
    //   1 jeśli trasa 1 jest lepsza od trasy 2
    public abstract int porownaj(Trasa trasa1, int odl1, Trasa trasa2, int odl2);
}
