
package clases;

import org.xml.sax.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author anrod
 */
public class TituloSAXHandler extends DefaultHandler{
    private String etiqueta;
    
    public TituloSAXHandler(){
        this.etiqueta="";
    }
    
    @Override
    public void startDocument() {
        System.out.println("LISTADO DE TITULOS");
        System.out.println("==================");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("Libro")){
            etiqueta ="Libro";
        }else if (qName.equals("Titulo")){
            etiqueta ="Titulo";
        }else if (qName.equals("Autor")){
            etiqueta ="Autor";
        }
    }
    
    
    
    /**
     * Sobreescribimos el método que recoge los datos ?? de un elemento y lo modificamos
     * para eliminar tabulaciones y saltos de linea, imprimimos solo si es un ttulo
     * Añadido --> imprimimos también los autores 
     * @param ch
     * @param start
     * @param lenght
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int lenght) throws SAXException{
        if(etiqueta.equals("Titulo")){
            String car = new String(ch,start,lenght);
            car = car.replaceAll("\t", "");
            car = car.replaceAll("\n", "");
            car=car.trim();
            System.out.print(" | "+car+" ");           
        }else if(etiqueta.equals("Autor")){
            String car = new String(ch,start,lenght);
            car = car.replaceAll("\t", "");
            car = car.replaceAll("\n", "");
            car=car.trim();
            System.out.print(car);           
        }
        
    }
    
    /**
     * Sobreescribimos el mñetodo que se ejecuta cuando se acaba de recorrer un Element
     * En este caso cuando se salga de un elemento titulo imprimios un salto de linea.
     * @param uri
     * @param local
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement (String uri, String local, String qName) throws SAXException{
        if(qName.equals("Libro")){
            System.out.print("\n");
        }
    }
    
    
    
    
    
    
}
