package ba.unsa.etf.rpr.tut5;

import java.util.ArrayList;
import java.util.List;

public class PlanStudija {
    String naziv;
    List<Semestar> listaSemestara;

    public PlanStudija(String naziv) {
        this.naziv = naziv;
        listaSemestara = new ArrayList<>();
    }
    public void dodajSemestar(Semestar semestar){
        if(listaSemestara.contains(semestar))
            throw new IllegalArgumentException("Semestar već postoji!");
        listaSemestara.add(semestar);
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Semestar> getListaSemestara() {
        return listaSemestara;
    }

    public void setListaSemestara(List<Semestar> listaSemestara) {
        this.listaSemestara = listaSemestara;
    }
}
