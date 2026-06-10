package pl.projekt.sportowcy;

import pl.projekt.cechy.Czas;
import pl.projekt.osrodek.Polaczenie;
import pl.projekt.osrodek.Wezel;
import pl.projekt.zdarzenia.Zdarzenie;

import java.util.LinkedList;
import java.util.Queue;

public abstract class SportowiecPlanujacy extends Sportowiec{
    private Queue<Polaczenie> aktualnyPlan;

    public SportowiecPlanujacy(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, Wagi wagi, boolean sledzony, Wezel wezelStartowy, Czas czasStartu){
        super(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, wagi, sledzony, wezelStartowy, czasStartu);
        aktualnyPlan = new LinkedList<>();
    }
    public SportowiecPlanujacy(int numer, int poziomZaawansowania, double wspSpontanicznosci, double wspZnudzenia, double wagaTrudnosci, double wagaNawierzchni, double wagaZnudzenia, boolean sledzony, Wezel wezelStartowy, Czas czasStartu) {
        this(numer, poziomZaawansowania, wspSpontanicznosci, wspZnudzenia, new Wagi(wagaTrudnosci, wagaNawierzchni, wagaZnudzenia), sledzony, wezelStartowy, czasStartu);
    }

    public boolean wykonujePlan(){
        return !aktualnyPlan.isEmpty();
    }
    public void dodajDoPlanu(Polaczenie polaczenie){
        aktualnyPlan.offer(polaczenie);
    }
    public abstract void stworzNowyPlan(Czas czas, Wezel wezel);

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

        Polaczenie nastepnePolaczenie = aktualnyPlan.poll();

        if (nastepnePolaczenie == null || nastepnePolaczenie.start() != wezel){
            throw new RuntimeException("Niespełniony niezmiennik kolejki planu sportowca planującego");
        }
        return nastepnePolaczenie.stworzZdarzenie(czas, this);
    }
}
