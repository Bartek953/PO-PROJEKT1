package pl.projekt.mapki;

import kadra.mapki.GeneratorMapek;
import kadra.mapki.pliki.WyjatekSystemuPlikow;
import kadra.mapki.styl.GruboscKonturu;
import kadra.mapki.styl.StylKrawedzi;
import kadra.mapki.styl.StylLinii;
import kadra.mapki.styl.StylWezla;
import pl.projekt.osrodek.Trasa;
import pl.projekt.osrodek.Wezel;
import pl.projekt.osrodek.Wyciag;

import java.util.ArrayList;
import java.util.function.Function;

public class TworcaMapek {
    private GeneratorMapek generatorMapek;

    public TworcaMapek(String folder) throws WyjatekSystemuPlikow {
        generatorMapek = new GeneratorMapek(folder);
    }

    private void dodajWezel(Wezel wezel){
        int nr = wezel.numer();
        int x = wezel.wspolrzedne().x();
        int y = wezel.wspolrzedne().y();
        StylWezla styl = wezel.skomunikowany() ? new StylWezla(GruboscKonturu.POGRUBIONY) : new StylWezla(GruboscKonturu.ZWYKLY);
        generatorMapek.dodajWezel(nr, x, y, styl);
    }

    private void dodajTrase(Trasa trasa, Function<Trasa, ArrayList<String>> opisTrasy){
        int start = trasa.start().numer();
        int koniec = trasa.koniec().numer();
        StylKrawedzi styl = new StylKrawedzi(StylLinii.CIAGLA);

        generatorMapek.dodajKrawedz(start, koniec, styl, opisTrasy.apply(trasa));
    }

    private void dodajWyciag(Wyciag wyciag, Function<Wyciag, ArrayList<String>> opisWyciagu){
        int start = wyciag.start().numer();
        int koniec = wyciag.koniec().numer();
        StylKrawedzi styl = new StylKrawedzi(StylLinii.PRZERYWANA);

        generatorMapek.dodajKrawedz(start, koniec, styl, opisWyciagu.apply(wyciag));
    }

    private void tworzMapkeGrafu(String nazwa, Wezel[] listaWezlow, Trasa[] listaTras, Wyciag[] listaWyciagow,
                                 Function<Trasa, ArrayList<String>> opisTrasy, Function<Wyciag, ArrayList<String>> opisWyciagu)
                                 throws WyjatekSystemuPlikow {
        for (Wezel wezel : listaWezlow){
            dodajWezel(wezel);
        }

        for (Trasa trasa : listaTras){
            dodajTrase(trasa, opisTrasy);
        }

        for (Wyciag wyciag : listaWyciagow){
            dodajWyciag(wyciag, opisWyciagu);
        }

        generatorMapek.tworzMapke(nazwa);
        generatorMapek.zeruj();
    }

    private ArrayList<String> parametrycznyOpisTrasy(Trasa trasa){
        ArrayList<String> opis = new ArrayList<>();
        opis.add(String.format(
                "t%d: poziom: %d, czas: %ds",
                trasa.numer(), trasa.poziomTrudnosci(), trasa.czasPrzejazdu().naSekundy())
        );
        opis.add(String.format(
                "odporność: %.2f, %.5f",
                trasa.bazowaAtrakcyjnosc(), trasa.odpornosc()
        ));
        return opis;
    }
    private ArrayList<String> parametrycznyOpisWyciagu(Wyciag wyciag){
        ArrayList<String> opis = new ArrayList<>();
        opis.add(String.format(
                "w%d: %d os. co %ds",
                wyciag.numer(), wyciag.maksymalnaLiczbaOsob(), wyciag.odstepCzasu().naSekundy()
        ));
        opis.add(String.format(
                "czas: %ds",
                wyciag.czasPrzejazdu().naSekundy()
        ));
        return opis;
    }

    public void tworzMapkeParametrow(Wezel[] listaWezlow, Trasa[] listaTras, Wyciag[] listaWyciagow) throws WyjatekSystemuPlikow{
        tworzMapkeGrafu("parametry.tex", listaWezlow, listaTras, listaWyciagow,
                this::parametrycznyOpisTrasy, this::parametrycznyOpisWyciagu);
    }

    private ArrayList<String> statystycznyOpisTrasy(Trasa trasa){
        ArrayList<String> opis = new ArrayList<>();
        opis.add(String.format(
                "t%d: śnieg: %.2f",
                trasa.numer(), trasa.atrakcyjnoscNawierzchni()
        ));
        opis.add(String.format(
                "zjazdy: %d",
                trasa.liczbaPrzejazdow()
        ));
        return opis;
    }
    private ArrayList<String> statystycznyOpisWyciagu(Wyciag wyciag){
        ArrayList<String> opis = new ArrayList<>();
        opis.add(String.format(
                "w%d: kol: %d(śr), %d(maks)",
                wyciag.numer(), Math.round(wyciag.sredniaDlugoscKolejki()), wyciag.maksymalnaDlugoscKolejki()
        ));
        opis.add(String.format(
                "wjazdy: %d / %d (%d%%)",
                wyciag.liczbaPrzejazdow(), wyciag.potencjalLiczbyPrzewiezionych(), wyciag.procentZajetychMiejsc()
        ));
        return opis;
    }
    public void tworzMapkeStatystyk(Wezel[] listaWezlow, Trasa[] listaTras, Wyciag[] listaWyciagow) throws WyjatekSystemuPlikow{
        tworzMapkeGrafu("statystyki.tex", listaWezlow, listaTras, listaWyciagow,
                this::statystycznyOpisTrasy, this::statystycznyOpisWyciagu);
    }
}
