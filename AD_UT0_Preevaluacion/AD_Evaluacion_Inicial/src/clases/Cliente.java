/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author anrod
 */
/* ----------------Programación Ejercicio 2 ------------------------------*/
public class Cliente {
    private int id_cliente;
    private String nombre, direccion, telefono;

    public Cliente() {
    }
    
    public Cliente(int id_cliente, String nombre, String direccion, String telefono) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    /* ----------------Programación Ejercicio 3 ------------------------------*/
    @Override
    public String toString() {
        return "ID de cliente: "+this.id_cliente+"\nNombre;        "+this.nombre
                +"\nDirección:     "+this.direccion+"\nTeléfono:      "+this.telefono;             
    }
    
    /* ----------------Programación Ejercicio 4 ------------------------------*/
    public void pedirDatosCliente (){
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Introduzca los datos del cliente:");
        System.out.print("Identificador del cliente --> ");
        this.id_cliente = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Nombre del cliente --> ");
        this.nombre = teclado.nextLine();
        System.out.print("Direccion del cliente --> ");
        this.direccion = teclado.nextLine();
        System.out.print("Teléfono del cliente --> ");
        this.telefono = teclado.nextLine();  
    }
    /* ----------------Programación Ejercicio 5 ------------------------------*/
    public void guardarDatos(){       
        String ruta ="src/clases/misclientes.txt";
        File archivo = new File (ruta);
        // Aqui habria que controlar nullpoinexception por si el string de la ruta fuera
        // null.
       
        if (archivo.exists() && archivo.isFile()){
            try(BufferedWriter buferEscritura = new BufferedWriter(new FileWriter(ruta,true))){
                 buferEscritura.write(this.toString());
                 buferEscritura.newLine();          
            } catch (IOException ex) {
                System.out.println("Error al crear o escribir en el archivo.");
            }
        }else if(!archivo.isDirectory()){
            try(BufferedWriter buferEscritura = new BufferedWriter(new FileWriter(ruta))){
                 buferEscritura.write(this.toString());
                 buferEscritura.newLine();          
            } catch (IOException ex) {
                System.out.println("Error al crear o escribir en el archivo.");
            }
        }else
            System.out.println("Imposible escribir en esa ruta, la ruta es un directorio. ");
    }
}
