package ba.unsa.etf.rpr.z2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PredmetTest {
    private static Profesor profesor;
    private static Fakultet fakultet;
    private static Student student;
    @BeforeAll
    static void setUp(){
        fakultet = new Fakultet("TestniFakultet");
        fakultet.dodajSemestar(new Semestar("I", "TestniStudij", "I"));
        student = new Student("Testni", "Student", 18890, fakultet);
        profesor = new Profesor("Testni", "Profesor", fakultet);
    }
    @Test
    void upisiStudenta() {
        Predmet predmet = new Predmet("TestniPredmet", 8, 49, profesor, true);
        predmet.upisiStudenta(student);
        assertEquals(predmet.upisaniStudenti.get(0), student);
    }

    @Test
    void upisiStudentaIzuzetak(){
        Predmet predmet = new Predmet("TestniPredmet", 8, 49, profesor, true);
        predmet.upisiStudenta(student);
        assertThrows(IllegalArgumentException.class, () -> predmet.upisiStudenta(student));
    }

    @Test
    void ispisiStudenta() {
        Predmet predmet = new Predmet("TestniPredmet", 8, 49, profesor, true);
        predmet.upisiStudenta(student);
        predmet.ispisiStudenta(student);
        assertEquals(predmet.upisaniStudenti.size(), 0);
    }
}