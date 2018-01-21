package dom.sax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribirFichBytes {
public static void main(String[] args) throws IOException {   
   File fichero = new File("C:\\prueba\\FichBytes.dat");//declara fichero
   //crea flujo de salida hacia el fichero  
   FileOutputStream fileout = new FileOutputStream(fichero);   
   //crea flujo de entrada
   FileInputStream filein = new FileInputStream(fichero);   
   int i;

   for (i=1; i<100; i++)
       fileout.write(i); //escribe datos en el flujo de salida 	  
   fileout.close(); //cerrar stream de salida   
   
   //visualizar los datos del fichero	   
   while ((i = filein.read()) != -1) //lee datos del flujo de entrada
	    System.out.println(i);		
   filein.close();  //cerrar stream de entrada 
  }
    
}
