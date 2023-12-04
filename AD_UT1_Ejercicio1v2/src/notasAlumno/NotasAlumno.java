/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notasAlumno;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anrod
 */
public class NotasAlumno {
    private String nombre;
    private ArrayList<Integer>notas;

    public NotasAlumno(String nombre, ArrayList<Integer> notas) {
        if (nombre == null)
            this.nombre="";
        else       
            this.nombre = nombre;
        if (notas == null)
            this.notas = new ArrayList();
        else
            this.notas=notas;
    }
    
    /**
     * Lee un archivo de texto CSV con formato: nombre:nota:nota:nota: ...
     * Donde nota ha de ser un numero  entero y nombre una cadena  de texto.
     * @param ruta String con la ruta del archivo de lectura
     * @return ArrayList de objetos  NotaAlumnos, si hay algun error lo devuelve null.
     */
    public static ArrayList<NotasAlumno> leerArchivoNotas(String ruta){       
        ArrayList<NotasAlumno> listado=null;
        ArrayList<Integer> notas;
        String[] linea;
        File archivo;
        
        if(ruta != null){
            archivo  = new File(ruta);
            
            if ( archivo.exists() && !archivo.isDirectory() && archivo.canRead() ){
                // Try  con recursos para que cierre solo el IS
                try(FileInputStream in = new FileInputStream(ruta)){
                    listado= new ArrayList();
                    Scanner lectura = new Scanner(in);
                    //Minetras haya algo que leer guardo en linea la siguiente linea
                    while (lectura.hasNext()){
                        notas= new ArrayList();
                        linea = lectura.nextLine().split(":");
                        // Para cada nota tras el nombre  la parseo al arraylist de notas
                        for (int i = 1; i < linea.length; i++) {
                            try{
                                notas.add(Integer.valueOf(linea[i]));
                            }catch (NumberFormatException ex){
                                System.out.println("Error. Dato leido no es una nota válida."
                                        + "Se continua con el siguiente  dato.");
                            }                   
                        }
                        // Cada alumno  con sus notas lo añado  al arraylist de NotasAlumno
                        listado.add( new NotasAlumno(linea[0], notas) );            
                    }
                }catch(IOException ex){
                    System.out.println("Error de lecttura / escritura");                   
                }                
            }else
                System.out.println("Error, la ruta especificada no es un archivo o no se tiene acceso.");
            
        }else {
            listado = null;
            System.out.println("Error, la ruta no puede ser null");          
        }                                
        return listado; // Devuelvo un ArrayList de AlumnoNotas       
    }
    
    /**
     * Crea o  sobreescribe un archivo cvon los datos  dfe la coleccion  failitada
     * en formato  CSV --> nombre:nota:nota ...
     * @param listado coleccion de Notasalumno
     * @param ruta ruta donde se creará  o sobreescribirá el archivo
     */
    public static void escribirNotasEnDisco(ArrayList <NotasAlumno> listado, String ruta){
               
        File archivo;
        if(ruta!=null && listado != null){
            archivo  = new File(ruta);           
            try {
                // si el archivo  no es un directorio  y
                // obien existe y se puede esxribir en el,       o bien no exsite y se puede crear y se crea
                if( !archivo.isDirectory() && (archivo.exists()&&archivo.canWrite()  || ! archivo.exists()&&archivo.createNewFile()) ){
                    // si todo es correcto creo o sobreescribo el archivo
                    try(BufferedWriter  bufferEscritura = new BufferedWriter(new FileWriter(archivo))){
                        for (NotasAlumno notasAlumno : listado) {
                            bufferEscritura.write(notasAlumno.toString());
                            bufferEscritura.newLine();
                        }
                    } catch (IOException ex) {
                        System.out.println("Error de escritura al guardar el listado");
                    }
                }else   //  si la  ruta es un directorio o no se tiene  permiso de escritura lanzo una ex¡cepcion
                    System.out.println("Error, el archivo es un directorio o no se"
                            + "tiene acceso ");
            } catch (IOException ex) {
                System.out.println("Error, la ruta no es un archivo o no se tiene acceso.");
            }         
        }else{
            if(listado == null)  // si la coleccion de NopaAlumno es null lanzo una excepcion.
                System.out.println("Error, el lsitaod de alumno y notas  no puede ser nulo.");
            if(ruta == null)  // si la coleccion de NopaAlumno es null lanzo una excepcion.
                System.out.println("Error, la ruta facilitada no puede ser null.");
        }              
    }
    
    /**
     * Muestra el nombre y la nota media con dos decimales
     * @return String
     */
    public String muestraNotaMedia(){
        int suma=0;
        double  media;
        String  espacios="";
        for(int nota : this.notas){
            suma+=nota; // sumo para cada nota de la coleccion
        }
        for (int i = this.nombre.length(); i < 15; i++) {
            espacios+=" "; // añado  espacios  para justificar las notas medias
        }
        media = (double)suma/this.notas.size(); 
        
        return "Nombre : "+this.nombre+espacios+"|     Nota media : "
                // añado la  media pero en formato con dos decimales unicamente
                +( new DecimalFormat("0.00") ).format(media);
    }

    @Override
    public String toString() {
        String notas="";
        for (Integer nota : this.notas) {
            notas+=":"+nota.toString();
        }
        return this.nombre+notas;
    }
    
    
    
    
    
}
