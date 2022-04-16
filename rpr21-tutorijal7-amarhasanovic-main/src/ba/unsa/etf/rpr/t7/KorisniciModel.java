package ba.unsa.etf.rpr.t7;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();

    public void napuni(){
        korisnici.add(new Korisnik("Vedran", "Ljubović", "vljubovic@etf.unsa.ba", "vljubovic", "1234"));
        korisnici.add(new Korisnik("Amar", "Hasanović", "ahasanovic4@etf.unsa.ba", "ahasanovic4", "1234"));
        trenutniKorisnik.set(null);
    }
    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public void addKorisnika(Korisnik korisnik){ this.korisnici.add(korisnik); }
}
