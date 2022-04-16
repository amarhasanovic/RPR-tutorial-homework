package ba.unsa.etf.rpr.z2;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static Fakultet setUp(){
        Fakultet fakultet = new Fakultet("Elektrotehnički fakultet");
        String planStudija = "Računarstvo i informatika";
        Semestar semestar1 = new Semestar("I", planStudija, "I");
        Semestar semestar2 = new Semestar("II", planStudija, "I");
        Semestar semestar3 = new Semestar("III", planStudija, "I");
        Semestar semestar4 = new Semestar("IV", planStudija, "I");

        Profesor vedran = new Profesor("Vedran", "Ljubović", fakultet);
        Profesor naida = new Profesor("Naida", "Mujić", fakultet);
        Profesor dijana = new Profesor("Dijana", "Dujak", fakultet);
        Profesor almasa = new Profesor("Almasa", "Odžak", fakultet);
        Profesor zeljko = new Profesor("Željko", "Jurić", fakultet);
        Profesor samir = new Profesor("Samir", "Ribić", fakultet);
        Profesor amela = new Profesor("Amela", "Ribić", fakultet);
        Profesor tarik = new Profesor("Tark", "Uzunović", fakultet);
        Profesor dinko = new Profesor("Dinko", "Osmanović", fakultet);
        Profesor haris = new Profesor("Haris", "Šupić", fakultet);
        Profesor novica = new Profesor("Novica", "Nosović", fakultet);
        Profesor emir = new Profesor("Emir", "Buza", fakultet);
        Profesor kemal = new Profesor("Kemal", "Hajdarević", fakultet);
        Profesor vensada = new Profesor("Vensada", "Okanović", fakultet);
        Profesor amila = new Profesor("Amila", "Akagić", fakultet);

        //Prvi semestar
        Predmet predmet1 = new Predmet("Uvod u programiranje", 6, 44, vedran, true);
        Predmet predmet2 = new Predmet("Inženjerska matematika 1", 7, 49, naida, true);
        Predmet predmet3 = new Predmet("Inženjerska fizika 1", 5, 39, dijana, true);
        Predmet predmet4 = new Predmet("Linearna algebra i geometrija", 5, 39, almasa, true);
        Predmet predmet5 = new Predmet("Osnove elektrotehnike I", 7, 52, tarik, true);
        semestar1.dodajPredmet(predmet1);
        semestar1.dodajPredmet(predmet2);
        semestar1.dodajPredmet(predmet3);
        semestar1.dodajPredmet(predmet4);
        semestar1.dodajPredmet(predmet5);

        //Drugi semestar
        Predmet predmet6 = new Predmet("Tehnike programiranja", 6, 44, zeljko, true);
        Predmet predmet7 = new Predmet("Inženjerska matematika 2", 7, 52, naida, true);
        Predmet predmet8 = new Predmet("Operativni sistemi", 5, 38, samir, false);
        Predmet predmet9 = new Predmet("Vjerovatnoća i statistika", 5, 38, amela, false);
        Predmet predmet10 = new Predmet("Matematička logika i teorija izračunljivosti", 7, 42, dinko, false);
        semestar2.dodajPredmet(predmet6);
        semestar2.dodajPredmet(predmet7);
        semestar2.dodajPredmet(predmet8);
        semestar2.dodajPredmet(predmet9);
        semestar2.dodajPredmet(predmet10);

        //Treci semestar
        Predmet predmet11 = new Predmet("Algoritmi i strukture podataka", 5, 38, haris, true);
        Predmet predmet12 = new Predmet("Logički dizajn", 5, 38, novica, true);
        Predmet predmet13 = new Predmet("Razvoj programskih rješenja", 5, 38, vedran, true);
        Predmet predmet14 = new Predmet("Osnove baza podataka", 5, 38, emir, true);
        Predmet predmet15 = new Predmet("Diskretna matematika", 5, 39, zeljko, true);
        Predmet predmet16 = new Predmet("Sistemsko programiranje", 5, 36, samir, false);
        Predmet predmet17 = new Predmet("Numerički algoritmi", 5, 35, zeljko, false);
        semestar3.dodajPredmet(predmet11);
        semestar3.dodajPredmet(predmet12);
        semestar3.dodajPredmet(predmet13);
        semestar3.dodajPredmet(predmet14);
        semestar3.dodajPredmet(predmet15);
        semestar3.dodajPredmet(predmet16);
        semestar3.dodajPredmet(predmet17);

        //Cetvrti semestar
        Predmet predmet18 = new Predmet("Računarske arhitekture", 5, 40, novica, true);
        Predmet predmet19 = new Predmet("Osnove računarskih mreža", 5, 40, kemal, true);
        Predmet predmet20 = new Predmet("Objektno orijentisana analiza i dizajn", 5, 38, vedran, true);
        Predmet predmet21 = new Predmet("Automati i formalni jezici", 5, 38, haris, true);
        Predmet predmet22 = new Predmet("Razvoj mobilnih aplikacija", 5, 35, vensada, false);
        Predmet predmet23 = new Predmet("CAD-CAM inžinjering", 5, 35, vedran, false);
        Predmet predmet24 = new Predmet("Ugradbeni sistemi", 5, 28, samir, false);
        Predmet predmet25 = new Predmet("Digitalno procesiranje signala", 5, 28, amila, false);
        semestar4.dodajPredmet(predmet18);
        semestar4.dodajPredmet(predmet19);
        semestar4.dodajPredmet(predmet20);
        semestar4.dodajPredmet(predmet21);
        semestar4.dodajPredmet(predmet22);
        semestar4.dodajPredmet(predmet23);
        semestar4.dodajPredmet(predmet24);
        semestar4.dodajPredmet(predmet25);

        fakultet.dodajSemestar(semestar1);
        fakultet.dodajSemestar(semestar2);
        fakultet.dodajSemestar(semestar3);
        fakultet.dodajSemestar(semestar4);

        return fakultet;
    }

    public static void ispisOpcija(Fakultet fakultet){
        System.out.println(fakultet.nazivFakulteta);
        String s = "-------------------------------------------------------------------------------------------------\n";
        s = s + "1. Upiši studenta\n2. Ispis profesora bez norme\n3. Ispis profesora koji rade preko norme\n";
        s = s + "4. Ispis profesora sortiranih prema normi\n5. Ispis profesora sortiranih prema broju studenata\n";
        s = s + "6. Upisi ocjenu studentu\n7. Prepis ocjena\n8. Upisi studenta u sljedeći semestar\n0. (za kraj)\n";
        s = s + "-------------------------------------------------------------------------------------------------";
        System.out.println(s);
    }

    public static void main(String[] args){
        Fakultet fakultet = setUp();
        Scanner ulaz = new Scanner(System.in);
        int input = 1;
        int brojIndexa = 18000;
        while(input != 0) {
            ispisOpcija(fakultet);
            try {
                input = ulaz.nextInt();
            }catch (Exception e){
                ulaz.nextLine();
                input = -1;
            }
            switch (input) {
                case 0 -> {
                    break;
                }
                case 1 -> {
                    String ime;
                    String prezime;
                    System.out.print("Unesite ime studenta: ");
                    Scanner unosStudenta = new Scanner(System.in);
                    ime = unosStudenta.nextLine();
                    System.out.print("Unesite prezime studenta: ");
                    prezime = unosStudenta.nextLine();
                    new Student(ime, prezime, brojIndexa, fakultet);
                    brojIndexa = brojIndexa + 1;
                }
                case 2 -> fakultet.dajListuProfesoraPremaNormi().stream().filter(profesor -> profesor.brojCasova < 120).forEach(System.out::println);
                case 3 -> fakultet.dajListuProfesoraPremaNormi().stream().filter(profesor -> profesor.brojCasova > 150).forEach(System.out::println);
                case 4 -> fakultet.dajListuProfesoraPremaNormi().forEach(System.out::println);
                case 5 -> fakultet.dajListuProfesoraPremaBrojuStudenata().forEach(System.out::println);
                case 6 -> {
                    System.out.println("Odaberite studenta za upis ocjene:");
                    int j = 1;
                    for (Student temp : fakultet.getUpisaniStudenti()) {
                        System.out.println(j + ". " + temp.getIme() + " " + temp.getPrezime() + " (" + temp.getBrojIndexa() + ")");
                        j = j + 1;
                    }
                    Scanner in = new Scanner(System.in);
                    int studentId = in.nextInt() - 1;
                    if (studentId > fakultet.getUpisaniStudenti().size() - 1 || studentId < 0)
                        System.out.println("Nevalidan odabir studenta!");
                    else {
                        System.out.println("Odaberite predmet za upis ocjene (0 za izlaz):");
                        Student odabraniStudent = fakultet.getUpisaniStudenti().get(studentId);
                        j = 1;
                        for (Predmet predmet : odabraniStudent.listaUpisanihPredmeta) {
                            System.out.println(j + ". " + predmet.getNazivPredmeta());
                            j = j + 1;
                        }
                        int predmetId = in.nextInt() - 1;
                        if (predmetId != -1) {
                            try {
                                System.out.print("Unesite ocjenu: ");
                                Scanner unosOcjene = new Scanner(System.in);
                                Integer ocjena = unosOcjene.nextInt();
                                odabraniStudent.upisiOcjenu(predmetId, ocjena);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
                case 7 -> {
                    System.out.println("Odaberite studenta za kojeg želite prepis ocjena:");
                    int i = 1;
                    for (Student temp : fakultet.getUpisaniStudenti()) {
                        System.out.println(i + ". " + temp.getIme() + " " + temp.getPrezime() + " (" + temp.getBrojIndexa() + ")");
                        i = i + 1;
                    }
                    int odabirStudenta = (new Scanner(System.in)).nextInt() - 1;
                    if (odabirStudenta > fakultet.getUpisaniStudenti().size() - 1 || odabirStudenta < 0)
                        System.out.println("Neispravan index studenta!");
                    else {
                        System.out.println(fakultet.getUpisaniStudenti().get(odabirStudenta));
                    }
                }
                case 8 -> {
                    System.out.println("Odaberite studenta kojeg upisujete u sljedeci semestar:");
                    int i = 1;
                    for (Student temp : fakultet.getUpisaniStudenti()) {
                        System.out.println(i + ". " + temp.getIme() + " " + temp.getPrezime() + " (" + temp.getBrojIndexa() + ")");
                        i = i + 1;
                    }
                    Scanner in = new Scanner(System.in);
                    int studentId = in.nextInt();
                    if (studentId > fakultet.getUpisaniStudenti().size() || studentId < 1)
                        System.out.println("Nevalidan odabir studenta!");
                    else {
                        try {
                            fakultet.getUpisaniStudenti().get(studentId - 1).upisiSemestar();
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                }
                default -> { System.out.println("Nevalidna instrukcija!"); }
            }
        }
    }
}
