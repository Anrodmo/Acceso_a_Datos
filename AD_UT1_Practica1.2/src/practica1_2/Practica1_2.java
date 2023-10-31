
package practica1_2;

import clases.AccesoDOM;
import java.io.File;

/**
 *
 * @author anrod
 */
public class Practica1_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta ="./src/practica1_2/libros.xml";       
        AccesoDOM acceso = new AccesoDOM();
        
        if(ruta==null)ruta=""; // Evito la PointNullException al crear File
        File archivo = new File(ruta);
        
        if(acceso.abrirXMLaDom(archivo) == 1)
            System.out.println("... DOM generado con éxito.");
        else
            System.out.println("...Error al creaar el DOM");
        
        acceso.recorreDOMyMuestra();
        
        
    }
    
}
