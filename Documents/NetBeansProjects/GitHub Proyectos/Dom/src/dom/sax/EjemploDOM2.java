package dom.sax;

import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import org.xml.sax.SAXException;

public class EjemploDOM2 {
	
	public static void main(String[] args) {

	    try {
	        // cargar el fichero xml
	        File f = new File("libros_BD.xml");

	        DocumentBuilderFactory xmlDocDB=DocumentBuilderFactory.newInstance();
	        DocumentBuilder xmlDoc=xmlDocDB.newDocumentBuilder ();
	        Element raizDocXml=xmlDoc.parse(f).getDocumentElement();

            System.out.println("Documento XML Cargado...");

	        if ("libros".equals(raizDocXml.getNodeName())) {      	   
	            	ObtenerTitulos(raizDocXml);	                          
	      }
	    } 
	    catch(ParserConfigurationException | SAXException | IOException e){
	    	System.out.println(e.toString());
	    }
	}
	
	// Obtener los valores de los títulos de los libros
	static void ObtenerTitulos(Element raizDocXml){
		try {
			NodeList nodos = raizDocXml.getChildNodes();
			for (int i= 0; i < nodos.getLength(); i++){
				//nodos hijos de libro
				Node nodo = nodos.item(i); // nodo libro
			
				// Compruebo si el nodo es de tipo elemento
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nodo;
					NodeList nodostitulo= eElement.getElementsByTagName("titulo").item(0).getChildNodes();
					Node titulo = (Node) nodostitulo.item(0); 
				 
					System.out.println("Titulo: " + titulo.getNodeValue());
                                       
	           	} 
			}
		}
	    catch(Exception e){
	    	System.out.println(e.toString());
	    }
    }

}