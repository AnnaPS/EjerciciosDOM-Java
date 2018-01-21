package dom.sax;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeerFichTexto {
    private static int i;
   public static void main(String[] args) throws IOException {
    File fichero = new File("C:\\Users\\Antonio\\Desktop\\ProyAD\\DOM-SAX\\src\\dom\\sax\\LeerFichTexto.java");
                //declarar fichero
    FileReader fic = new FileReader(fichero); //crear el flujo de entrada   
    char b[]=new char[150];
    while ((i = fic.read(b)) != -1) //se va leyendo un carácter
      System.out.println(b);
    fic.close(); //cerrar fichero   
  } 
}
