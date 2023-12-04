
package clases;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;//for Document
import org.w3c.dom.Document;
import java.io.*;//clase File
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;


/**
 * Clase que crea un Document de DOm y tiene los métodos necesarios para su gestión
 * @author anrod
 */
public class AccesoDOM {
    private Document miDocumento;
    
    /**
     * Mátodo que obtiene un objeto Document y lo asigna al atributo de la clase
     * @param archivo Objeto FIle represetnativo del archivo XML a parsear
     * @return 1 todo correcto; -1 error al crear el DOM
     */
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
            retorno=0;              
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex); 
        }       
        return retorno;
    }
    
    /**
     * Método que recorre y muestra en pantalla el atrbuto de la clase Document, 
     * previamente debe haber sido generado con el método abrirXMLaDom. 
     */
    public void recorreDOMyMuestra(){
        System.out.println(miDocumento.getFirstChild().getNodeName()); // obtengo el nombre de la raiz y lo imprimo
            // obtengo una lista con todos los nodos hijos de la raiz (En este caso los "Libro")
        NodeList nodosHijos = miDocumento.getFirstChild().getChildNodes();
            // Llamoal mñetodo recursivo con la lista y el numero de nivel en elque estoy para las tabulaciones
        recorreElementosyMuestra(nodosHijos,0);
    }
    
    /**
     * Metodo recursivo que recorre un nodelist y muestra por pantalla el nivel actual y se llama a si mismo 
     * para mostrar el nivel inferior.
     * @param nodosHijos NodeList
     * @param nivel entero que indica el nivel del elemento para gestionar las tabulaciones al mostrar
     * @return true -> si en el nivel actual hay nodos hijos que sean elementos; false en caso contrario.
     */
    private boolean recorreElementosyMuestra(NodeList  nodosHijos, int nivel){
            // Boolean que me va a ayudar a saber si en esta iteración de mostrar un nodo hay elementos
        boolean tieneHijosElementos=false;
            // en cada iteracion auemtno el nivel para las tabulaciones.
        nivel++;
            // recorro todos los nodos de la lsista de nodos.
        for (int i = 0; i < nodosHijos.getLength(); i++) {  
            // para cada nodo si es un elemento hago cosas
            if(nodosHijos.item(i).getNodeType() == Node.ELEMENT_NODE){
                // aqui guardo si sus hijos tienen elemetnos, esto es solo para saber si tengo que mostrar el valor (nota1)
                tieneHijosElementos = true;
                //Muestro el nombre del elemento tabulado.
                System.out.print(tabs(nivel)+nodosHijos.item(i).getNodeName()); 
                // Compruebo si tiene atributos, y si los tiene llamo al mñetodo que  los recorre y muestra.
                if(nodosHijos.item(i).hasAttributes()){
                    recorreAtributosyMuestra(nodosHijos.item(i));
                }
                // Obtengo una lista  con los nodos hijos del nodo actual del bucle.
                // Llamo al metodo que me indica si sus hijos son elementos, si son elementos salto la linea pues en el que estoy no tiene valor
                if(hijosSonElementos(nodosHijos.item(i).getChildNodes())){
                   System.out.println(""); 
                }                   
            }
            
           // commpruebo si el nodo en el que estoy del bucle for tiene hijos.
            if(nodosHijos.item(i).hasChildNodes()){
                // si los tiene (siempre tienen algo) los recorro con recursividad y muestro y me informo de si son Element
                if ( !recorreElementosyMuestra(nodosHijos.item(i).getChildNodes(),nivel) ){
                    // si sus hijos no son elementos entonces imprimo su valor y salto linea.
                    System.out.print(" :  "+nodosHijos.item(i).getTextContent()+"\n");              
                }else{
                    // si sus hijos son elementos se habrán mostrado en la recursividad y aqui solo necesito saltar linea para mantener el formato.
                    System.out.println("");
                }              
            }
                
        }      
        return tieneHijosElementos;
    }
    // Nota 1 -->  eso es porque si muestro el  valor o textcontent de un nodo, si itene hijos me muestra el suyo (que no tiene al tener hijos)
    // pero tambien el de  todos sus hijos <-- esto con dom4j no pasa
    
    /**
     * Mñetodo que revcorre los atributos de un nodo y losmuiestra en pantalla en la misma línea.
     * @param nodo nodo del que se quieren  mostrar los atributos.
     */
    private void recorreAtributosyMuestra(Node nodo){
        // los nodos se tienen que guardar en este tipo de lista
        NamedNodeMap atributos= nodo.getAttributes();
        
        // recorro la lista
        for (int i = 0; i < atributos.getLength(); i++) {
                                  // del item de la lista consigo el nombre
            System.out.print("  "+atributos.item(i).getNodeName()+" = "+
                                    // consigo el valor
                    atributos.item(i).getNodeValue());
        } 
    }
    
    /**
     * metodo que recorre una lista de nodos para saber si alguno es elemmento o no.
     * @param nodosHijos NodeList
     * @return true si alguno de los nodos hijos es un Element, false en caso contrario.
     */
    private boolean hijosSonElementos(NodeList  nodosHijos){
        boolean tieneHijosElementos=false; // almaceno si tiene hijos elementos
        for (int i = 0; i < nodosHijos.getLength() && tieneHijosElementos; i++) {    // recorro todos los hijos      
            if(nodosHijos.item(i).getNodeType() == Node.ELEMENT_NODE)
                tieneHijosElementos = true;
            //si alguno es elemento devuelvo true y finalizo bucle
        }
        return tieneHijosElementos;
    }
    
    /**
     * Método que devuelve un String con tabulaciones en función del entero facilitado. 
     * @param nivel int -> contaidad de tabulaciones que se quiere devolver
     * @return String que solo contiene tabulaciones.
     */
    private String tabs(int nivel){
        String tabulacion="";
        for (int i = 0; i < nivel; i++) {
            tabulacion+="\t";
        }
        return tabulacion;
    }
    
    /**
     * Método que inserta un Libro en el Document de la clase. Funcionará en un esquema XML:
     * <Libro publicado=valor>
     *      <Titulo>valor</Titulo>
     *      <Autor>valor</Autor>
     * </Libro>
     * @param titulo valor de <Titulo>
     * @param autor valor de <Autor>
     * @param fecha valor de publicado
     * @return 0 operación correcta, -1 error.
     */
    public int insertarLibroEnDOM(String titulo, String autor, String fecha){
        int correcto = 0;
        
        try{
            System.out.println("Añadir libro al arbol DOM: "+titulo+";"+autor+";"+fecha+" ...");
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
            System.out.println("... Libro insertado en el DOM");         
        }catch (DOMException ex){
            System.out.println("... Error en la creación del nodo Hijo.");
            correcto= -1;       
        }     
        return correcto;
    }
    
    /**
     * Método que borra un Element Libro y todos sus hijos a partir de un string que es el valor de 
     * titulo, funcionara en un XML que en el tercer nivel (contando la raiz) tenga un elemento <Titulo>
     * Si su valor coincide con el sTring facilitado borrara su nodo padre (y todo lo que contiene).
     * Efectivamente borra un Libro a partir de su título en un XML con esquema: 
     * <Libros> 
     *      <Libro publicado=valor>
     *          <Titulo>valor</Titulo>
     *          <Autor>valor</Autor>
     *      </Libro>
     *      ......
     * </Libros>
     * @param titulo el valor de <Titulo> a buscar.
     * @return 0 si no se produjern errores, -1 si sucedió un error.
     */
    public int deleteNode(String titulo){
        int correcto = 0;
        try{
            System.out.println("Buscando el Libro "+titulo+" para borrarlo.");
            // me guardo todos lo elementos del DOM que se llamen Titulo
            NodeList listaNodos = miDocumento.getElementsByTagName("Titulo");
            Node unNodo;

            for (int i = 0; i < listaNodos.getLength(); i++) { // recorro la lista
                unNodo = listaNodos.item(i); // guardo cada elemento en cada iteracion
                if (unNodo.getNodeType() == Node.ELEMENT_NODE){ //redundante por getElementsByTagName, no lo es si buscamos getChildNodes()
                    if(unNodo.getChildNodes().item(0).getTextContent().equals(titulo)){
                        // el primer hijo de un elmento es su valor, si coincide con el que buscamos
                                                        // .getNodeValue()
                        System.out.println("Borramdo el Libro ....");
// buscamos la raiz (padre de ttulo-> libro, su padre -> libros) y boorramos el padre(libro) de este tiitulo
                        unNodo.getParentNode().getParentNode().removeChild(unNodo.getParentNode());
                        System.out.println("... Nodo borrado.");
                    }                                                  
                }
            }
        }catch(DOMException e){
            System.out.println("Error el borrar del DOM el libro con titulo: "+titulo);
            correcto=-1;
        }
        return correcto;
    }
    
    public void guardarDOMcomoArchivo(String ruta){
        try{
            Source src = new DOMSource(miDocumento); // definimos el origen
            StreamResult srt = new StreamResult(new File(ruta)); // definimos el resultado
            Transformer tranformador = TransformerFactory.newInstance().newTransformer();
            
            // Opción para indentar el archivo
            tranformador.setOutputProperty(OutputKeys.INDENT, "yes");
            
            tranformador.transform(src, (javax.xml.transform.Result)srt);
            
            System.out.println("Archivo creado con exito.");
            
        } catch (TransformerConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerException ex) {
            System.out.println(ex);
        }
        
        
        
        
    }
     
}


