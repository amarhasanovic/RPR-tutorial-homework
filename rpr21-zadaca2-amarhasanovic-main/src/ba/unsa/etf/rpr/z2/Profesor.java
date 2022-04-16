package ba.unsa.etf.rpr.z2;

public class Profesor{
    String ime;
    String prezime;
    Integer brojCasova;
    Integer brojStudenataNaPredmetima;

    public Profesor(String ime, String prezime, Fakultet fakultet) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojCasova = 0;
        this.brojStudenataNaPredmetima = 0;
        fakultet.dodajProfesora(this);
    }

    public String getIme() {
        return ime;
    }

    @Override
    public String toString(){
        String s  = "";
        s = s + ime + " " + prezime + " " + "(" + brojCasova + "p, " + brojStudenataNaPredmetima + "s)";
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
