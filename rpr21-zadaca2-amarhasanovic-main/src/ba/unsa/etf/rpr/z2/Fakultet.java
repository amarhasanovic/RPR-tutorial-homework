package ba.unsa.etf.rpr.z2;

import java.util.ArrayList;
import java.util.List;

public class Fakultet {
    String nazivFakulteta;
    List<Semestar> semestri;
    List<Profesor> profesori;

    List<Student> upisaniStudenti;

    public Fakultet(String nazivFakulteta) {
        this.nazivFakulteta = nazivFakulteta;
        this.semestri = new ArrayList<>();
        this.profesori = new ArrayList<>();
        this.upisaniStudenti = new ArrayList<>();
    }

    public List<Profesor> dajListuProfesoraPremaNormi(){
        profesori.sort((p1, p2) -> p2.brojCasova.compareTo(p1.brojCasova));
        return profesori;
    }

    public List<Profesor> dajListuProfesoraPremaBrojuStudenata(){
        profesori.sort((p1, p2)->p2.brojStudenataNaPredmetima.compareTo(p1.brojStudenataNaPredmetima));
        return profesori;
    }

    public void dodajSemestar(Semestar semestar){
        if(semestri.contains(semestar))
            throw new IllegalArgumentException("Semestar već dodan!");
        semestri.add(semestar);
    }
    void dodajProfesora(Profesor profesor){
        if(!profesori.contains(profesor))
            profesori.add(profesor);
    }

    void upisiStudenta(Student student){
        if(upisaniStudenti.contains(student))
            throw new IllegalArgumentException("Student već upisan!");
        upisaniStudenti.add(student);
    }

    public List<Student> getUpisaniStudenti() {
        return upisaniStudenti;
    }
}
