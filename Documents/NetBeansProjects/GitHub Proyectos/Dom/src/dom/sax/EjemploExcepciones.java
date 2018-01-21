
package dom.sax;
public class EjemploExcepciones {
public static void main(String[] args) {   
    String cad1="20", cad2="0", mensaje;
	int nume, denom, cociente;
	int[] arraynum = new int[4];	
	
    try {
          //codigo que puede producir errores
		  arraynum[10]=20;
          nume=Integer.parseInt(cad1);
          denom=Integer.parseInt(cad2);
          cociente=nume/denom;
          mensaje=String.valueOf(cociente);		
    } catch(NumberFormatException ex){
            mensaje="caracteres no numéricos";
    } catch(ArithmeticException ex){
            mensaje="Division por cero";
    } catch (ArrayIndexOutOfBoundsException ex) {
		    mensaje="Fuera de rango en el array";
	} finally {
		 System.out.println("SE EJECUTA SIEMPRE"); 
	}
    System.out.println(mensaje); 
	
 }//    
}
