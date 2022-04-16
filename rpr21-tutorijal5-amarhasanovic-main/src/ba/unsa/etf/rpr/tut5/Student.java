package ba.unsa.etf.rpr.tut5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    String ime;
    String prezime;
    PlanStudija planStudija;
    Semestar semestar;
    List<Predmet> listaUpisanihPredmeta;

    public Student(String ime, String prezime, PlanStudija planStudija) {
        this.ime = ime;
        this.prezime = prezime;
        this.planStudija = planStudija;
        this.semestar = planStudija.getListaSemestara().get(0);
        listaUpisanihPredmeta = new ArrayList<>();
        upisiNaPredmete();
    }

    public void upisiUSljedeciSemestar(){
        if(semestar == planStudija.listaSemestara.get(planStudija.listaSemestara.size()-1))
            throw new IllegalArgumentException("Student je završio trenutni program studija!");
        int index = planStudija.getListaSemestara().indexOf(semestar);
        semestar = planStudija.getListaSemestara().get(index + 1);
        listaUpisanihPredmeta.clear();
        upisiNaPredmete();
    }

    public void upisiNaPredmete(){
        int ectsBodovi = 0;
        for(Predmet predmet : semestar.getListaObaveznihPredmeta()){
            listaUpisanihPredmeta.add(predmet);
            predmet.upisiStudenta(this);
            ectsBodovi = ectsBodovi + predmet.getBrojECTS();
        }

        System.out.println("Trenutni broj ECTS poena: " + ectsBodovi + "\n");
        if(!semestar.getListaIzbornihPredmeta().isEmpty()) {
            System.out.println("Odaberite izborne predmete (0 za kraj):\n" + semestar.prikaziIzbornePredmete());
            Scanner odabir = new Scanner(System.in);
            int index;
            while ((index = odabir.nextInt()) != 0 || ectsBodovi < 30) {
                if (index != 0) {
                    Predmet upisani = semestar.getListaIzbornihPredmeta().get(index - 1);
                    listaUpisanihPredmeta.add(upisani);
                    upisani.upisiStudenta(this);
                    ectsBodovi = ectsBodovi + upisani.getBrojECTS();
                }
                if (ectsBodovi < 30 && index == 0)
                    System.out.println("Odaberite još jedan predmet!");
            }
        }
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public PlanStudija getPlanStudija() {
        return planStudija;
    }

    public List<Predmet> getListaUpisanihPredmeta() {
        return listaUpisanihPredmeta;
    }

}
