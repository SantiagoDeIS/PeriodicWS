/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientjavawsperiodictable;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author entrar
 */
public class ClientJavaWSPeriodictable {

    /**
     * @param args the command line arguments
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        // TODO code application logic here
        /*System.out.println(getAtomicNumber("Helium"));
        System.out.println(getAtomicWeight("Helium"));
        System.out.println(getAtoms());
        System.out.println(getElementSymbol("Helium"));*/
        
        System.out.println("pulse 1 para mostrar todos los elementos.");
        System.out.println("pulse 2 para introducir un elemento por teclado.");
        Scanner s = new Scanner(System.in);
        int val = s.nextInt();
        switch (val) {
            case 1:
                getElement(getAtoms(),"ElementName");
                break;
            case 2:
                String text = s.next();
                getElement(getAtomicNumber(text),"AtomicNumber");
                getElement(getAtomicWeight(text),"AtomicWeight");
                getElement(getElementSymbol(text),"Symbol");
                break;
            default:
                System.out.println("Valor introducido no correcto");
                break;
        }
        
    }

    private static String getAtomicNumber(java.lang.String elementName) {
        net.webservicex.Periodictable service = new net.webservicex.Periodictable();
        net.webservicex.PeriodictableSoap port = service.getPeriodictableSoap();
        return port.getAtomicNumber(elementName);
    }

    private static String getAtomicWeight(java.lang.String elementName) {
        net.webservicex.Periodictable service = new net.webservicex.Periodictable();
        net.webservicex.PeriodictableSoap port = service.getPeriodictableSoap();
        return port.getAtomicWeight(elementName);
    }

    private static String getAtoms() {
        net.webservicex.Periodictable service = new net.webservicex.Periodictable();
        net.webservicex.PeriodictableSoap port = service.getPeriodictableSoap();
        return port.getAtoms();
    }

    private static String getElementSymbol(java.lang.String elementName) {
        net.webservicex.Periodictable service = new net.webservicex.Periodictable();
        net.webservicex.PeriodictableSoap port = service.getPeriodictableSoap();
        return port.getElementSymbol(elementName);
    }
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";

    }
    
    public static void getElement(String xml, String tag) throws SAXException, IOException, ParserConfigurationException{
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("Table");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            NodeList number = element.getElementsByTagName(tag);
            Element line = (Element) number.item(0);
            System.out.println(tag+": " + getCharacterDataFromElement(line));

        }
    }
    
}
