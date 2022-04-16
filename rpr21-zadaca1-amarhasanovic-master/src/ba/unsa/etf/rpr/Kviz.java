package ba.unsa.etf.rpr;

import java.util.*;

public class Kviz {
    public enum SistemBodovanja{BINARNO, PARCIJALNO, PARCIJALNO_SA_NEGATIVNIM}

    String naziv;
    SistemBodovanja sistemBodovanja;
    List<Pitanje> pitanja;

    public Kviz(String naziv, SistemBodovanja sistemBodovanja) {
        this.naziv = naziv;
        this.sistemBodovanja = sistemBodovanja;
        pitanja = new ArrayList<>();
    }
    public void dodajPitanje(Pitanje pitanje){
        if(pitanja.contains(pitanje))
            throw new IllegalArgumentException("Ne možete dodati pitanje sa tekstom koji već postoji");
        pitanja.add(pitanje);
    }

    @Override
    public String toString(){
        String ispis = "Kviz \"" + getNaziv() + "\" boduje se ";
        switch (sistemBodovanja){
            case PARCIJALNO:
                ispis = ispis + "parcijalno. ";
                break;
            case BINARNO:
                ispis = ispis + "binarno. ";
                break;
            case PARCIJALNO_SA_NEGATIVNIM:
                ispis = ispis + "parcijalno sa negativnim bodovima. ";
                break;
        }

        ispis = ispis + "Pitanja:\n";
        int i = 1;
        for(Pitanje pitanje : pitanja){
            ispis = ispis + i + ". " + pitanje.getTekst() + "(" + pitanje.getBrojPoena() + "b)";
            for(Map.Entry<String,Odgovor> entry : pitanje.getOdgovori().entrySet()){
                ispis = ispis + "\n\t" + entry.getKey() + ": " + entry.getValue().getTekstOdgovora();
                if(entry.getValue().isTacno())
                    ispis = ispis + "(T)";
            }
            if(i != pitanja.size())
                ispis = ispis + "\n\n";
            i = i + 1;
        }
        return ispis;
    }

    public RezultatKviza predajKviz(Map<Pitanje, ArrayList<String>> rezultatiKviza){
        RezultatKviza rezultat = new RezultatKviza(this);
        Map<Pitanje, Double> bodovi = new HashMap<>();
        double total = 0;
        for (Pitanje pitanje : pitanja) {
            if (rezultatiKviza.containsKey(pitanje)) {
                Pitanje temp = pitanje;
                total = total + temp.izracunajPoene(rezultatiKviza.get(temp), this.getSistemBodovanja());
                bodovi.put(temp, temp.izracunajPoene(rezultatiKviza.get(temp), this.getSistemBodovanja()));
            } else {
                bodovi.put(pitanje, (double) 0);
            }
        }
        rezultat.setTotal(total);
        rezultat.setBodovi(bodovi);
        return rezultat;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setSistemBodovanja(SistemBodovanja sistemBodovanja) {
        this.sistemBodovanja = sistemBodovanja;
    }

    public void setPitanja(List<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }

    public String getNaziv() {
        return naziv;
    }

    public SistemBodovanja getSistemBodovanja() {
        return sistemBodovanja;
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }
}
