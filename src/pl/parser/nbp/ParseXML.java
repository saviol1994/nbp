package pl.parser.nbp;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class ParseXML {


    public  Currency parseXmlFile(String path,String currencyToFind){
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL(path).openStream());
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName("pozycja");


            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String kod_waluty =getValue("kod_waluty", element);


                    if(kod_waluty.equals(currencyToFind))
                        return new Currency(getValue("kod_waluty", element),getValue("kurs_kupna", element)
                                ,getValue("kurs_sprzedazy", element));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String getValue(String tag, Element element) {


        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodes.item(0);

        return node.getNodeValue();

    }
}
