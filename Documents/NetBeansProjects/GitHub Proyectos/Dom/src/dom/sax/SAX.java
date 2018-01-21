
package dom.sax;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAX {
    
    public static void main(String[] args) throws SAXException, IOException{
        
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        GestionContenido gestor= new GestionContenido();
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource("Empleados.xml");
        procesadorXML.parse(fileXML);
    }


    private static class GestionContenido extends DefaultHandler {

        public GestionContenido() {
            super();
        }
        
        @Override
        public void startDocument(){
            System.out.println("Comienzo del documento XML");
        }
        
        @Override
        public void endDocument(){
            System.out.println("Fin del documento XML");
        }
        
        @Override
        public void startElement(String uri, String nombre, String nombreC, Attributes atrbs){
             System.out.println("\t"+nombre);
        }       
        
            
        @Override
        public void characters(char[] ch, int inicio, int longitud){
            String car=new String(ch, inicio, longitud);
            car=car.replaceAll("[\t\n]", ""); //quitar saltos de linea
            System.out.println("\t\t"+ car);
        }
    }
}
