package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private PreparedStatement dajGradoveStatement, dajDrzaveStatement, obrisiDrzavuStatement, obrisiGradoveStatement;
    private PreparedStatement dodajGradStatement, dodajDrzavuStatement, izmijeniGradStatement, dajSljedeciIdDrzave, dajSljedeciIdGrada;
    private PreparedStatement dajDrzave2Statement, dajDrzave1Statement, obrisiGradStatement;
    private Connection conn;
    private ArrayList<Drzava> drzave;
    private GeografijaDAO(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            dajGradoveStatement = conn.prepareStatement("SELECT * FROM grad");
            dajDrzaveStatement = conn.prepareStatement("SELECT * FROM drzava WHERE drzava.id = ?");
            dajDrzave1Statement = conn.prepareStatement("SELECT * FROM drzava");
            obrisiDrzavuStatement = conn.prepareStatement("DELETE FROM drzava WHERE drzava.id=?");
            obrisiGradoveStatement = conn.prepareStatement("DELETE FROM grad WHERE grad.drzava=?");
            obrisiGradStatement = conn.prepareStatement("DELETE FROM grad WHERE id=?");
            dodajGradStatement = conn.prepareStatement("INSERT INTO grad VALUES(?,?,?,?)");
            dodajDrzavuStatement = conn.prepareStatement("INSERT INTO drzava VALUES(?,?,?)");
            izmijeniGradStatement = conn.prepareStatement("UPDATE grad SET naziv = ?, broj_stanovnika = ?, drzava = ? WHERE id = ?");
            dajSljedeciIdDrzave = conn.prepareStatement("SELECT MAX(id)+1 FROM drzava");
            dajSljedeciIdGrada = conn.prepareStatement("SELECT MAX(id)+1 FROM grad");
            dajDrzave2Statement = conn.prepareStatement("SELECT * FROM drzava WHERE naziv = ?");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                dajGradoveStatement = conn.prepareStatement("SELECT * FROM grad");
                dajDrzaveStatement = conn.prepareStatement("SELECT * FROM drzava WHERE drzava.id = ?");
                dajDrzave1Statement = conn.prepareStatement("SELECT * FROM drzava");
                obrisiDrzavuStatement = conn.prepareStatement("DELETE FROM drzava WHERE id=?");
                obrisiGradoveStatement = conn.prepareStatement("DELETE FROM grad WHERE grad.drzava=?");
                obrisiGradStatement = conn.prepareStatement("DELETE FROM grad WHERE id=?");
                dodajGradStatement = conn.prepareStatement("INSERT INTO grad VALUES(?,?,?,?)");
                dodajDrzavuStatement = conn.prepareStatement("INSERT INTO drzava VALUES(?,?,?)");
                izmijeniGradStatement = conn.prepareStatement("UPDATE grad SET naziv = ?, broj_stanovnika = ?, drzava = ? WHERE id = ?");
                dajSljedeciIdDrzave = conn.prepareStatement("SELECT MAX(id)+1 FROM drzava");
                dajSljedeciIdGrada = conn.prepareStatement("SELECT MAX(id)+1 FROM grad");
                dajDrzave2Statement = conn.prepareStatement("SELECT * FROM drzava WHERE naziv = ?");
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

    public void vratiBazuNaDefault() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM grad");
            stmt.executeUpdate("DELETE FROM drzava");
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
                Drzava drzava = null;
                if(resultDrzava.next()) {
                    drzava = new Drzava(resultDrzava.getInt(1),
                            resultDrzava.getString(2));
                }
                Grad grad = new Grad(resultGrad.getInt(1), resultGrad.getString(2),
                        resultGrad.getInt(3), drzava);
                if(drzava != null)
                    drzava.setGlavniGrad(grad);
                rezultat.add(grad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rezultat = (ArrayList<Grad>) rezultat.stream().sorted((a, b) -> Integer.compare(b.getBrojStanovnika(), a.getBrojStanovnika())).collect(Collectors.toList());
        return rezultat;
    }

    public void obrisiDrzavu(String drzava) {
        ArrayList<Grad> gradoviLista = this.gradovi();
        int id = -1;
        for(Grad grad : gradoviLista){
            if(grad.getDrzava().getNaziv().equals(drzava)){
                id = grad.getDrzava().getId();
            }
        }
        if(id != -1){
            try {
                obrisiDrzavuStatement.setInt(1, id);
                obrisiGradoveStatement.setInt(1, id);
                obrisiGradoveStatement.executeUpdate();
                obrisiDrzavuStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void obrisiGrad(String grad){
        ArrayList<Grad> gradoviLista = this.gradovi();
        int id = -1;
        for(Grad x : gradoviLista){
            if(x.getNaziv().equals(grad)){
                id = x.getId();
            }
        }
        try {
            obrisiGradStatement.setInt(1, id);
            obrisiGradStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Grad glavniGrad(String drzava) {
        ArrayList<Grad> gradoviLista = this.gradovi();
        for(Grad grad : gradoviLista){
            if(grad.getDrzava().getNaziv().equals(drzava)){
                return grad.getDrzava().getGlavniGrad();
            }
        }
        return null;
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            int id = dajSljedeciIdDrzave.executeQuery().getInt(1);
            drzava.setId(id);
            dodajDrzavuStatement.setInt(1, id);
            dodajDrzavuStatement.setString(2, drzava.getNaziv());
            if(drzava.getGlavniGrad() != null)
                dodajDrzavuStatement.setInt(3, drzava.getGlavniGrad().getId());
            dodajDrzavuStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajGrad(Grad grad) {
        try {
            int id = dajSljedeciIdGrada.executeQuery().getInt(1);
            grad.setId(id);
            dodajGradStatement.setInt(1, id);
            dodajGradStatement.setString(2, grad.getNaziv());
            dodajGradStatement.setInt(3, grad.getBrojStanovnika());
            if(grad.getDrzava() != null)
                dodajGradStatement.setInt(4, grad.getDrzava().getId());
            dodajGradStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            izmijeniGradStatement.setString(1, grad.getNaziv());
            izmijeniGradStatement.setInt(2, grad.getBrojStanovnika());
            if(grad.getDrzava() != null)
                izmijeniGradStatement.setInt(3, grad.getDrzava().getId());
            izmijeniGradStatement.setInt(4, grad.getId());
            izmijeniGradStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String drzava) {
        try {
            dajDrzave2Statement.setString(1, drzava);
            ResultSet result = dajDrzave2Statement.executeQuery();
            Drzava drzava1 =  new Drzava(result.getInt(1), result.getString(2));
            return drzava1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Grad nadjiGrad(String grad) {
        ArrayList<Grad> gradoviLista = gradovi();
        for(Grad x : gradoviLista){
            if(x.getNaziv().equals(grad)){
                return x;
            }
        }
        return null;
    }

    public ArrayList<Drzava> drzave() {
        ArrayList<Drzava> rezultat = new ArrayList<>();
        try {
            ResultSet resultSet = dajDrzave1Statement.executeQuery();
            while(resultSet.next()){
                Drzava drzava = new Drzava(resultSet.getInt(1), resultSet.getString(2));
                rezultat.add(drzava);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }
}
