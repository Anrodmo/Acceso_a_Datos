/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_dom4j;



import accesoDOM.AccesoDOM;
import java.io.File;

/**
 *
 * @author anrod
 */
public class Practica_Dom4j {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta ="./src/practica_dom4j/libros.xml";       
        AccesoDOM acceso = new AccesoDOM();
        
        if(ruta==null)ruta=""; // Evito la PointNullException al crear File
        File archivo = new File(ruta);
        
        System.out.println("\n=========== Practica 1.1  y 1.2 - leo y muestro ===============\n");
        acceso.Parse(archivo);
        
        acceso.treeWalk();
        
        System.out.println("\n=========== Practica 1.3 - añado y muestro ====================\n");
        
        acceso.insertarLibroEnDOM4J("Libro añadido", "Angel", "2006");
        acceso.treeWalk();
        
        
        System.out.println("\n=========== Practica 1.3 - borro y muestro ====================\n");
        
        acceso.borrarLibroPorTituloEnDOM4J("Libro añadido");
        acceso.treeWalk();
    }
    
}
