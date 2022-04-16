package ba.unsa.etf.rpr.tutorijal5.util;

import ba.unsa.etf.rpr.tutorijal5.Banka;
import ba.unsa.etf.rpr.tutorijal5.Korisnik;
import ba.unsa.etf.rpr.tutorijal5.Racun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KreditTest {
    List<Korisnik> korisnici;
    Kredit kredit;
    @BeforeEach
    void setUp() {
        kredit = new Kredit();
        korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("ime1", "prezime1"));
        korisnici.add(new Korisnik("ime2", "prezime2"));
        korisnici.add(new Korisnik("ime3", "prezime3"));
        for(Korisnik k: korisnici) {
            Racun r = new Racun(1000000000000000L, k);
            r.odobriPrekoracenje(10.);
            r.izvrsiIsplatu(10.);
           k.dodajRacun(r);

        }
        korisnici.get(0).getRacun().izvrsiUplatu(10.);
    }

    @Test
    void dajKreditnuSposobnost() {
        korisnici.get(0).getRacun().izvrsiUplatu(10000.);
        assertEquals(11000., kredit.dajKreditnuSposobnost((r)-> r.getStanjeRacuna()*1.10, korisnici.get(0)));
    }

    @Test
    void bezPrekoracenja() {
        assertEquals("{ime='ime1', prezime='prezime1'}", kredit.bezPrekoracenja(korisnici));
    }

    @Test
    void odobriPrekoracenje() {
        for(Korisnik k: korisnici) {
            k.getRacun().izvrsiUplatu(15000.);
        }
        kredit.odobriPrekoracenje(korisnici);
        assertAll(
                () -> assertTrue(korisnici.get(0).getRacun().provjeriOdobrenjePrekoracenja(1000.)),
                () -> assertTrue(korisnici.get(1).getRacun().provjeriOdobrenjePrekoracenja(1000.)),
                () -> assertTrue(korisnici.get(2).getRacun().provjeriOdobrenjePrekoracenja(1000.))
        );
    }
}