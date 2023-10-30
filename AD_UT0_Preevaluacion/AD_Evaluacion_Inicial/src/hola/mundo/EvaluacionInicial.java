
package hola.mundo;

import clases.Cliente;
import java.util.InputMismatchException;
import java.util.Scanner;


public class EvaluacionInicial
{

  
    public static void main(String[] args) {
       
    /*--------------------- Programación Ejercicio 1 --------------------------*/
    
        int numeros[]={5,3,1,0,7};
        
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("El numero en la posición "+i+" es: "+numeros[i]);            
        }  
       
        /*  Programación  ejercicios 2-3-4-5  */   
        Cliente cliente1 = new Cliente();        
        cliente1.pedirDatosCliente();        
        System.out.println(cliente1.toString());             
        Cliente cliente2 = new Cliente(16125640, "angel", "albarderos", "967238563");  
        System.out.println(cliente2.toString()); 
        cliente1.guardarDatos();
        cliente2.guardarDatos();
        
        /*  Programación II ejercicio 1 */
        dividirDosnumeros();
        /*  Programación II ejercicio 2 */
        dividirDosnumerosExcepcxiones1(); 
        /*  Programación II ejercicio 3 */
        dividirDosnumerosExcepcxiones2();    
    
    }
    
    /*--------------------- Programación II Ejercicio 1 -----------------------*/
    private static void dividirDosnumeros(){
        Scanner teclado = new Scanner (System.in);
        int dividendo, divisor;
        System.out.println("======== Calcule la division de dos números ========");
        System.out.print("Introduzca el dividendo --> ");
        dividendo = teclado.nextInt();
        System.out.print("Introduzca el divisor --> ");
        divisor = teclado.nextInt();
        System.out.println("El resultado de la división es :"+ ((double)dividendo/divisor));       
    }
    
    /*--------------------- Programación II Ejercicio 2 -----------------------*/
    private static void dividirDosnumerosExcepcxiones1(){
        Scanner teclado = new Scanner (System.in);
        int dividendo=0, divisor=0;
        boolean datosIncorrectos = false;
        System.out.println("======== Calcule la division de dos números ========");
        System.out.print("Introduzca el dividendo --> ");
        try{
            dividendo = teclado.nextInt();
        }catch (InputMismatchException ex){
            datosIncorrectos = true;
            teclado.next();
        }             
        System.out.print("Introduzca el divisor --> ");
        try{
            divisor = teclado.nextInt();
        }catch (InputMismatchException ex){
            datosIncorrectos = true;
            teclado.next();
        }
        if(datosIncorrectos)
            System.out.println("No se ha podido realizar la operacion");
        else
            System.out.println("El resultado de la división es :"+ ((double)dividendo/divisor));        
    }
    
    /*--------------------- Programación II Ejercicio 3 -----------------------*/
    private static void dividirDosnumerosExcepcxiones2(){
        Scanner teclado = new Scanner (System.in);
        int dividendo=0, divisor=0;
        boolean datosIncorrectos = false;
        System.out.println("======== Calcule la division de dos números ========");
        System.out.print("Introduzca el dividendo --> ");
        try{
            dividendo = teclado.nextInt();
        }catch (InputMismatchException ex){
            datosIncorrectos = true;
            teclado.next();
        }             
        System.out.print("Introduzca el divisor --> ");
        try{
            divisor = teclado.nextInt();
            while (divisor == 0){
                System.out.println("No es posible dividor por 0, introduzca un nuevo divisor.");
                System.out.print("Introduzca el divisor --> ");
                divisor = teclado.nextInt();
            }
        }catch (InputMismatchException ex){
            datosIncorrectos = true;
            teclado.next();
        }
        if(datosIncorrectos)
            System.out.println("No se ha podido realizar la operacion");
        else
            System.out.println("El resultado de la división es :"+ ((double) dividendo/divisor) );        
    }
    
    
   // ArithmeticException
}
