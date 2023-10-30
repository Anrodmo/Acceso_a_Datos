/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica1_1;

import clases.AccesoDOM;
import java.io.File;

/**
 *
 * @author anrod
 */
public class Practica1_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta ="";
        AccesoDOM acceso = new AccesoDOM();
        File archivo = new File(ruta);
        
        acceso.abrirXMLaDom(archivo);
    }
    
}
