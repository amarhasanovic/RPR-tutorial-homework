package ba.unsa.etf.rpr;

public class Drzava {
    private int id;
    private String naziv;
    private Grad glavniGrad;

    public Drzava(){

    }
    public Drzava(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = null;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
