package dom.sax;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeerFichData {
  public static void main(String[] args) throws IOException {    
   File fichero = new File("C:\\prueba\\FichData.dat");
   FileInputStream filein = new FileInputStream(fichero);   
   DataInputStream dataIS = new DataInputStream(filein);

   String n;
   int e;

   try {
    while (true) {
        n = dataIS.readUTF(); //recupera el nombre
        e = dataIS.readInt(); //recupera la edad
        System.out.println("Nombre: " + n + ", edad: " + e);        
    }
    }catch (EOFException eo) {}
	
   dataIS.close();  //cerrar stream   
  }
   
}
