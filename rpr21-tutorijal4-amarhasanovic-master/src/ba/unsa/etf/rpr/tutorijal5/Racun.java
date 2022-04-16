package ba.unsa.etf.rpr.tutorijal5;

public class Racun {
    private final Long brojRacuna;
    private final Osoba korisnikRacuna;
    private Double stanjeRacuna;
    private Double odobrenjePrekoracenja;

    public Racun(Long brojRacuna, Osoba korisnikRacuna) {
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna = korisnikRacuna;
        this.stanjeRacuna = 0d;
        this.odobrenjePrekoracenja = 0d;
    }

    public boolean izvrsiUplatu(Double iznos) {
        stanjeRacuna = stanjeRacuna + iznos;
        return true;
    }

    public boolean izvrsiIsplatu(Double iznos) {
        if (iznos > stanjeRacuna && !provjeriOdobrenjePrekoracenja(iznos - stanjeRacuna))
            return false;
        stanjeRacuna = stanjeRacuna - iznos;
        return true;
    }

    public void odobriPrekoracenje(Double iznos) {
        // U budućnosti se može dodati iznos odobrenog prekoračenja
        odobrenjePrekoracenja = iznos;
    }

    public boolean provjeriOdobrenjePrekoracenja(Double iznos) {
        // U budućnosti se može dodati iznos prekoračenja
        return iznos < odobrenjePrekoracenja || odobrenjePrekoracenja.equals(iznos);
    }

    public Double getStanjeRacuna() {
        return stanjeRacuna;
    }

    public Long getBrojRacuna() {
        return brojRacuna;
    }

    public Osoba getKorisnikRacuna() {
        return korisnikRacuna;
    }
}
