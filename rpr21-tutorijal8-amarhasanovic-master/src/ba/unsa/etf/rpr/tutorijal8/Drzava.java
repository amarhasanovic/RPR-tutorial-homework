package ba.unsa.etf.rpr.tutorijal8;

public class Drzava {
    private String naziv;
    private int brojStanovnika;
    private double povrsina;
    private String jedinica;
    private Grad glavniGrad;

    public String getNaziv() {
        return naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public double getPovrsina() {
        return povrsina;
    }

    public String getJedinica() {
        return jedinica;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public void setPovrsina(double povrsina) {
        this.povrsina = povrsina;
    }

    public void setJedinica(String jedinica) {
        this.jedinica = jedinica;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
