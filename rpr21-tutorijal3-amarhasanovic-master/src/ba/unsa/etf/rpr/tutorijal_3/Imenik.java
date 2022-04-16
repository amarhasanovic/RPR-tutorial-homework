package ba.unsa.etf.rpr.tutorijal_3;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Imenik {
    HashMap<String, TelefonskiBroj> s = new HashMap<>();
    public void dodaj(String ime, TelefonskiBroj broj) {
        s.put(ime, broj);
    }

    public String dajBroj(String ime) {
        return s.get(ime).ispisi();
    }

    public String dajIme(TelefonskiBroj broj) {
        for(Map.Entry<String, TelefonskiBroj> entry: s.entrySet()){
            if(entry.getValue() == broj){
                return entry.getKey();
            }
        }
        return "";
    }

    public String naSlovo(char s) {
        String lista = "";
        int i=0;
        for(Map.Entry<String, TelefonskiBroj> entry: (this.s).entrySet()){
            if(entry.getKey().charAt(0) == s){
                int privremeni = i + 1;
                lista = lista + privremeni + ". " + entry.getKey() + " - " + entry.getValue().ispisi() + "\n";
                i++;
            }
        }
        return lista;
    }

    public TreeSet<String> izGrada(FiksniBroj.Grad g) {
        TreeSet<String> p = new TreeSet<>();
        for(Map.Entry<String, TelefonskiBroj> entry: (this.s).entrySet()){
            if(entry.getValue() instanceof FiksniBroj && ((FiksniBroj) entry.getValue()).getGrad() == g){
                p.add(entry.getKey());
            }
        }
        return p;
    }

    public TreeSet<TelefonskiBroj> izGradaBrojevi(FiksniBroj.Grad g) {
        TreeSet<TelefonskiBroj> t = new TreeSet<>();
        for(Map.Entry<String, TelefonskiBroj> entry: (this.s).entrySet()){
            if(entry.getValue() instanceof FiksniBroj && ((FiksniBroj) entry.getValue()).getGrad() == g){
                t.add(entry.getValue());
            }
        }
        return t;
    }

}
