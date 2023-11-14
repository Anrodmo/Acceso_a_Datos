
package ad_ut1_maven_jakarta;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class JakartaLeer {

    
    public static void main(String[] args) {
        Catalog catalog=null; 

        
            // primera parte que lee el xml y muestra titulo y autor           
            catalog=leerXMLYCrearObjetos(catalog);

            
            System.out.println(catalog.getCatalog().size());
            for(Book book : catalog.getCatalog()){
                System.out.print(book.getTitle()+",  ");
                System.out.print(book.getId()+",  "); 
                System.out.print(book.getAuthor()+",  ");
                System.out.print(book.getGenre()+",  ");
                System.out.print(book.getPrice()+",  ");
                System.out.print(book.getPublish_date()+",  ");
                System.out.print(book.getDescription()+"\n");                 
            }
            
            // segunda parte en la que añado un libro y luego escribo el XMl en otro
            // archivo
            añadirLibroDePrueba(catalog);           
            mostrarEnPantallaYGuardarEnFichero(catalog);            
//        }catch (JAXBException e) {            
//            e.printStackTrace();
//        }
        
             
    }
    
    static private Catalog leerXMLYCrearObjetos(Catalog catalog){
        try {
            JAXBContext jabxContext = JAXBContext.newInstance( Catalog.class );
            Unmarshaller jabxUnmarshaller = jabxContext.createUnmarshaller();
            catalog = (Catalog)jabxUnmarshaller.unmarshal(
                    new File("./src/main/java/ad_ut1_maven_jakarta/Books.xml") );
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        
        return catalog;
    }
    
    
    static private void añadirLibroDePrueba(Catalog catalogo){
        Book libro = new Book();
        libro.setAuthor("Frank Herbert");
        libro.setTitle("Dune");
        libro.setGenre("SciFi");
        libro.setDescription("Sand, Sand everywhere ...");
        libro.setPrice("12");
        libro.setId("BK113");
        libro.setPublish_date("1969-10-10");       
        catalogo.getCatalog().add(libro);       
    }
    
    static private void mostrarEnPantallaYGuardarEnFichero(Catalog catalog){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            System.out.println("________________________________________________________________________________________\n\n");
            
            // Con esto lo mando a pantalla
            jaxbMarshaller.marshal(catalog, System.out);
            // Con esto lo mando a un fichero
            jaxbMarshaller.marshal(catalog, new File("./src/main/java/ad_ut1_maven_jakarta/Books2.xml"));
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }      
    }
    
    
}
