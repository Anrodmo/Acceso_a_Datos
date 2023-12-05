package ejercicio1v2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import notasAlumno.NotasAlumno;

/**
 *
 * @author anrod
 */
public class AD_UT1_Ejercicio1v2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta = "./src/ejercicio1v2/alumnosNotas.txt";
        String rutaBackup = "./src/ejercicio1v2/alumnosNotasBackup.txt";
        String rutaMedia = "./src/ejercicio1v2/alumnosNotasMedias.txt";
        ArrayList <NotasAlumno> listado=null;
        
        if( (listado = NotasAlumno.leerArchivoNotas(ruta)) != null ){
            for (NotasAlumno notasAlumno : listado) {
                System.out.println(notasAlumno.muestraNotaMedia());               
            }
            
            System.out.println("Escribiendo notas en archivo backup ....");
            NotasAlumno.escribirNotasEnDisco(listado, rutaBackup);
            System.out.println("... escritura completada.");
            
            System.out.println("Escribiendo notas medias en archivo ....");
            NotasAlumno.escribirNotaMediaenDisco(listado, rutaMedia);
            System.out.println("... escritura completada.");
            
        }
            
            
            
        
        
        
        
        
        
            
       
        
    }
    
}
