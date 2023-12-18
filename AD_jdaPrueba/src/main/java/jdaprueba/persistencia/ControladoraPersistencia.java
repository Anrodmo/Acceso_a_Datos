
package jdaprueba.persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdaprueba.logica.Alumno;
import jdaprueba.logica.Carrera;
import jdaprueba.logica.Materia;
import jdaprueba.persistencia.exceptions.NonexistentEntityException;

/**
 * Esta clase recibe los datos de la coontroladora lógica y los transmite a las
 * diferentes clases de persistencia para manejar la BBDD
 * @author anrod
 */
public class ControladoraPersistencia {
    
    // Cremos un objeto de cada una de las clases controladoras de persistencia
    AlumnoJpaController alumnoJPA = new AlumnoJpaController();
    CarreraJpaController carreraJPA = new CarreraJpaController();
    MateriaJpaController materiaJPA = new MateriaJpaController();
    
    
    // Creamos los diferentes métodos que necesitaremos para cada una de las clases
    // de persistencia, en este caso los métodos son iguales (con var diferentee)
    public void crearAlumno(Alumno alu) {
          alumnoJPA.create(alu);
    }

    public void eliminarAlumno(int id) {
        try {
            alumnoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarAlumno(Alumno alu) {
       
        try {
            alumnoJPA.edit(alu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Alumno traerAlumno(int id) {
        return alumnoJPA.findAlumno(id);
    }

    public ArrayList<Alumno> traerListaAlimnos() {
        List<Alumno> listado = alumnoJPA.findAlumnoEntities();
//        ArrayList<Alumno> listaAlumnos =new ArrayList<>(listado);
//        return listaAlumnos;
        return  new ArrayList<>(listado);
    }

    public void crearCarrera(Carrera carr) {
        carreraJPA.create(carr);
    }

    public void eliminarCarrera(int id) {
        try {
            carreraJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCarrera(Carrera carr) {
        try {
            carreraJPA.edit(carr);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Carrera traerCarrera(int id) {
        return carreraJPA.findCarrera(id);
    }

    public ArrayList<Carrera> traerListaCarreras() {
        List<Carrera> listado = carreraJPA.findCarreraEntities();
        return new ArrayList<>(listado);
    }

    public void crearMateria(Materia materia) {
        materiaJPA.create(materia);
    }

    public void eliminarMateria(int id) {
        try {
            materiaJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarMateria(Materia materia) {
        try {
            materiaJPA.edit(materia);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Materia traerMateria(int id) {
        return materiaJPA.findMateria(id);
    }

    public LinkedList<Materia> traerListaMaterias() {
        List<Materia> listado = materiaJPA.findMateriaEntities();
        return new LinkedList<>(listado);
    }
    
}
