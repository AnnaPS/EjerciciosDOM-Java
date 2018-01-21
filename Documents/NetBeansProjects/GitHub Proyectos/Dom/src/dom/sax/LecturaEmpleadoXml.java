package dom.sax;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class LecturaEmpleadoXml {
 public static void main(String argv[]) {
  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  try {
	 
	  DocumentBuilder builder = factory.newDocumentBuilder();
	  Document document = builder.parse(new File("Empleados.xml"));
	  document.getDocumentElement().normalize();

	  System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
	  NodeList empleados = document.getElementsByTagName("empleado");

	  for (int i = 0; i < empleados.getLength(); i ++) {

	    Node emple = empleados.item(i);

	    if (emple.getNodeType() == Node.ELEMENT_NODE) {

            Element elemento = (Element) emple;

            System.out.println("ID: " + getNodo("id", elemento));
            System.out.println("Apellido: " + getNodo("apellido", elemento));
            System.out.println("Departamento: " + getNodo("dep", elemento));
			System.out.println("Salario: " + getNodo("salario", elemento));

	    }
    }
  } catch (ParserConfigurationException | SAXException | IOException e) {}
 }//fin de main
 
 //obtener la informaciÃ³n de un nodo
 private static String getNodo(String etiqueta, Element elem)
 {
	  NodeList nodo= elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
	  Node valornodo = (Node) nodo.item(0);
	  return valornodo.getNodeValue();
 }
}