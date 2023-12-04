
package clases;


import org.xml.sax.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase handler para imprimir ppor pantalla todos los elementos, atributos y sus
 * valores del xml de libros.
 * @author anrod
 */
public class LibroSAXhandler extends DefaultHandler{ 
    
    private int contadorLibros;
    public LibroSAXhandler(){
        this.contadorLibros=0;
    }

    /**
     * Sobreescribimos el método para que nos muestre en pantalla una presetnación 
     * del xml
     * @throws SAXException Error de lectura del XML
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        System.out.println("========= LISTADO DE LIBROS ==========");
        System.out.println("======================================");
    }
       
    /**
     * Sobreescribimos el método que se ejecuta cuando el SAX abre un Element 
     * paraque nos imprima por pantalla lo que queremos
     * @param uri
     * @param localName
     * @param qName
     * @param atts
     * @throws SAXException Error de lectura del XML
     */
    @Override
    public void startElement(String uri,String localName,String qName,
            Attributes atts) throws SAXException{

        if(qName.equals("Libro")){
            contadorLibros++;  // solo para mostrar por que libro vamos.
            System.out.print("\nLibro nº "+contadorLibros+" --> ");
            System.out.print("Publicado en: "+ atts.getValue(atts.getQName(0))); // extrae el primer atributo              
        }else if(qName.equals("Titulo")){
            System.out.print("\n El título es: "); // aun no sabemos el titulo eso lo dice el evento 
                                        // caracteres
        }else if (qName.equals("Autor")){
            System.out.print("\n El Autor es: ");
        }            
    }
    
    /**
     * Sobreescribimos el mñetodo que se ejecuta cuando se acaba de recorrer un Element
     * En este caso cuando se salga de un elemento libro imprimios una linea pra separa unos libros de otros.
     * @param uri
     * @param local
     * @param qName
     * @throws SAXException Error de lectura del XML
     */
    @Override
    public void endElement (String uri, String local, String qName) throws SAXException{
        if(qName.equals("Libro")){
            System.out.print("\n------------------------------------");
        }
    } 
        
    /**
     * Sobreescribimos el método que recoge los datos ?? de un elemento y lo modificamos
     * para eliminar tabulaciones y saltos de linea.
     * @param ch
     * @param start
     * @param lenght
     * @throws SAXException Error de lectura del XML
     */
    @Override
    public void characters(char[] ch, int start, int lenght) throws SAXException{
        String car = new String(ch,start,lenght);
        car = car.replaceAll("\t", ""); // quitamos tabulaciones
        car = car.replaceAll("\n", ""); // quitamos saltos de linea
        System.out.print(car); // lo muestro por pantalla
    }

    /**
     * Se sobreescribe este metodo que no esta en la practica para que el mensaje de final de ejecución
     * salga en una nueva linea.
     * @throws SAXException Error de lectura del XML
     */
    @Override
    public void endDocument() throws SAXException {
        System.out.println("");
        super.endDocument(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
        
    
    
    

    
        
    
    
    
}
    
    
    

    

