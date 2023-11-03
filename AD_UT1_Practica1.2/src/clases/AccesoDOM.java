
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
    private Document miDocumento;
    
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
    
  
    public void recorreDOMyMuestra(){
        System.out.println(miDocumento.getFirstChild().getNodeName()); // obtengo el nombre de la raiz y lo imprimo
        NodeList nodosHijos = miDocumento.getFirstChild().getChildNodes();
        recorreElementosyMuestra(nodosHijos,0);
    }
    
    public boolean recorreElementosyMuestra(NodeList  nodosHijos, int nivel){
        boolean tieneHijosElementos=false;
        nivel++;
        for (int i = 0; i < nodosHijos.getLength(); i++) {           
            if(nodosHijos.item(i).getNodeType() == Node.ELEMENT_NODE){
                tieneHijosElementos = true;             
                System.out.print(tabs(nivel)+nodosHijos.item(i).getNodeName());              
                if(nodosHijos.item(i).hasAttributes()){
                    recorreAtributosyMuestra(nodosHijos.item(i));
                }
                if(hijosSonElementos(nodosHijos.item(i).getChildNodes())){
                   System.out.println(""); 
                }                   
            }           
            if(nodosHijos.item(i).hasChildNodes()){
                if ( !recorreElementosyMuestra(nodosHijos.item(i).getChildNodes(),nivel) ){
                    System.out.print(" :  "+nodosHijos.item(i).getTextContent()+"\n");              
                }else{
                    System.out.println("");
                }              
            }
                
        }      
        return tieneHijosElementos;
    }
    
    private void recorreAtributosyMuestra(Node nodo){
        NamedNodeMap atributos= nodo.getAttributes();       
        for (int i = 0; i < atributos.getLength(); i++) {
            System.out.print("  "+atributos.item(i).getNodeName()+" = "+
                    atributos.item(i).getNodeValue());
        } 
    }
    
    private boolean hijosSonElementos(NodeList  nodosHijos){
        boolean tieneHijosElementos=false;
        for (int i = 0; i < nodosHijos.getLength(); i++) {           
            if(nodosHijos.item(i).getNodeType() == Node.ELEMENT_NODE)
                tieneHijosElementos = true;                    
        }
        return tieneHijosElementos;
    }
    
    private String tabs(int nivel){
        String tabulacion="";
        for (int i = 0; i < nivel; i++) {
            tabulacion+="\t";
        }
        return tabulacion;
    }
    
    
    public int insertarLibroEnDOM(String titulo, String autor, String fecha){
        int correcto = 0;
        
        try{
            System.out.println("Añadir libro al arbol DOM: "+titulo+";"+autor+";"+fecha);
            //crea los nodos=>los añade al padre desde las hojas a la raíz
                    //CREATE TITULO con el texto en medio
            Node nTitulo = miDocumento.createElement("Titulo"); // crea atiquetas.
            Node nTitulo_text=miDocumento.createTextNode(titulo); // asigna el titulo

            nTitulo.appendChild(nTitulo_text); // añade el texto del titulo al nodo titulo.

            Node nAutor = miDocumento.createElement("Autor"); // crea atiquetas.
            Node nAutor_text=miDocumento.createTextNode(autor); // asigna el autor

            nAutor.appendChild(nAutor_text); // añade el texto del autor al nodo autor.

            Node nLibro = miDocumento.createElement("Libro"); // creo el padre Libro
            ((Element)nLibro).setAttribute("publicado", fecha); // creo el atributo y lo pongo en Libro
            nLibro.appendChild(nTitulo);
            nLibro.appendChild(nAutor);  // Asigno como hijos los nodos titulo y autor

            nLibro.appendChild(miDocumento.createTextNode("\n")); // inserta salto de linea

            Node raiz  = miDocumento.getFirstChild();  // cojo la raiz --> miDocumento.getChildNodes().item(0).
            raiz.appendChild(nLibro);  // añado el Libro a la raiz.
            System.out.println("Libro insertado en el DOM");         
        }catch (Exception ex){
            System.out.println("Error en la creación del nodo Hijo.");
            correcto= -1;       
        }     
        return correcto;
    }
    
    
    public int deleteNode(String titulo){
        int correcto = 0;
        try{
            System.out.println("Buscando el Libro "+titulo+" para borrarlo.");

            NodeList listaNodos = miDocumento.getElementsByTagName("Titulo");
            Node unNodo;

            for (int i = 0; i < listaNodos.getLength(); i++) {
                unNodo = listaNodos.item(i);
                if (unNodo.getNodeType() == Node.ELEMENT_NODE){ //redundante por getElementsByTagName, no lo es si buscamos getChildNodes()
                    if(unNodo.getChildNodes().item(0).getTextContent().equals(titulo)){
                                                        // .getNodeValue()
                        System.out.println("Borramdo el Libro ....");

                        unNodo.getParentNode().getParentNode().removeChild(unNodo.getParentNode());
                        System.out.println("... Nodo borrado.");
                    }                                                  
                }

            }
        }catch(Exception e){
            System.out.println("Error el borrar del DOM el libro con titulo: "+titulo);
            correcto=-1;
        }
        return correcto;
    }
     
}


