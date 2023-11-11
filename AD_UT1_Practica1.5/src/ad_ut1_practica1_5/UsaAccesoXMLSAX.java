
package ad_ut1_practica1_5;

import clases.AccesoXMLSAX;
import java.io.File;

/**
 *
 * @author anrod
 */
public class UsaAccesoXMLSAX {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f=new File("./src/ad_ut1_practica1_5/libros.xml");
        
        
        // La primera aprte de la práctica
        AccesoXMLSAX a  = new AccesoXMLSAX();
        a.parsearXMLconLibrosSAXhandler(f);
        
        // La  segunda parte de la  prática
        a.parsearXMLconTitulosSAXHandler(f);
        
        
        
    }
    
}
