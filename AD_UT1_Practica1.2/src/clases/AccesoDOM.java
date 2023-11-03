
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
        recorreElementosyMuestra(nodosHijos);
    }
    
    public boolean recorreElementosyMuestra(NodeList  nodosHijos){
        boolean tieneHijosElementos=false;
        for (int i = 0; i < nodosHijos.getLength(); i++) {           
            if(nodosHijos.item(i).getNodeType() == Node.ELEMENT_NODE){
                tieneHijosElementos = true;             
                System.out.print(nodosHijos.item(i).getNodeName());              
                if(nodosHijos.item(i).hasAttributes()){
                    recorreAtributosyMuestra(nodosHijos.item(i));
                }
                if(hijosSonElementos(nodosHijos.item(i).getChildNodes())){
                   System.out.println(""); 
                }                   
            }           
            if(nodosHijos.item(i).hasChildNodes()){
                if ( !recorreElementosyMuestra(nodosHijos.item(i).getChildNodes()) ){
                    System.out.print(" :  "+nodosHijos.item(i).getTextContent()+"\n");              
                }else{
                    System.out.println("");
                }              
            }
                
        }      
        return tieneHijosElementos;
    }
    
    public void recorreAtributosyMuestra(Node nodo){
        NamedNodeMap atributos= nodo.getAttributes();       
        for (int i = 0; i < atributos.getLength(); i++) {
            System.out.print("  "+atributos.item(i).getNodeName()+" = "+
                    atributos.item(i).getNodeValue());
        } 
    }
    
    public boolean hijosSonElementos(NodeList  nodosHijos){
        boolean tieneHijosElementos=false;
        for (int i = 0; i < nodosHijos.getLength(); i++) {           
            if(nodosHijos.item(i).getNodeType() == Node.ELEMENT_NODE)
                tieneHijosElementos = true;                    
        }
        return tieneHijosElementos;
    }
     
}


