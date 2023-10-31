
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
    
    
    public void recorreDOMyMuestra(){
        String datos[] = new String[3];
        Node nodo = null;
        Node root = miDocumento.getFirstChild(); // coneguimos la raiz
        NodeList listaNodos = root.getChildNodes();  // con seguimos el arbol de cada hijo.
        
        System.out.println(root.getNodeName());
        
        //  ahora recorremos el árbol DOM. Cada vielta del bucle es un hijo
        for (int i = 0; i < listaNodos.getLength(); i++) {
            nodo = listaNodos.item(i); // aqui metemos la estructura de un hijo
           // Comprobamos que el nodo en el que estamos es un nodo raiz del bloque actual  
            if(nodo.getNodeType() == Node.ELEMENT_NODE){
                Node nodoTemporal = null;
                int contador = 1;
             // sacamos el valor del atributo de libro y lo guardamos en la primera posicion
             // del array. Sacamos el del primer atributo, podria haber mas.
                datos[0]= nodo.getAttributes().item(0).getNodeValue();
                               
              // imprimo la parte del libro y su atributo , en este cado doy por hecho que solo
              // hay uno.
               System.out.println(nodo.getNodeName()+"\n\t"+
               nodo.getAttributes().item(0).getNodeName()+" : "+datos[0]);
                
                
                NodeList listaSubnodos = nodo.getChildNodes(); // obteneoms los hijos de nodo
                for (int j = 0; j < listaSubnodos.getLength(); j++) {
                    nodoTemporal =listaSubnodos.item(j); // obtengo el hijo del hijo
                    
                    if(nodoTemporal.getNodeType() == Node.ELEMENT_NODE){
             // para conseguir el valor podemos usar getNodeValue()
                        datos[contador]= nodoTemporal.getTextContent();
                        contador++;
                        
             // imprimo los nombres de los elementos sean los que sean y se llamen como se llamen
                        System.out.println("\t"+nodoTemporal.getNodeName()+" : "+
                            nodoTemporal.getTextContent());
                    }
                    
                }
            // aqui el array datos[] tiene los datos que necesitamos para imprimir una linea
//                System.out.println(nodo.getNodeName()+"\n\t"+
//                        nodo.getAttributes().item(0).getNodeName()+" : "+datos[0]);
            }
            
            
                
            
            
            
            
        }
        
        
        
        
    }

    
    

}
    
// ====================== codigo de pruebas y cacharreo ============================================================
   
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
    

