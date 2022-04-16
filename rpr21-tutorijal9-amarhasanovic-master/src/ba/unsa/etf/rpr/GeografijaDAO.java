package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private PreparedStatement dajGradoveStatement, dajDrzaveStatement, dajNoviId, dodajGrad;
    private Connection conn;
    private GeografijaDAO(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            dajGradoveStatement = conn.prepareStatement("SELECT * FROM grad");
            dajDrzaveStatement = conn.prepareStatement("SELECT * FROM drzava WHERE drzava.id = ?");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                dajGradoveStatement = conn.prepareStatement("SELECT * FROM grad");
                dajDrzaveStatement = conn.prepareStatement("SELECT * FROM drzava WHERE drzava.id = ?");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static GeografijaDAO getInstance() {
        if (instance == null) instance = new GeografijaDAO();
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
            ulaz = new Scanner(new FileInputStream("baza.sql"));
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

    public void vratiNaDefault() {
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

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> rezultat = new ArrayList<>();
        try {
            ResultSet resultGrad = dajGradoveStatement.executeQuery();
            while(resultGrad.next()) {
                dajDrzaveStatement.setInt(1, resultGrad.getInt(4));
                ResultSet resultDrzava = dajDrzaveStatement.executeQuery();

                Drzava drzava = new Drzava(resultDrzava.getInt(1),
                        resultDrzava.getString(2));
                Grad grad = new Grad(resultGrad.getInt(1), resultGrad.getString(2),
                        resultGrad.getInt(3), drzava);
                drzava.setGlavniGrad(grad);
                rezultat.add(grad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Comparator<Grad> komp = (a, b)-> {
//            if(a.getBrojStanovnika() < b.getBrojStanovnika())
//                return b.getBrojStanovnika();
//            return a.getBrojStanovnika();
//        };
        rezultat = (ArrayList<Grad>) rezultat.stream().sorted((a, b) -> Integer.compare(b.getBrojStanovnika(), a.getBrojStanovnika())).collect(Collectors.toList());
        return rezultat;
    }

    public void obrisiDrzavu(String austrija) {
    }

    public Grad glavniGrad(String bosna_i_hercegovina) {
        return new Grad();
    }

    public void dodajDrzavu(Drzava bih) {
    }

    public void dodajGrad(Grad sarajevo) {
    }

    public void izmijeniGrad(Grad bech) {
    }

    public Drzava nadjiDrzavu(String francuska) {
        return new Drzava();
    }
}
