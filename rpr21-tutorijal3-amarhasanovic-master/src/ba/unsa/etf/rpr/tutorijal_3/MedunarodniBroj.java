package ba.unsa.etf.rpr.tutorijal_3;

public class MedunarodniBroj implements TelefonskiBroj, Comparable{
    String drzava;
    String broj;

    public MedunarodniBroj(String drzava, String broj) {
        this.drzava = drzava;
        this.broj = broj;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getBroj() {
        return broj;
    }

    @Override
    public String ispisi() {
        return drzava + broj;
    }

    @Override
    public int hashCode() {
        return ispisi().hashCode();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
