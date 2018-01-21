package dom.sax;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import org.xml.sax.SAXException;
 
public class EjemploDOM3 {
 
 public static void main(String argv[]) {
 
 try {
 
    File fXmlFile = new File("libros_bd.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(fXmlFile);
    doc.getDocumentElement().normalize();
 
    System.out.println("Elemento Raíz:" + doc.getDocumentElement().getNodeName());
    NodeList nList = doc.getElementsByTagName("libro");
    System.out.println("-----------------------");
 
    for (int temp = 0; temp < nList.getLength(); temp++) {
 
       Node nNode = nList.item(temp);	    
       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
          Element eElement = (Element) nNode;
          
          System.out.println("*******");
          System.out.println("Titulo:    "  + valorNodoHoja("titulo",eElement));
          System.out.println("autor:     "  + trataAutor (eElement));
          System.out.println("editorial: "  + valorNodoHoja("editorial",eElement));
          System.out.println("año:       "  + valorNodoHoja("año",eElement));
          System.out.println("precio:    "  + valorNodoHoja("precio",eElement)); 
        }
    }
  } catch (ParserConfigurationException | SAXException | IOException e) {
  }
 }
 
 // Obtenemos el valor de los nodos hoja titulo, editorial, año y precio
 private static String valorNodoHoja (String sTag, Element eElement){
	 
	 NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 Node nValue = (Node) nlList.item(0); 
 
    return nValue.getNodeValue();  
 }
 
 // Hacemos un tratamient especial para los elementos AUTOR
 // Dado que puede haber más de un autor y además tiene nodos hijos apèllidos y nombre
 private static String trataAutor (Element elementolibro){
		String salida = "";	
		NodeList listautores = elementolibro.getElementsByTagName("autor");
		for (int i= 0; i < listautores .getLength(); i++){
			Node nodoautor = (Node) listautores.item(i); // un nodo autor concreto
			if (nodoautor.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoautor = (Element) nodoautor;
				
				NodeList listaApel = elementoautor.getElementsByTagName("apellidos").item(0).getChildNodes();
				Node apel = (Node) listaApel.item(0); 
				NodeList listaNom = elementoautor.getElementsByTagName("nombre").item(0).getChildNodes();
				Node nombre = (Node) listaNom.item(0);
				if (!"".equals(salida)) {
					salida = salida + "; ";
				}
				salida = salida + nombre.getNodeValue() + " " + apel.getNodeValue();		 
				
             }   
				
		}
		

			

			
     return salida;    	 
	}	
}