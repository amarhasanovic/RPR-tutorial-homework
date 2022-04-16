package ba.unsa.etf.rpr.z2;

import java.util.ArrayList;
import java.util.List;

public class Predmet {
    String nazivPredmeta;
    int brojEcts;
    int brojCasova;
    Profesor profesor;
    boolean obavezniPredmet;
    List<Student> upisaniStudenti;

    public Predmet(String nazivPredmeta, int brojEcts, int brojCasova, Profesor profesor, boolean obavezniPredmet) {
        this.nazivPredmeta = nazivPredmeta;
        this.brojEcts = brojEcts;
        this.brojCasova = brojCasova;
        this.profesor = profesor;
        this.obavezniPredmet = obavezniPredmet;
        this.upisaniStudenti = new ArrayList<>();
    }

    public void upisiStudenta(Student student){
        if(upisaniStudenti.contains(student))
            throw new IllegalArgumentException("Student već upisan na predmet!");
        if(upisaniStudenti.isEmpty())
            profesor.brojCasova = profesor.brojCasova + this.brojCasova;
        upisaniStudenti.add(student);
        profesor.brojStudenataNaPredmetima = profesor.brojStudenataNaPredmetima + 1;
    }

    public void ispisiStudenta(Student student){
        upisaniStudenti.remove(student);
        profesor.brojStudenataNaPredmetima -= 1;
        if(upisaniStudenti.isEmpty()){
            profesor.brojCasova = profesor.brojCasova - this.brojCasova;
        }
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public int getBrojEcts() {
        return brojEcts;
    }

    public boolean isObavezniPredmet() {
        return obavezniPredmet;
    }

    @Override
    public String toString(){
        return nazivPredmeta;
    }
}
