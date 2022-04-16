package ba.unsa.etf.rpr.z2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakultetTest {
    private static Fakultet fakultet;
    @BeforeAll
    static void setUp(){
        fakultet = new Fakultet("Elektrotehnički fakultet");

        Profesor vedran = new Profesor("Vedran", "Ljubović", fakultet);
        Profesor naida = new Profesor("Naida", "Mujić", fakultet);
        Profesor dijana = new Profesor("Dijana", "Dujak", fakultet);
        Profesor almasa = new Profesor("Almasa", "Odžak", fakultet);
        Profesor zeljko = new Profesor("Željko", "Jurić", fakultet);
        Profesor samir = new Profesor("Samir", "Ribić", fakultet);
        Profesor amela = new Profesor("Amela", "Ribić", fakultet);

        Semestar s1 = new Semestar("I", "Računarstvo i informatika", "I");
        s1.dodajPredmet(new Predmet("Uvod u programiranje", 8, 49, vedran, true));
        s1.dodajPredmet(new Predmet("Inženjerska matematika 1", 8, 44, naida, true));
        s1.dodajPredmet(new Predmet("Inženjerska fizika 1", 7, 39, dijana, true));
        s1.dodajPredmet(new Predmet("Linearna algebra i geometrija", 7, 39, almasa, true));

        Semestar s2 = new Semestar("II", "Računarstvo i informatika", "I");
        s2.dodajPredmet(new Predmet("Tehnike programiranja", 8, 48, zeljko, true));
        s2.dodajPredmet(new Predmet("Inženjerska matematika 2", 8, 44, naida, true));
        s2.dodajPredmet(new Predmet("Operativni sistemi", 7, 38, samir, true));
        s2.dodajPredmet(new Predmet("Vjerovatnoća i statistika", 7, 39, amela, true));

        fakultet.dodajSemestar(s1);
        fakultet.dodajSemestar(s2);

        Student student1 = new Student("Amar", "Hasanović", 18890, fakultet);
        Student student2 = new Student("Ime1", "Prezime1", 18903, fakultet);
    }
    @Test
    void dajListuProfesoraPremaNormi() {
        assertAll(
                ()->assertEquals("Naida", fakultet.dajListuProfesoraPremaNormi().get(0).getIme()),
                ()->assertEquals("Vedran", fakultet.dajListuProfesoraPremaNormi().get(1).getIme())
        );
    }

    @Test
    void dajListuProfesoraPremaNormi2(){
        Student student = new Student("Ime2", "Prezime2", 18941, fakultet);
        student.upisiSemestar();
        assertEquals("Naida Mujić (88p, 3s)", fakultet.dajListuProfesoraPremaNormi().get(0).toString());
    }

    @Test
    void dajListuProfesoraPremaBrojuStudenata(){
        Student student1 = new Student("Ime3", "Prezime3", 12345, fakultet);
        Student student2 = new Student("Ime4", "Prezime4", 23456, fakultet);
        assertEquals(5, fakultet.dajListuProfesoraPremaBrojuStudenata().get(0).brojStudenataNaPredmetima);
    }

    @Test
    void exeptionPostojeciSemestarTest(){
        assertThrows(IllegalArgumentException.class, () -> fakultet.dodajSemestar(new Semestar("I", "Računarstvo i informatika", "I")));
    }


}