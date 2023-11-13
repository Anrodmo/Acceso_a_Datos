
package clases;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * Con esta clase parsearemos el documento XML utilizando el hander que hemos 
 creado en BooksSAXhandler
 * @author anrod
 */
public class AccesoXMLSAX {
    SAXParser parser;
    
    /**
     * Método que recorre el XML con SAX y utiliza el handler que meustra todos 
     * los elementos de Libro y sus valores
     * @param f Archivo XML que se quiere parsear
     * @return 0 -> todo correcto, -1 -> error al parsear.
     */
    public int parsearXMLconLibrosSAXhandler (File f){
        int retorno=0;
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            BooksSAXhandler sh = new BooksSAXhandler();
            parser.parse(f, sh);
            
        }catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
            retorno = -1 ;
        }       
        return retorno;
    }
    
    
    
    /**
     * Método que recorre el XML con SAX y utiliza el handler que muestra solo 
     * el valor de los titulos de los libros
     * @param f Archivo XML que se quiere parsear
     * @return 0 -> todo correcto, -1 -> error al parsear.
     */
    public int parsearXMLconTitulosSAXHandler(File f){
        int retorno=0;
        try{
            SAXParserFactory factory= SAXParserFactory.newInstance();
            parser=factory.newSAXParser();
            TitleSAXHandler sh = new TitleSAXHandler();
            parser.parse(f, sh);
            
        }catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
            retorno = -1;
        }       
        return retorno;
    }
    
}
