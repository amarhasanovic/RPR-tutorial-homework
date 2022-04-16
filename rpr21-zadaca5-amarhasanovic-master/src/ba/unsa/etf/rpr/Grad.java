package ba.unsa.etf.rpr;

public class Grad {
    int id;
    String naziv;
    int brojStanovnika;
    Drzava drzava;
    String slika;
    int postanskiBroj;

    public Grad(){
        this.slika = "C:/Users/Korisnik/IdeaProjects/rpr21-zadaca5-amarhasanovic/resources/pictures/architecture-and-city.png";
    }
    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.slika = "C:/Users/Korisnik/IdeaProjects/rpr21-zadaca5-amarhasanovic/resources/pictures/architecture-and-city.png";
        this.drzava = drzava;
    }

    public String getNaziv() {
        return naziv;
    }

    public Drzava getDrzava() {
        return drzava;
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

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public String getSlika(){ return this.slika; }

    public void setSlika(String slika){ this.slika = slika; }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
