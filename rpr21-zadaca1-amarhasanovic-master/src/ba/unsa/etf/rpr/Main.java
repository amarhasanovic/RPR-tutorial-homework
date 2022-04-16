package ba.unsa.etf.rpr;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void igrajKviz(Kviz kviz){
        Scanner ulaz = new Scanner(System.in);
        Map<Pitanje, ArrayList<String>> odgovori = new HashMap<>();
        int i = 1;
        for(Pitanje pitanje : kviz.getPitanja()){
            System.out.println(i + ". " + pitanje.toString());
            i = i + 1;
            System.out.print("Odgovor (ex. a,b,c): ");
            String odgovor = ulaz.nextLine();
            if(odgovor.length() == 0)
                odgovori.put(pitanje, new ArrayList<>());
            else {
                String granicnik = "[,]+";
                String[] temp = odgovor.split(granicnik);
                odgovori.put(pitanje, new ArrayList<>(Arrays.asList(temp)));
            }
        }
        System.out.println("Rezultati:\n" + kviz.predajKviz(odgovori).toString());
    }
    public static void main(String[] args) {
        Kviz kviz = new Kviz("Kviz test", Kviz.SistemBodovanja.PARCIJALNO);
        Pitanje pitanje1 = new Pitanje("Pitanje 1?", 3.0);
        pitanje1.dodajOdgovor("a", "Odgovor1", true);
        pitanje1.dodajOdgovor("b", "Odgovor2", false);
        pitanje1.dodajOdgovor("c", "Odgovor3", false);
        kviz.dodajPitanje(pitanje1);
        Pitanje pitanje2 = new Pitanje("Pitanje 2?", 1.0);
        pitanje2.dodajOdgovor("a", "Odgovor1", true);
        pitanje2.dodajOdgovor("b", "Odgovor2", false);
        pitanje2.dodajOdgovor("c", "Odgovor3", false);
        kviz.dodajPitanje(pitanje2);
        Pitanje pitanje3 = new Pitanje("Pitanje 3?", 2.5);
        pitanje3.dodajOdgovor("a", "Odgovor1", true);
        pitanje3.dodajOdgovor("b", "Odgovor2", false);
        pitanje3.dodajOdgovor("c", "Odgovor3", true);
        kviz.dodajPitanje(pitanje3);
        igrajKviz(kviz);
    }
}
