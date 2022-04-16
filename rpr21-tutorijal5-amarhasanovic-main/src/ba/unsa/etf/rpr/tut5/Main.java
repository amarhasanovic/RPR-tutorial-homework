package ba.unsa.etf.rpr.tut5;

public class Main {

    public static void main(String[] args) {
        PlanStudija planStudija = new PlanStudija("RI");
        Semestar semestar1 = new Semestar("I", planStudija);
        semestar1.dodajPredmet(new Predmet("Razvoj programskih rješenja", planStudija, semestar1, 5), true);
        semestar1.dodajPredmet(new Predmet("Sistemsko programiranje", planStudija, semestar1, 5), false);
        semestar1.dodajPredmet(new Predmet("Diskretna matematika", planStudija, semestar1, 5), true);
        semestar1.dodajPredmet(new Predmet("Algoritmi i strukture podataka", planStudija, semestar1, 5), true);
        semestar1.dodajPredmet(new Predmet("Numerički algoritmi", planStudija, semestar1, 5), false);
        semestar1.dodajPredmet(new Predmet("Logički dizajn", planStudija, semestar1, 5), true);
        semestar1.dodajPredmet(new Predmet("Osnove baza podataka", planStudija, semestar1, 5), true);

        planStudija.dodajSemestar(semestar1);
        Student student = new Student("Amar", "Hasanovic", planStudija);

        Semestar semestar2 = new Semestar("II", planStudija);
        semestar2.dodajPredmet(new Predmet("Inženjerska matematika I", planStudija, semestar1, 5), true);
        semestar2.dodajPredmet(new Predmet("Inženjerska matematika II", planStudija, semestar1, 5), true);
        semestar2.dodajPredmet(new Predmet("Inženjerska fizika I", planStudija, semestar1, 5), true);
        semestar2.dodajPredmet(new Predmet("Osnove elektrotehnike I", planStudija, semestar1, 5), true);
        semestar2.dodajPredmet(new Predmet("Uvod u programiranje", planStudija, semestar1, 5), true);
        semestar2.dodajPredmet(new Predmet("Tehnike programiranja", planStudija, semestar1, 5), true);
        planStudija.dodajSemestar(semestar2);

        System.out.println(student.getIme() + " " + student.getPrezime() + " " + student.getPlanStudija().getNaziv());
        int i = 1;
        for(Predmet predmet : student.getListaUpisanihPredmeta()) {
            System.out.println(i + ". " + predmet.getNazivPredmeta());
            i = i + 1;
        }

        student.upisiUSljedeciSemestar();
        i = 1;
        for(Predmet predmet : student.getListaUpisanihPredmeta()) {
            System.out.println(i + ". " + predmet.getNazivPredmeta());
            i = i + 1;
        }
    }

}
