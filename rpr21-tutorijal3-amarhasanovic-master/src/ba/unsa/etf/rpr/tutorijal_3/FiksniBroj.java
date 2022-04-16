package ba.unsa.etf.rpr.tutorijal_3;

public class FiksniBroj implements TelefonskiBroj, Comparable{
    enum Grad{TRAVNIK, ORASJE, ZENICA, SARAJEVO, LIVNO, TUZLA, MOSTAR, BIHAC, GORAZDE, SIROKIBRIJEG, BRCKO}
    private Grad grad;
    private String broj;

    public FiksniBroj(Grad grad, String broj) {
        this.grad = grad;
        this.broj = broj;
    }

    public Grad getGrad() {
        return grad;
    }

    public String getBroj() {
        return broj;
    }

    @Override
    public String ispisi(){
        String s = "";
        switch(this.grad){
            case BIHAC:
                s = s + "037";
                break;
            case BRCKO:
                s = s + "049";
                break;
            case LIVNO:
                s = s + "034";
                break;
            case TUZLA:
                s = s + "035";
                break;
            case MOSTAR:
                s = s + "036";
                break;
            case ORASJE:
                s = s + "031";
                break;
            case ZENICA:
                s = s + "032";
                break;
            case GORAZDE:
                s = s + "038";
                break;
            case TRAVNIK:
                s = s + "030";
                break;
            case SARAJEVO:
                s = s + "033";
                break;
            case SIROKIBRIJEG:
                s = s + "039";
                break;
        }
        return s + "/" + broj;
    }

    @Override
    public int hashCode() {
        return ispisi().hashCode();
    }

    @Override
    public int compareTo(Object o) {
        FiksniBroj privremeni = (FiksniBroj) o;
        return this.ispisi().compareTo(privremeni.ispisi());
    }
}
