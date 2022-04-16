package ba.unsa.etf.rpr.t7;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class KorisniciModel {
    private static KorisniciModel instance = null;
    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();
    private PreparedStatement dajKorisnikeStatement, dodajKorisnikaStatement, promijeniKorisnikaStatement, obrisiKorisnikaStatement, dajZadnjiUmetnutiId;
    private Connection conn;

    public KorisniciModel() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:korisnici.db");
            dajKorisnikeStatement = conn.prepareStatement("SELECT * FROM korisnik");
            promijeniKorisnikaStatement = conn.prepareStatement("UPDATE korisnik SET ime =?, prezime=?, email=?,username=?,password=?,slika=? WHERE id=?");
            obrisiKorisnikaStatement = conn.prepareStatement("DELETE FROM korisnik WHERE id = ?");
            dodajKorisnikaStatement = conn.prepareStatement("INSERT INTO korisnik VALUES(?,?,?,?,?,?,?)");
            dajZadnjiUmetnutiId = conn.prepareStatement("SELECT MAX(id) FROM korisnik");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                dajKorisnikeStatement = conn.prepareStatement("SELECT * FROM korisnik");
                promijeniKorisnikaStatement = conn.prepareStatement("UPDATE korisnik SET ime =?, prezime=?, email=?,username=?,password=?,slika=? WHERE id=?");
                obrisiKorisnikaStatement = conn.prepareStatement("DELETE FROM korisnik WHERE id = ?");
                dodajKorisnikaStatement = conn.prepareStatement("INSERT INTO korisnik VALUES(?,?,?,?,?,?,?)");
                dajZadnjiUmetnutiId = conn.prepareStatement("SELECT MAX(id) FROM korisnik");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static KorisniciModel getInstance() {
        if (instance == null) instance = new KorisniciModel();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) {
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }

    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("korisnici_dump.sql"));
            String sqlUpit = "";
            while(ulaz.hasNext()){
                sqlUpit+=ulaz.nextLine();
                if(sqlUpit.length()>1 && sqlUpit.charAt(sqlUpit.length()-1)==';'){
                    try {
                        Statement stmt=conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka, nastavljam sa praznom bazom");
            e.printStackTrace();
        }
    }

    public void napuni() {
        // Ako je lista već bila napunjena, praznimo je
        // Na taj način se metoda napuni() može pozivati više puta u testovima
        korisnici.clear();

//        korisnici.add(new Korisnik("Vedran", "Ljubović", "vljubovic@etf.unsa.ba", "vedranlj", "test"));
//        korisnici.add(new Korisnik("Amra", "Delić", "adelic@etf.unsa.ba", "amrad", "test"));
//        korisnici.add(new Korisnik("Tarik", "Sijerčić", "tsijercic1@etf.unsa.ba", "tariks", "test"));
//        korisnici.add(new Korisnik("Rijad", "Fejzić", "rfejzic1@etf.unsa.ba", "rijadf", "test"));

        try {
            ResultSet korisniciResult = dajKorisnikeStatement.executeQuery();
            while(korisniciResult.next()) {
                Korisnik korisnik = new Korisnik(korisniciResult.getString(2), korisniciResult.getString(3),
                        korisniciResult.getString(4), korisniciResult.getString(5),
                        korisniciResult.getString(6));
                korisnik.setSlika(korisniciResult.getString(7));
                korisnik.setId(korisniciResult.getInt(1));
                korisnici.add(korisnik);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        trenutniKorisnik.set(null);
    }

    public void vratiNaDefault() {
        // Dodali smo metodu vratiNaDefault koja trenutno ne radi ništa, a kada prebacite Model na DAO onda
        // implementirajte ovu metodu
        // Razlog za ovo je da polazni testovi ne bi padali nakon što dodate bazu
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM korisnik");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        regenerisiBazu();
    }

    public void diskonektuj() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        if(this.getTrenutniKorisnik()!=null)
            promijeniKorisnika();
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public void setTrenutniKorisnik(int i) {
        if(this.getTrenutniKorisnik()!=null)
            promijeniKorisnika();
        this.trenutniKorisnik.set(korisnici.get(i));
    }

    public void promijeniKorisnika(){
        try {
            promijeniKorisnikaStatement.setString(1, this.getTrenutniKorisnik().getIme());
            promijeniKorisnikaStatement.setString(2, this.getTrenutniKorisnik().getPrezime());
            promijeniKorisnikaStatement.setString(3, this.getTrenutniKorisnik().getEmail());
            promijeniKorisnikaStatement.setString(4, this.getTrenutniKorisnik().getUsername());
            promijeniKorisnikaStatement.setString(5, this.getTrenutniKorisnik().getPassword());
            promijeniKorisnikaStatement.setString(6, this.getTrenutniKorisnik().getSlika());
            promijeniKorisnikaStatement.setInt(7, this.getTrenutniKorisnik().getId());
            promijeniKorisnikaStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void obrisiKorisnika(){
        try {
            obrisiKorisnikaStatement.setInt(1, this.getTrenutniKorisnik().getId());
            obrisiKorisnikaStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajKorisnika() {
        try {
            dodajKorisnikaStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int dajId(){
        int id = 0;
        try {
            id = dajZadnjiUmetnutiId.executeQuery().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void zapisiDatoteku(File file){
        if(file!=null) {
            PrintWriter izlaz = null;
            try {
                izlaz = new PrintWriter(file);
                ResultSet result = null;
                try {
                    result = dajKorisnikeStatement.executeQuery();
                    while (result.next()) {
                        izlaz.println(result.getString(5) + ":" + result.getString(6) + ":" + result.getInt(1) + ":"
                                + result.getInt(1) + ":" + result.getString(2) + " " + result.getString(3) + "::");
                    }
                } catch (SQLException e) {
                    System.out.println("Greška u queryu baze");
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Datoteka se ne može otvoriti");
            }finally {
                izlaz.close();
            }
        }
    }

    public Connection getConn() { return conn; }
}
