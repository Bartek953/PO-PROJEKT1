package pl.projekt.zdarzenia;


/*
    Uwaga!
    System priorytetów nie jest sprzeczny z brakiem szeregowania wspomnianym na forum.
    Zdarzenia z treści wszystkie mają priorytet zwykły, co gwarantuje wykonywanie ich
    zgodnie z kolejnością wstawienia do kolejki.
    Wyższy priorytet dotyczy tylko zdarzeń pomocniczych symulacji - jak zdarzenie
    decyzyjne, co pozwala rozbić bardziej skomplikowaną logikę z treści na pojedyncze
    bloki, nie zmnieniając kwestii wykonania takiego bloku jako jedności.
    System priorytetów jest tutaj konieczny - gdyby go nie było, to sportowiec
    kończący zjazd w tym samym momencie co przyjazd wyciągu nigdy by nie zdążył
    (musi dokonać decyzji i ustawić się w kolejce). Byłoby to niezgodne z treścią
    (taka sytuacja powinna być niedeterministyczna). System priorytetów pozwala
    połączyć koniec zjazdu, decyzje i ustawienie w kolejce w jedno zdarzenie, co
    wprowadza niedterminizm z forum - jeśli koniec zjazdu sportowca jest wrzucony na
    kolejkę wcześniej niż przyjazd wyciągu to zdąży, wpp nie zdąży.

 */
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
