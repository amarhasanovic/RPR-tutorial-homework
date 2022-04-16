package ba.unsa.etf.rpr.tutorijal8;

public class Grad {
    private String naziv;
    private int brojStanovnika;
    private double[] temperature;

    public Grad(){
        this.naziv = "";
        this.brojStanovnika = 0;
    }
    public Grad(String trim, int stanovnika) {
        this.naziv = trim;
        this.brojStanovnika = stanovnika;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }
}
