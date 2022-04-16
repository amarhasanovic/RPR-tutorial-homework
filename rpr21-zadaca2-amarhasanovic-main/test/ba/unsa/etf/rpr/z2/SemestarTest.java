package ba.unsa.etf.rpr.z2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemestarTest {
    private static Predmet predmetObavezan;
    private static Predmet predmetIzborni;

    @BeforeAll
    static void setUp(){
        Fakultet fakultet = new Fakultet("TestniFakultet");
        Profesor profesor = new Profesor("Testni", "Profesor", fakultet);
        predmetObavezan = new Predmet("Obavezni predmet", 7, 44, profesor, true);
        predmetIzborni = new Predmet("Izborni predmet", 7, 44, profesor, false);
    }
    @Test
    void dodajPredmet() {
        Semestar semestar = new Semestar("I", "Računarstvo i informatika", "I");
        semestar.dodajPredmet(predmetObavezan);
        assertEquals(semestar.listaPredmet.size(), 1);
    }

    @Test
    void testToString() {
        Semestar semestar = new Semestar("I", "Računarstvo i informatika", "I");
        semestar.dodajPredmet(predmetObavezan);
        semestar.dodajPredmet(predmetIzborni);
        String s = "I semestar:\n1. Obavezni predmet(7b) obavezan\n2. Izborni predmet(7b) izborni\n";
        assertEquals(s, semestar.toString());
    }

    @Test
    void testEquals() {
        Semestar semestar1 = new Semestar("I", "Računarstvo i informatika", "I");
        Semestar semestar2 = new Semestar("II", "Računarstvo i informatika", "I");
        Semestar semestar3 = new Semestar("I", "Automatika i robotika", "I");
        Semestar semestar4 = new Semestar("I", "Računarstvo i informatika", "II");

        assertAll(
                () -> assertEquals(false, semestar1.equals(semestar2)),
                () -> assertEquals(false, semestar1.equals(semestar3)),
                () -> assertEquals(false, semestar1.equals(semestar4))
        );
    }
}