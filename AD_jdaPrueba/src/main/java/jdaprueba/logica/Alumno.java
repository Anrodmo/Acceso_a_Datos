
package jdaprueba.logica;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import java.util.Calendar;

@Entity    // indico que esta clase es una entodad de la BBDD
// @Table(name="nombre de la tabla", schema = "esquema al que pertenece") <- si quiero que la tabla se 
// relacione o cree con una tabla con un nombre y esquema determinado.
public class Alumno implements Serializable {
    @Id   // indico cual es el campo que va a ser primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE)   // autogenerados
    private int id;  // kas dos annotations deben ir en este orden justo encima
    
    // @Transient esto se usaria para ndicar que este atributo no es persistente
    @Basic   // campos normales
    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)   // indicamos que es un campo solo fecha 
    private Calendar fechaNac;  // alfinal lo cambioa a Calendar porque Date esta medio  deprecated
                                // y mysql admite o Date o Calendar, pero no LocalDate
    
    @OneToOne
    private Carrera suCarrera;  // como prueba se define 1to1, es decir una alumno una carrera
                                // esto nos va a crear un id de la carrera en la tabla alumnos
                                // en este caso no se crea relacion en la clase Carrera
//    @Transient     // esto se usaria para definir atributos quen quiero que sean
//    private int años;    // persistentes en la BBDD
    
    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellido, Calendar fechaNac, Carrera suCarrera) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.suCarrera = suCarrera;
    }

    public Carrera getSuCarrera() {
        return suCarrera;
    }

    public void setSuCarrera(Carrera suCarrera) {
        this.suCarrera = suCarrera;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Calendar getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Calendar fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac.getTime()
                + ", suCarrera=" + suCarrera.getNombre() + '}';
    }

   
    
    
    
}
