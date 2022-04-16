package ba.unsa.etf.rpr.tut5;

import java.util.ArrayList;
import java.util.List;

public class Semestar {
    String oznakaSemestra;
    PlanStudija planStudija;
    List<Predmet> listaObaveznihPredmeta;
    List<Predmet> listaIzbornihPredmeta;

    public Semestar(String oznakaSemestra, PlanStudija planStudija) {
        this.oznakaSemestra = oznakaSemestra;
        this.planStudija = planStudija;
        listaObaveznihPredmeta = new ArrayList<>();
        listaIzbornihPredmeta = new ArrayList<>();
    }

    public void dodajPredmet(Predmet predmet, boolean obavezan){
        if(listaObaveznihPredmeta.contains(predmet) || listaIzbornihPredmeta.contains(predmet))
            throw new IllegalArgumentException("Predmet postoji!");
        if(obavezan)
            listaObaveznihPredmeta.add(predmet);
        else
            listaIzbornihPredmeta.add(predmet);
    }

    public String prikaziIzbornePredmete(){
        int i = 1;
        String s = "";
        for(Predmet predmet: listaIzbornihPredmeta){
            s = s + i + ". " + predmet.getNazivPredmeta() + " (" + predmet.getBrojECTS() + ")" + "\n";
            i = i + 1;
        }
        return s;
    }

    public String getOznakaSemestra() {
        return oznakaSemestra;
    }

    public void setOznakaSemestra(String oznakaSemestra) {
        this.oznakaSemestra = oznakaSemestra;
    }

    public PlanStudija getPlanStudija() {
        return planStudija;
    }

    public void setPlanStudija(PlanStudija planStudija) {
        this.planStudija = planStudija;
    }

    public List<Predmet> getListaObaveznihPredmeta() {
        return listaObaveznihPredmeta;
    }

    public void setListaObaveznihPredmeta(List<Predmet> listaObaveznihPredmeta) {
        this.listaObaveznihPredmeta = listaObaveznihPredmeta;
    }

    public List<Predmet> getListaIzbornihPredmeta() {
        return listaIzbornihPredmeta;
    }

    public void setListaIzbornihPredmeta(List<Predmet> listaIzbornihPredmeta) {
        this.listaIzbornihPredmeta = listaIzbornihPredmeta;
    }
}
