package pl.projekt.zdarzenia;

public enum Priorytet {
    ZDARZENIE_NORMALNE(1), ZDARZENIE_NATYCHMIASTOWE(10);

    private final int priorytet;
    Priorytet(int priorytet){
        this.priorytet = priorytet;
    }
    int priorytet(){
        return priorytet;
    }
}
