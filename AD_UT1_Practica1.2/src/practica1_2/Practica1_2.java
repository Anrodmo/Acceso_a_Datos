
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
        System.out.println("================== Práctica 1.1 ========================");
        if(ruta==null)ruta=""; // Evito la PointNullException al crear File
        File archivo = new File(ruta);
        
        if(acceso.abrirXMLaDom(archivo) == 1)
            System.out.println("... DOM generado con éxito.\n");
        else
            System.out.println("...Error al creaar el DOM\n");
        System.out.println("\n================== Práctica 1.2 ========================");     
        acceso.recorreDOMyMuestra();
        System.out.println("\n================== Práctica 1.3 Añadir=================="); 
        acceso.insertarLibroEnDOM("Vaca","Perro", "enero 2000 ");       
        System.out.println("Mostrando DOM de nuevo para comprobar que se ha insertado con éxito:");
        acceso.recorreDOMyMuestra();
        
        System.out.println("\n================== Práctica 1.3 Eliminar=================");
        acceso.deleteNode("Vaca");
        System.out.println("Mostrando DOM de nuevo para comprobar que se ha borrado con éxito:");
        acceso.recorreDOMyMuestra();
        
                
    }
    
}