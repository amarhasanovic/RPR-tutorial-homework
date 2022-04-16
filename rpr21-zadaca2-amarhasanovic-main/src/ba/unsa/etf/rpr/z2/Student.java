package ba.unsa.etf.rpr.z2;

import java.util.*;

public class Student {
    String ime;
    String prezime;
    int brojIndexa;

    Map<Predmet, Integer> ocjene;
    Fakultet fakultet;
    Semestar upisaniSemestar;
    List<Predmet> listaUpisanihPredmeta;

    public Student(String ime, String prezime, int brojIndexa, Fakultet fakultet) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojIndexa = brojIndexa;

        this.ocjene = new HashMap<>();
        this.fakultet = fakultet;
        this.upisaniSemestar = fakultet.semestri.get(0);
        this.listaUpisanihPredmeta = new ArrayList<>();

        fakultet.upisiStudenta(this);
        upisiSemestar();
    }

    public void upisiSemestar(){
        if(!listaUpisanihPredmeta.isEmpty()){
            listaUpisanihPredmeta.forEach(predmet -> predmet.ispisiStudenta(this));
            listaUpisanihPredmeta.clear();
            int index = fakultet.semestri.indexOf(upisaniSemestar);
            if(index == fakultet.semestri.size()-1)
                throw new IllegalArgumentException("Student je završio ciklus studija!");
            upisaniSemestar = fakultet.semestri.get(index+1);
        }

        int brojEcts = upisiObaveznePredmete();
        upisiIzbornePredmete(brojEcts);
    }

    int upisiObaveznePredmete(){
        int brojECTS = 0;
        for(Predmet predmet : upisaniSemestar.getListaPredmet()){
            if(predmet.isObavezniPredmet()) {
                listaUpisanihPredmeta.add(predmet);
                brojECTS = brojECTS + predmet.getBrojEcts();
                predmet.upisiStudenta(this);
            }
        }
        return brojECTS;
    }

    void upisiIzbornePredmete(int brojEcts){
        int broj = (int) upisaniSemestar.getListaPredmet().stream().filter(predmet -> !predmet.isObavezniPredmet()).count();
        if(broj != 0) {
            System.out.println("Izaberite izborne predmete:");
            int i = 1;
            for(Predmet predmet : upisaniSemestar.getListaPredmet()){
                String s = i + ". " + predmet.getNazivPredmeta() + " ";
                if(predmet.isObavezniPredmet()){
                    s = s + "(obavezan)";
                }else{
                    s = s + "(izborni)";
                }
                System.out.println(s);
                i = i + 1;
            }
            Scanner in = new Scanner(System.in);
            int ulaz;
            do {
                System.out.println("Broj ECTS = " + brojEcts + ". Odaberite predmet:");
                if (brojEcts < 30)
                    System.out.println("Odaberite još predmeta!");
                ulaz = in.nextInt();
                ulaz = ulaz - 1;
                if(brojEcts >= 30)
                    break;
                if (ulaz >= upisaniSemestar.getListaPredmet().size() || ulaz < 0 ||
                        upisaniSemestar.getListaPredmet().get(ulaz).isObavezniPredmet()) {
                    System.out.println("Neispravan index ili predmet nije izborni!");
                } else {
                    try {
                        upisaniSemestar.getListaPredmet().get(ulaz).upisiStudenta(this);
                        brojEcts = brojEcts + upisaniSemestar.getListaPredmet().get(ulaz).getBrojEcts();
                        listaUpisanihPredmeta.add(upisaniSemestar.getListaPredmet().get(ulaz));
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }
            } while (brojEcts < 30);
        }
    }

    public void upisiOcjenu(int ulaz, Integer ocjena){
         if(ocjene.containsKey(listaUpisanihPredmeta.get(ulaz)))
             throw new IllegalArgumentException("Ocjena je već upisana iz predmeta " + listaUpisanihPredmeta.get(ulaz).getNazivPredmeta() + ".");
         ocjene.put(listaUpisanihPredmeta.get(ulaz), ocjena);
    }

    @Override
    public String toString(){
        String s = "Student " + ime + " " + prezime + " (" + brojIndexa + ")" + ":\n";
        int i = 1;
        for(Map.Entry<Predmet, Integer> entry : getOcjene().entrySet()){
            s = s + i + ". " + entry.getKey().getNazivPredmeta() + " " + entry.getValue() + "\n";
            i = i + 1;
        }
        return s;
    }

    @Override
    public boolean equals(Object o){
        return Objects.equals(((Student) o).getIme(), this.getIme()) &&
                Objects.equals(((Student) o).getPrezime(), this.getPrezime()) &&
                ((Student)o).getBrojIndexa() == this.getBrojIndexa() &&
                ((Student)o).fakultet == this.fakultet;
    }

    public int getBrojIndexa(){
        return brojIndexa;
    }

    public Map<Predmet, Integer> getOcjene() {
        return ocjene;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public List<Predmet> getListaUpisanihPredmeta(){ return listaUpisanihPredmeta; }
}
