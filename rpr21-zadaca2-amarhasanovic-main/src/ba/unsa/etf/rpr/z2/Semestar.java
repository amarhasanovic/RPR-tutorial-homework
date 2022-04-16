package ba.unsa.etf.rpr.z2;

import java.util.ArrayList;
import java.util.List;

public class Semestar {
    String brojSemestra;
    String nazivCiklusa;
    String planStudija;
    List<Predmet> listaPredmet;

    public Semestar(String brojSemestra, String planStudija, String nazivCiklusa) {
        this.brojSemestra = brojSemestra;
        this.planStudija = planStudija;
        this.nazivCiklusa = nazivCiklusa;
        this.listaPredmet = new ArrayList<>();
    }

    public void dodajPredmet(Predmet predmet){
        if(listaPredmet.contains(predmet))
            throw new IllegalArgumentException("Predmet već postoji!");
        listaPredmet.add(predmet);
    }

    public List<Predmet> getListaPredmet() {
        return listaPredmet;
    }

    @Override
    public String toString(){
        String s = "";
        s = s + brojSemestra + " semestar:\n";
        int i = 1;
        for(Predmet predmet : listaPredmet){
            s = s + i + ". " + predmet.getNazivPredmeta() + "(" + predmet.getBrojEcts() + "b) ";
            if(predmet.isObavezniPredmet())
                s = s + "obavezan";
            else
                s = s + "izborni";
            s = s + "\n";
            i = i + 1;
        }
        return s;
    }

    @Override
    public boolean equals(Object o){
        return ((Semestar)o).planStudija == this.planStudija &&
                ((Semestar)o).nazivCiklusa == this.nazivCiklusa &&
                ((Semestar)o).brojSemestra == this.brojSemestra;
    }
}
