
package ad_ut1_ejercicio1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;


/**
 *
 * @author anrod
 */
public class AD_UT1_Ejercicio1 {

  
    public static void main(String[] args) {
        String ruta = "./src/ad_ut1_ejercicio1/alumnosttttNotas.txt";
        String archivo="";
        
        try{
            archivo = leerArchivo(ruta);
        }catch (FileNotFoundException ex){
            System.out.println("Ruta del archivo incorrecta\n"+ex.getStackTrace().toString());
        } catch (IOException ex) {
            System.out.println("Error de lectura / escritura:\n"+ex.getStackTrace().toString());
        }catch (NullPointerException  ex){
            System.out.println("Se ha proporcionado una ruta de acceso nula");
        }finally{           
                imprimirNotaMedia(archivo);           
        }         
    }
    
    /**
     * Lee un archivo de texto  y  hace una copia del mismo en un String
     * @param String ruta del archivo CSV
     * @return String con unba imagen exacta del fichero de texto
     */
    private static String leerArchivo(String ruta) throws FileNotFoundException, IOException, NullPointerException{
        String archivo=""; // Inicializo donde se van a guardar los datos.
        if(ruta == null)   // Lanzo excepción si la ruta introducida es null.
            throw new NullPointerException ("Ruta de archivo nula");
        else{
                // Try  con recursos para que cierre solo el IS
            try(FileInputStream in = new FileInputStream(ruta)){
                int caracter;     // Leo el archivo caracter a caracter ascii
                while ( (caracter= in.read()) != -1){
                    archivo+=(char)caracter;  // Y lo guardo en  el el string                  
                }                          
                       
            }
             
        }
        return archivo; // Devuelvo una imagen del  archivo en una variable Strin  
    }
    
    
    /**
     * Muestra el nombre del alumno y su nota media. El string origen tiene que tener el formato
     * nombre_alumno:nota:nota:nota:... donde nota deben ser enteros.
     * @param String ruta del archivo CSV
     */
    private static void imprimirNotaMedia(String datos){
        
        Scanner entradaDatos = new Scanner (datos);
        String linea[];
        int suma;
        double media;     
        while ( entradaDatos.hasNext() ){
            linea = entradaDatos.nextLine().split(":");
            suma=0;                                
            for (int i = 1; i < linea.length; i++) {
                suma+= Integer.parseInt(linea[i])  ;               
            }           
            media = (double)suma/(linea.length-1);
            System.out.print("Alumno: "+linea[0]);
            System.out.print(" | Nota media: "+media+"\n");           
        }      
    }
    
}
