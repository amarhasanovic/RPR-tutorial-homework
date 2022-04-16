package ba.unsa.etf.rpr;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RezultatKviza {
    Kviz kviz;
    double total;
    Map<Pitanje, Double> bodovi;

    public RezultatKviza(Kviz kviz) {
        this.kviz = kviz;
        this.total = 0;
        this.bodovi = new HashMap<>();
    }

    @Override
    public String toString(){
        String ispis = "Na kvizu \"" + getKviz().getNaziv() + "\" ostvarili ste ukupno " + getTotal() + " poena. Raspored po pitanjima:";
        for(Map.Entry<Pitanje, Double> entry : bodovi.entrySet()){
            ispis = ispis + "\n" + entry.getKey().getTekst() + " - " + entry.getValue().toString() + "b";
        }

        return ispis;
    }

    public void setKviz(Kviz kviz) {
        this.kviz = kviz;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setBodovi(Map<Pitanje, Double> bodovi) {
        this.bodovi = bodovi;
    }

    public Kviz getKviz() {
        return kviz;
    }

    public double getTotal() {
        return total;
    }

    public Map<Pitanje, Double> getBodovi() {
        return bodovi;
    }
}
