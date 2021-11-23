package design_pattern.lab1.简单工厂;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @author fang
 */
public class XMLUtil {
    public static String getPersonType(){
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = docFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("src//design_pattern//lab1//简单工厂//config.xml"));

            NodeList nodeList = doc.getElementsByTagName("personType");
            Node classNode = nodeList.item(0).getFirstChild();
            String personType = classNode.getNodeValue().trim();
            return personType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
