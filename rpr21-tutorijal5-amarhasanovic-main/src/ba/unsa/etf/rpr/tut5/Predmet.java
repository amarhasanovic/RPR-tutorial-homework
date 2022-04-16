package ba.unsa.etf.rpr.tut5;

import java.util.ArrayList;
import java.util.List;

public class Predmet {
    String nazivPredmeta;
    PlanStudija planStudija;
    Semestar semestar;
    int brojECTS;
    List<Student> listaUpisanih;

    public Predmet(String nazivPredmeta, PlanStudija planStudija, Semestar semestar, int brojECTS) {
        this.nazivPredmeta = nazivPredmeta;
        this.planStudija = planStudija;
        this.semestar = semestar;
        this.brojECTS = brojECTS;
        listaUpisanih = new ArrayList<>();
    }

    public void upisiStudenta(Student student){
        if(listaUpisanih.contains(student))
            throw new IllegalArgumentException("Student " + student.getIme() + " " + student.getPrezime() + " vec upisan na predmet " + nazivPredmeta + " .");
        listaUpisanih.add(student);
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public PlanStudija getPlanStudija() {
        return planStudija;
    }

    public Semestar getSemestar() {
        return semestar;
    }

    public int getBrojECTS() {
        return brojECTS;
    }

    public List<Student> getListaUpisanih() {
        return listaUpisanih;
    }
}
