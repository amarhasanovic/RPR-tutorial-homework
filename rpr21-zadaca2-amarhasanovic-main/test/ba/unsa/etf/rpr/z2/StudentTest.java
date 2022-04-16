package ba.unsa.etf.rpr.z2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    static Fakultet fakultet;
    static Predmet predmet1, predmet2, predmet3, predmet4;
    static Map<Predmet, Integer> testneOcjene;

    @BeforeAll
    static void setUp(){
        fakultet = new Fakultet("TestniNazivFakulteta");
        Semestar semestar = new Semestar("I", "TestniPlanStudija", "TestniCiklus");
        predmet1 = new Predmet("Uvod u programiranje", 7, 49, new Profesor("Vedran", "Ljubović", fakultet), true);
        predmet2 = new Predmet("Inženjerska matematika 1", 7, 49, new Profesor("Naida", "Mujić", fakultet), true);

        Semestar semestar2 = new Semestar("II", "TestniPlanStudija", "TestniCiklus");
        predmet3 = new Predmet("Tehnike programiranja", 7, 44, new Profesor("Željko", "Jurić", fakultet), true);
        predmet4 = new Predmet("Operativni sistemi", 5, 42, new Profesor("Samir", "Ribić", fakultet), true);
        testneOcjene = new HashMap<>();
        testneOcjene.put(predmet1, 8);
        testneOcjene.put(predmet2, 7);
        semestar.dodajPredmet(predmet1);
        semestar.dodajPredmet(predmet2);
        semestar2.dodajPredmet(predmet3);
        semestar2.dodajPredmet(predmet4);
        fakultet.dodajSemestar(semestar);
        fakultet.dodajSemestar(semestar2);
    }
    @Test
    void testToString() {
        Student student = new Student("Amar", "Hasanović", 18890, fakultet);
        student.ocjene = testneOcjene;
        String ocekivaniIspis1 = "Student Amar Hasanović (18890):\n1. Uvod u programiranje 8\n2. Inženjerska matematika 1 7\n";
        String ocekivaniIspis2 = "Student Amar Hasanović (18890):\n1. Inženjerska matematika 1 7\n2. Uvod u programiranje 8\n";
        String stvarniIspis = student.toString();

        boolean tacnostIspisa = stvarniIspis.equals(ocekivaniIspis1) || stvarniIspis.equals(ocekivaniIspis2);
        assertTrue(tacnostIspisa);
    }

    @Test
    void testEquals() {
        Student student1 = new Student("Ime", "Prezime", 12345, fakultet);
        Student student2 = new Student("Ime", "Prezime", 67891, fakultet);

        assertAll(
                () -> assertEquals(false, student1.equals(student2)),
                () -> assertThrows(IllegalArgumentException.class, () ->  new Student("Ime", "Prezime", 12345, fakultet))
        );
    }

    @Test
    void testUpisiSemestar(){
        Student student = new Student("Ime", "Prezime", 18000, fakultet);
        student.upisiSemestar();
        assertAll(
                () -> assertEquals("Tehnike programiranja", student.getListaUpisanihPredmeta().get(0).nazivPredmeta),
                () -> assertEquals("Operativni sistemi", student.getListaUpisanihPredmeta().get(1).nazivPredmeta)
        );
    }

    @Test
    void testUpisiOcjenu(){
        Student student = new Student("Amar", "Hasanović", 18899, fakultet);
        student.upisiOcjenu(0, 8);
        assertAll(
                () ->  assertEquals("Uvod u programiranje 8", predmet1.toString() + " " + student.getOcjene().get(predmet1)),
                () -> assertThrows(IllegalArgumentException.class, () -> student.upisiOcjenu(0, 7))
        );
    }
}