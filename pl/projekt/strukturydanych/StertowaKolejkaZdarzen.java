package pl.projekt.strukturydanych;

import pl.projekt.zdarzenia.Zdarzenie;

import java.util.PriorityQueue;
import java.util.Queue;

public class StertowaKolejkaZdarzen implements KolejkaZdarzen{
    private final Queue<Zdarzenie> kolejka;

    public StertowaKolejkaZdarzen(){
        kolejka = new PriorityQueue<>();
    }

    @Override
    public boolean pusta(){
        return kolejka.isEmpty();
    }
    @Override
    public void dodaj(Zdarzenie zdarzenie){
        kolejka.offer(zdarzenie);
    }
    @Override
    public Zdarzenie dajNajmniejszy(){
        if (kolejka.isEmpty()){
            throw new RuntimeException("Próba dostępu do pustej kolejki zdarzeń");
        }
        return kolejka.peek();
    }
    public void usunNajmniejszy(){
        if (kolejka.isEmpty()){
            throw new RuntimeException("Próba usunięcia zdarzenia z pustej kolejki");
        }
        kolejka.poll();
    }
}
