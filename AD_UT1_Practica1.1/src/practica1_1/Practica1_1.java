
package practica1_1;

import clases.AccesoDOM;
import java.io.File;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author anrod
 */
public class Practica1_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta ="./src/practica1_1/libros.xml";       
        AccesoDOM acceso = new AccesoDOM();
        
        if(ruta==null)ruta=""; // Evito la PointNullException al crear File
        File archivo = new File(ruta);
        
        if(acceso.abrirXMLaDom(archivo) == 1)
            System.out.println("... DOM generado con éxito.");
        else
            System.out.println("...Error al creaar el dOM");
        
        
     
    }
}
                
       
        
    
    

