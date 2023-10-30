
package clases;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;//for Document
import org.w3c.dom.Document;
import java.util.*;
import java.io.*;//clase File
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/**
 *
 * @author anrod
 */
public class AccesoDOM {
    Document miDocumento;
    
    public int abrirXMLaDom (File archivo){
       int retorno=-1;
        try {
            System.out.println("Abriendo el archivo XTMl y generando el DOM ....");
            
            // se crea un DocumentBuilderFactory
            DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
            // se configura para ignore comentarios y espacios en blanco
            fabricaDeDocumentos.setIgnoringComments(true);
            fabricaDeDocumentos.setIgnoringElementContentWhitespace(true);
            
            // Creamos un constructor de documentos con la fábrica.
            
            DocumentBuilder constructorDeDocumentos = fabricaDeDocumentos.newDocumentBuilder();
            miDocumento = constructorDeDocumentos.parse(archivo);
            retorno=1;              
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex); 
        }       
        return retorno;
    }
    
    
}
