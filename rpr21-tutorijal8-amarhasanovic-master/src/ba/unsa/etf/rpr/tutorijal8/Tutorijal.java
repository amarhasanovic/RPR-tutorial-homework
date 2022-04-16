package ba.unsa.etf.rpr.tutorijal8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {
    public static ArrayList<Grad> ucitajGradove(){
        ArrayList<Grad> gradovi = new ArrayList<>();

        Scanner ulaz;
        try {
            ulaz = new Scanner(new FileReader("mjerenja.txt"));
            while(ulaz.hasNextLine()) {
                String temp = ulaz.nextLine();
                String razdvojeni[] = temp.split(",");

                double[] temperature = new double[1000];

                for(int i=0; i<1000 && i<razdvojeni.length-1; i++){
                    temperature[i] = Double.parseDouble(razdvojeni[i+1]);
                }
                Grad grad = new Grad();
                grad.setBrojStanovnika(0);
                grad.setNaziv(razdvojeni[0]);
                grad.setTemperature(temperature);

                gradovi.add(grad);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datoteka ulaz.txt ne postoji ili se ne može otvoriti");
        }


        return gradovi;
    }
    public static void main(String[] args) {

    }

    public static UN ucitajXml(ArrayList<Grad> gradovi) {
        Document xml = null;
        UN ujedinjeneNacije = new UN();

        try {
            DocumentBuilder ulaz = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xml = ulaz.parse(new File("drzave.xml"));

            Element rootElement = xml.getDocumentElement();
            NodeList drzave = rootElement.getChildNodes();

            for(int i=0; i<drzave.getLength(); i++) {
                Node trenutnaDrzava = drzave.item(i);

                if(trenutnaDrzava instanceof Element) {
                    Drzava novaDrzava = new Drzava();
                    NodeList podaciODrzavi = trenutnaDrzava.getChildNodes();

                    for(int j=0; j<podaciODrzavi.getLength(); j++) {
                        Node temp = podaciODrzavi.item(j);

                        if(temp instanceof Element) {
                            switch (temp.getNodeName()) {
                                case "naziv" -> novaDrzava.setNaziv(temp.getTextContent());
                                case "povrsina" -> {
                                    novaDrzava.setPovrsina(Double.parseDouble(temp.getTextContent()));
                                    novaDrzava.setJedinica(((Element) temp).getAttribute("jedinica"));
                                }
                                case "glavnigrad" -> {
                                    novaDrzava.setGlavniGrad(new Grad(temp.getTextContent().trim(), Integer.parseInt(((Element) temp).getAttribute("stanovnika"))));
                                    for(var x : gradovi) {
                                        if(x.getNaziv().equals(temp.getTextContent().trim()))
                                            x.setBrojStanovnika(Integer.parseInt(((Element) temp).getAttribute("stanovnika")));
                                    }
                                }
                            }
                        }
                    }
                    ujedinjeneNacije.getDrzave().add(novaDrzava);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ujedinjeneNacije;
    }

    public static void zapisiXml(UN ujedinjeneNacije) {
        try {
            XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(ujedinjeneNacije);
            izlaz.close();
        } catch (Exception e) {
            System.out.println("Greška: " + e);
        }
    }
}
