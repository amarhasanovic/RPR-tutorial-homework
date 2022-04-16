package ba.unsa.etf.rpr.tutorijal_3;

public class MobilniBroj implements TelefonskiBroj, Comparable {
    private int mobilnaMreza;
    private String broj;

    public MobilniBroj(int mobilnaMreza, String broj) {
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }

    public int getMobilnaMreza() {
        return mobilnaMreza;
    }

    public String getBroj() {
        return broj;
    }

    @Override
    public String ispisi() {
        String s = "";
        s = s + "0" + mobilnaMreza + "/" + broj;
        return s;
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
