package pl.projekt;

public class Main {
    public static void main(String[] args){
        System.out.println("POCZĄTEK SYMULACJI");

        Symulacja symulacja = new Symulacja();
        symulacja.symuluj();
        try {
            symulacja.tworzMapki("mapki/mapki");
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }
}