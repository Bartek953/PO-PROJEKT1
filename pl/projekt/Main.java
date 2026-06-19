package pl.projekt;

import kadra.mapki.pliki.WyjatekSystemuPlikow;

public class Main {
    public static void main(String[] args){
        System.out.println("POCZĄTEK SYMULACJI");

        if (args.length != 1){
            System.err.println("Błąd: zła liczba argumentów programu.");
            System.err.println("Poprawne użycie: java Main sciezka_do_katalogu_mapek");
        }
        else {
            try {
                Symulacja symulacja = new Symulacja();
                symulacja.symuluj();
                symulacja.tworzMapki(args[0]);
            }
            catch (WyjatekSystemuPlikow e){
                System.err.println("Błąd: niepoprawna ścieżka do katalogu mapek: " + args[0]);
                e.printStackTrace(System.err);
            }
            catch (RuntimeException e){
                System.err.println("Wystąpił krytyczny błąd programu. Skontaktuj się z zespołem deweloperskim.");
                e.printStackTrace(System.err);
            }
            catch (Exception e){
                System.err.println("Wystąpił nieznany błąd systemu. Skontaktuj się z zespołem deweloperskim.");
                e.printStackTrace(System.err);
            }
        }
    }
}