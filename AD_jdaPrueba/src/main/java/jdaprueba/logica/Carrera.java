
package jdaprueba.logica;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.LinkedList;
//import jakarta.persistence.*;

@Entity
public class Carrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    
/*  Cada carrera tiene muchas materias y cada materia lo es de una sola carrera.
    Como la carrera tiene muchas materias se trae aqui una Lista con todas ellas
    Si solo hacemos esto  (en el lado de la materia no hacemos nada) entonces en la
    BBDD se crea una tabla con idCarrera + idMateria.
    */
    @OneToMany (mappedBy = "carreraEnMateria")  //<- Hay que decirle como se va
    private LinkedList<Materia> listaMateria;   // llamar en la BBDD el campo que será
                                                // el id de carrera en Materia
    public Carrera() {
    }

    public Carrera(int id, String nombre, LinkedList<Materia> listaMateria) {
        this.id = id;
        this.nombre = nombre;
        this.listaMateria = listaMateria;
    }

    public LinkedList<Materia> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(LinkedList<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    
    
    
    
}
