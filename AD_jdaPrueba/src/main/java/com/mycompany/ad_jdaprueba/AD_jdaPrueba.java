

package com.mycompany.ad_jdaprueba;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import jdaprueba.logica.Alumno;
import jdaprueba.logica.Carrera;
import jdaprueba.logica.ControladoraLogica;
import jdaprueba.logica.Materia;



public class AD_jdaPrueba {

    public static void main(String[] args) {
        
//========= creo un objeto de la controladora logica para gestionar operaciones ==============        
        ControladoraLogica controlLogico = new ControladoraLogica();
        Calendar fecha = Calendar.getInstance();
        fecha.set(1977, 01, 27);
        
//== Para crear la relacion materia <-> carrera hay que crear primero la carrera con  <-- al final del todo
//   la lista de carreras vacía:
        LinkedList<Materia> listaMaterias = new LinkedList<>();
        LinkedList<Materia> listaMaterias2 = new LinkedList<>();
        Carrera informatica = new Carrera(10, "informática", listaMaterias);
        Carrera teleco = new Carrera(10, "telecominaciones", listaMaterias2);
        
//== Guardo las carreras en la BBDD con la lista de materias vacías       
        controlLogico.crearCarrera(informatica);
        controlLogico.crearCarrera(teleco);
        
//== al estar ya hecha la relaccion hay que crear materias en la BBDD
//   pero no están asociada a las carreras

        Materia calculo = new Materia(1, "Calculo", "trimestral",informatica);
        Materia algebra = new Materia(1, "Algebra", "trimestral",informatica);
        Materia comunicaciones = new Materia(1, "Comunicaciones", "semestral",teleco);
        
        
        
        controlLogico.crearMateria(calculo);
        controlLogico.crearMateria(algebra);
        controlLogico.crearMateria(comunicaciones);
        
//======== agregamos las materias a linkedlist de materias  =================       
        listaMaterias.add(calculo);
        listaMaterias.add(algebra);
        listaMaterias2.add(comunicaciones);
        
//======== ponemos las materias en las carreras a nivel LÓGICO ==============       
        informatica.setListaMateria(listaMaterias);
        teleco.setListaMateria(listaMaterias2);

//======== ahora añadimos las materias a las carreras a nivel de BBDD =======
        controlLogico.editarCarrera(informatica);
        controlLogico.editarCarrera(teleco);

        Alumno alu = new Alumno(8, "Angelico", "Rodriguico", fecha, informatica);
        Alumno alu2 = new Alumno(8, "Pedrico", "Pedrón", fecha, teleco);
        
//========= llamamos al método de la controladora logica para crear ==============   
        controlLogico.crearAlumno(alu);
        controlLogico.crearAlumno(alu2);
//========= llamamos al método de la controladora logica para eliminar ==============   
        //controlLogico.eliminarAlumno(1);
        
//========= llamamos al método de la controladora logica para editar ==============   
//        alu.setApellido("Rodriguete");
//        controlLogico.editarAlumno(alu);   // esto edita por la id del objeto, si no 
//                                           // existe lo crea en la BBDD

//=== llamamos al método de la controladora logica para buscar un Alumno por id ======  
        alu= controlLogico.traerAlumno(1);
        System.out.println("\nAlumno buscado por id 1: "+alu.toString());
        
//=== llamamos al método de la controladora logica para buscar todos los Alumnos ======        
        ArrayList<Alumno> listadoAlumnos  = controlLogico.traerListaAlumnos();
        System.out.println("================Listado de alumnos =============================");
        for (Alumno unAlumno : listadoAlumnos) {
            System.out.println("Alumno -> "+unAlumno.getNombre()+" "+unAlumno.getApellido());
            System.out.println("Carrera -> "+unAlumno.getSuCarrera().getNombre());
            for (Materia materia : unAlumno.getSuCarrera().getListaMateria()) {
                System.out.println("\tMateria -> "+materia.getNombre());
            }
        }
        
    }
}
