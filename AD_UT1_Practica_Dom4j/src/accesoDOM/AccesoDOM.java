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
    
    
}
