package dom.sax;

import java.io.File;
import java.io.IOException;

public class CrearDir {
 public static void main(String[] args) {
   File d = new File("NUEVODIR"); //directorio que creo a partir del actual
   File f1 = new File(d,"FICHERO1.TXT");
   File f2 = new File(d,"FICHERO2.TXT");
      
   d.mkdir();//CREAR DIRECTORIO
     
   try { 
    if (f1.createNewFile())
       System.out.println("FICHERO1 creado correctamente...");
    else
       System.out.println("No se ha podido crear FICHERO1...");
    if (f2.createNewFile())
       System.out.println("FICHERO2 creado correctamente...");
    else
       System.out.println("No se ha podido crear FICHERO2...");
   } catch (IOException ioe) {}  
   
   f1.renameTo(new File(d,"FICHERO1NUEVO"));//renombro FICHERO1
 
   if(f1.delete()) //borro FICHERO1   
      System.out.println("Fichero 1 borrado...");
   else
	   System.out.println("No se ha podido borrar el Fichero 1...");
 
   if(f2.delete())
      System.out.println("Fichero 2 borrado...");
   else
	   System.out.println("No se ha podido borrar el Fichero 2...");
   
   if(d.delete()) //borrar DIRECTORIO
      System.out.println("Directorio NUEVODIR borrado...");
   else
	  System.out.println("No se ha podido borrar el directorio NUEVODIR...");
  
  try { 
     File f3 = new File("NUEVODIR/FICHERO3.TXT");
     f3.createNewFile();
   } catch (IOException ioe) {}  
 }   
}
