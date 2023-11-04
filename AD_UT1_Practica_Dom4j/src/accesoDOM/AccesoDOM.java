/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoDOM;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;


/**
 *
 * @author anrod
 */
public class AccesoDOM {
    Document miDocumento;
    
    public void Parse(File archivo){              
        SAXReader reader = new SAXReader();
        reader.setStripWhitespaceText(true);
        try {   // para que lo parsee  he tenido que  quitar xsi:noNamespaceSchemaLocation="Libros.xsd" 
                //   porque intenta comprobar con dtd y al no encontrarlo crashea
            miDocumento = reader.read(archivo);
        } catch (DocumentException ex) {
            Logger.getLogger(AccesoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void treeWalk() {
        System.out.println(miDocumento.getRootElement().getName());
        treeWalk( miDocumento.getRootElement());       
    }
    
    public void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);

            if (node instanceof Element) {
                System.out.println("\t"+node.getName().trim()+" : "+node.getText().trim());
                if(((Element) node).attributeCount()>0){
                    List<Attribute> attributes = ((Element) node).attributes();
                    for (Attribute attribute : attributes) {
                        System.out.println("\t\t"+attribute.getName().trim() + " : " + attribute.getValue().trim());                   
                    }
                }
                treeWalk((Element) node);
                
            }             
        }
    }
    
    public void insertarLibroEnDOM4J(String titulo, String autor, String fecha){
       
        // en DOM4j es al revés que en DOM, voy añadiendo directamente  al documento.
        // extraigo el elemento raiz (Libros)
        Element raiz = miDocumento.getRootElement();
        // Creo Libro añadiendolo a la raiz.        
        Element nLibro = raiz.addElement("Libro");
        // y la añado a Libro su atributo.
        nLibro.addAttribute("publicacion",fecha);

        // Creo titulo y  lo añado a Libro, luego le pongo su valor
        Element nTitulo = nLibro.addElement("Titulo");
        nTitulo.setText(titulo);
        // Creo autor y  lo añado a Libro, luego le pongo su valor
        Element nAutor = nLibro.addElement("Autor");
        nAutor.setText(autor);       
    }
    
    public void borrarLibroPorTituloEnDOM4J(String titulo){
        // Consigo el elemento raiz
        Element raiz = miDocumento.getRootElement();
        
        
        // Obtengo los elmentos Libro de la raiz y los recorro
        for (Element elementoN1 : raiz.elements("Libro")) {
            // Obtengo los elementos Titulo de cada elemento Libro
            for (Element elementoN2 : elementoN1.elements("Titulo")){
                //  si el valor del titulo coincide con el facilitado ...
                if (elementoN2.getText().equals(titulo)){
                    // Obtengo el padre del Libro en el que estoy y le quito el Libro.
                    elementoN1.getParent().remove(elementoN1);
                    // al quitar el Libro automaticammente  se eliminan todos los hijos y atributos

                }             
            }               
        }       
    }
    
    
    
    
}
