package ba.unsa.etf.rpr;

public class Odgovor {
    String tekstOdgovora;
    boolean tacno;

    public Odgovor(String tekstOdgovora, boolean tacno) {
        this.tekstOdgovora = tekstOdgovora;
        this.tacno = tacno;
    }

    @Override
    public boolean equals(Object o){
        Odgovor odgovor = (Odgovor) o;
        return tekstOdgovora.equals(((Odgovor) o).getTekstOdgovora()) && tacno == ((Odgovor) o).isTacno();
    }
    public void setTekstOdgovora(String tekstOdgovora) {
        this.tekstOdgovora = tekstOdgovora;
    }

    public void setTacno(boolean tacno) {
        this.tacno = tacno;
    }

    public String getTekstOdgovora() {
        return tekstOdgovora;
    }

    public boolean isTacno() {
        return tacno;
    }
}
