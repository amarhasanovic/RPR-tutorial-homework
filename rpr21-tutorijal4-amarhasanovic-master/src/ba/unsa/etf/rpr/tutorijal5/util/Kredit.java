package ba.unsa.etf.rpr.tutorijal5.util;

import ba.unsa.etf.rpr.tutorijal5.Korisnik;
import ba.unsa.etf.rpr.tutorijal5.Osoba;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Kredit {
    double dajKreditnuSposobnost(KreditnaSposobnost func, Korisnik korisnik){
        return func.provjeri(korisnik.getRacun());
    }

    String bezPrekoracenja(List<Korisnik> korisnici){
        return korisnici.stream().filter(k -> k.getRacun().getStanjeRacuna() >= 0).map(Osoba::toString).collect(Collectors.joining("\n"));
    }

    void odobriPrekoracenje(List<Korisnik> korisnici){
        korisnici.stream().filter(k -> k.getRacun().getStanjeRacuna() >= 10000).forEach(k -> k.getRacun().odobriPrekoracenje(1000.));
    }
}
