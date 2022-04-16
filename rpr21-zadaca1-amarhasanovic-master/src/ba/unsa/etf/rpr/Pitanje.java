package ba.unsa.etf.rpr;

import java.util.*;

public class Pitanje {
    String tekst;
    double brojPoena;
    Map<String, Odgovor> odgovori;

    int brojTacnihOdgovora(){
        int brojTacnih = 0;
        for (Map.Entry<String,Odgovor> entry : odgovori.entrySet()) {
            if(entry.getValue().isTacno())
                brojTacnih = brojTacnih+1;
        }
        return brojTacnih;
    }
    public Pitanje(String tekst, double brojPoena) {
        this.tekst = tekst;
        this.brojPoena = brojPoena;
        this.odgovori = new TreeMap<>();
    }

    public void dodajOdgovor(String id, String tekst, boolean tacno){
        if(odgovori.containsKey(id))
            throw new IllegalArgumentException("Id odgovora mora biti jedinstven");
        odgovori.put(id, new Odgovor(tekst, tacno));
    }

    public void obrisiOdgovor(String id){
        if(!odgovori.containsKey(id))
            throw new IllegalArgumentException("Odgovor s ovim id-em ne postoji");
        odgovori.remove(id);
    }

    public List<Odgovor> dajListuOdgovora(){
        List<Odgovor> lista = new ArrayList<>();
        for (Map.Entry<String,Odgovor> entry : odgovori.entrySet()) {
            lista.add(entry.getValue());
        }
        return lista;
    }

    @Override
    public boolean equals(Object o){
        Pitanje pitanje = (Pitanje) o;
        return ((Pitanje) o).getTekst().equalsIgnoreCase(this.getTekst());
    }

    @Override
    public String toString() {
        String ispis =  tekst + '(' + brojPoena + 'b' +')';
        for (Map.Entry<String,Odgovor> entry : odgovori.entrySet()) {
            ispis = ispis + "\n" + "\t" + entry.getKey() + ": ";
            ispis = ispis + entry.getValue().getTekstOdgovora();
        }
        return ispis;
    }
    public double izracunajPoene(List<String> listaOdgovora, Kviz.SistemBodovanja sistemBodovanja){
        TreeSet<String> temp = new TreeSet<>(listaOdgovora);
        if(temp.size()!=listaOdgovora.size())
            throw new IllegalArgumentException("Postoje duplikati među odabranim odgovorima");
        for(String odgovor : listaOdgovora){
            if(!odgovori.containsKey(odgovor))
                throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
        }
        if(listaOdgovora.size()==0)
            return 0;
        double poeni = 0;
        switch (sistemBodovanja){
            case BINARNO: {
                int tacanOdgovor = 0;
                for (String odgovor : listaOdgovora) {
                    if (odgovori.get(odgovor).isTacno()) {
                        tacanOdgovor = tacanOdgovor + 1;
                    }else if(!odgovori.get(odgovor).isTacno())
                        return 0;
                }
                if(tacanOdgovor == brojTacnihOdgovora())
                    poeni = brojPoena;
                break;
            }
            case PARCIJALNO: {
                int tacanOdgovor = 0;
                boolean netacanOdgovor = false;
                for (String odgovor : listaOdgovora) {
                    if (odgovori.get(odgovor).isTacno())
                        tacanOdgovor = tacanOdgovor + 1;
                    else
                        netacanOdgovor = true;
                }
                if(netacanOdgovor)
                    poeni = 0;
                else {
                    if(tacanOdgovor == brojTacnihOdgovora())
                        poeni = brojPoena;
                    else
                        poeni = ((double) tacanOdgovor / odgovori.size()) * brojPoena;
                }
                break;
            }
            case PARCIJALNO_SA_NEGATIVNIM:
                int tacanOdgovor = 0;
                boolean netacanOdgovor = false;
                for(String odgovor : listaOdgovora){
                    if(odgovori.get(odgovor).isTacno())
                        tacanOdgovor = tacanOdgovor + 1;
                    else
                        netacanOdgovor = true;
                }
                if(netacanOdgovor)
                    poeni = -brojPoena/2;
                else {
                    if(tacanOdgovor == brojTacnihOdgovora())
                        poeni = brojPoena;
                    else
                        poeni = ((double) tacanOdgovor / odgovori.size()) * brojPoena;
                }
                break;
        }
        return poeni;
    }
    //Getteri i setteri
    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public void setOdgovori(TreeMap<String, Odgovor> odgovori) {
        this.odgovori = odgovori;
    }

    public String getTekst() {
        return tekst;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public TreeMap<String, Odgovor> getOdgovori() {
        return (TreeMap<String, Odgovor>) odgovori;
    }
}
