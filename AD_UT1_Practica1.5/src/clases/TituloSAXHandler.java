
package clases;

import org.xml.sax.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *  Clase handler para el xml libros que imprime solo el titulo y el autor
 * @author anrod
 */
public class TituloSAXHandler extends DefaultHandler{
    private String etiqueta; // variable donde guardare el elemento del xml
    
    public TituloSAXHandler(){
        this.etiqueta=""; // seteo a cadena vacia
    }
    
   /**
     * Sobreescribimos el método para que nos muestre en pantalla una presetnación 
     * del xml
     */
    @Override
    public void startDocument() {
        System.out.println("LISTADO DE TITULOS");
        System.out.println("==================");
    }
    
    
    /**
     * Sobreescribimos el método que se ejecuta cuando el SAX abre un Element 
     * paraque nos imprima por pantalla lo que queremos, en este caso lee el nombre
     * del elemento 
     * @param uri
     * @param localName
     * @param qName
     * @param atts
     * @throws SAXException Error de lectura del XML
     */
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
        if(etiqueta.equals("Titulo")){  // si es titulo hacemos algo
            String car = new String(ch,start,lenght);
            car = car.replaceAll("\t", "");  // quito tabulaciones
            car = car.replaceAll("\n", "");  // quito salto de linea
            car=car.trim();  // quito espacios en blanco delante y detras
            System.out.print(" | "+car+" ");     // imprimoo con separador para que quede bonito       
        }else if(etiqueta.equals("Autor")){  // si es autor tambien hacemos cosas
            String car = new String(ch,start,lenght);
            car = car.replaceAll("\t", "");   // quito tabulaciones
            car = car.replaceAll("\n", "");   // quito salto de linea
            car=car.trim();  // quito espacios en blanco delante y detras
            System.out.print(car);      // imprimo.      
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
