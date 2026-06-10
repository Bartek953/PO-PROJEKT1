package pl.projekt.strukturydanych;

import pl.projekt.zdarzenia.Zdarzenie;

import java.util.PriorityQueue;
import java.util.Queue;

public class StertowaKolejkaZdarzen implements KolejkaZdarzen{
    private Queue<Zdarzenie> kolejka;

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
        return kolejka.peek();
    }
    public void usunNajmniejszy(){
        kolejka.poll();
    }
}
