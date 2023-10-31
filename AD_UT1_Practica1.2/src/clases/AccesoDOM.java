
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
    public Document miDocumento;
    
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
   
//    Element elemento = acceso.miDocumento.getDocumentElement();
//        System.out.println(elemento.toString());
//                
//        NodeList nList = acceso.miDocumento.getElementsByTagName("Libro");
//        System.out.println("Número de Libros: " + nList.getLength());
//        
//        for (int i = 0; i < nList.getLength(); i++) {
//            Node node = nList.item(i);
//
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) node;
////                if (eElement.hasAttributes()){
////                    NamedNodeMap listaAtributos = eElement.getAttributes();
////                    for (int j = 0; j < listaAtributos.getLength(); j++) {
////                        Node atributo = listaAtributos.item(i);
////                        System.out.print(atributo.getNodeName()+":"+atributo.getNodeValue());
////                    }
////                }
//                        
//                if(eElement.hasChildNodes()) {
//                    NodeList nl = node.getChildNodes();
//                    for(int j=0; j<nl.getLength(); j++) {
//                      Node nd = nl.item(j);
//                      System.out.print(nd.getTextContent());
//                    }
//                }
//            }
//        } 
//        
//        for(int temp = 0; temp < nList.getLength(); temp++) {
//            Node nNode = nList.item(temp);
//
//            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
//              Element eElement2 = (Element) nNode;
//
//              System.out.println("\nLibro publicado en: " + eElement2.getAttribute("publicado"));
//              System.out.println("Titulo: "
//                          + eElement2.getElementsByTagName("Titulo").item(0).getTextContent());
//              System.out.println("Autor: "
//                          + eElement2.getElementsByTagName("Autor").item(0).getTextContent());
//            }
//        }
    
}
