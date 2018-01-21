package dom.sax;

import java.io.File;

public class VerDir {
public static void main(String[] args) {
  System.out.println("Archivos en el directorio actual:");
  File f = new File("c:\\prueba");
  String[] archivos = f.list();
  for (int i = 0; i < archivos.length; i++) {
    System.out.println(archivos[i]);
  }
 }    
}
