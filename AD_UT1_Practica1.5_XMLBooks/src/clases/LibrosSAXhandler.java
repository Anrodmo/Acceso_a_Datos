
package clases;


import org.xml.sax.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class LibrosSAXhandler extends DefaultHandler{ 
    
    private int contadorLibros;
    public LibrosSAXhandler(){
        this.contadorLibros=0;
    }

    /**
     * Sobreescribimos el método para que nos muestre en pantalla una presetnación 
     * del xml
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        
        System.out.println("============ LIST OF BOOKS ===========");
        System.out.println("======================================");
    }
       
    /**
     * Sobreescribimos el método que se ejecuta cuando el SAX abre un Element 
     * paraque nos imprima por pantalla lo que queremos
     * @param uri
     * @param localName
     * @param qName
     * @param atts
     * @throws SAXException
     */
    @Override
    public void startElement(String uri,String localName,String qName,
            Attributes atts) throws SAXException{

        if(qName.equals("Book")){
            contadorLibros++;
            System.out.print("\nBook nº "+contadorLibros+" --> ");
            System.out.print("Id: "+ atts.getValue(atts.getQName(0))); // extrae el primer atributo              
        }else if(qName.equals("author")){
            System.out.print("\n Author: "); // aun no sabemos el titulo eso lo dice el evento 
                                        // caracteres
        }else if (qName.equals("title")){
            System.out.print("\n Title: ");
        }else if (qName.equals("genre")){
            System.out.print("\n Genre: ");
        }else if (qName.equals("price")){
            System.out.print("\n Price: ");
        }else if (qName.equals("publish_date")){
            System.out.print("\n Publish_date: ");
        }else if (qName.equals("description")){
            System.out.print("\n Description: ");
        }
    }
    
    /**
     * Sobreescribimos el mñetodo que se ejecuta cuando se acaba de recorrer un Element
     * En este caso cuando se salga de un elemento libro imprimios una linea pra separa unos libros de otros.
     * @param uri
     * @param local
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement (String uri, String local, String qName) throws SAXException{
        if(qName.equals("book")){
            System.out.print("\n----------------------------------");
        }
    } 
        
    /**
     * Sobreescribimos el método que recoge los datos ?? de un elemento y lo modificamos
     * para eliminar tabulaciones y saltos de linea.
     * @param ch
     * @param start
     * @param lenght
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int lenght) throws SAXException{
        String car = new String(ch,start,lenght);
        car = car.replaceAll("\t", "");
        car = car.replaceAll("\n", "");
        System.out.print(car);
    }

    /**
     * Se sobreescribe este metodo que no esta en la practica para que el mensaje de final de ejecución
     * salga en una nueva linea.
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        System.out.println("");
        
    }
    
    
        
    
    
    

    
        
    
    
    
}
    
    
    

    

