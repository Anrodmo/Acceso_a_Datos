
package jdaprueba.logica;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Calendar;

@Entity    // indico que esta clase es una entodad de la BBDD
public class Alumno implements Serializable {
    @Id   // indico cual es el campo que va a ser primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE)   // autogenerados
    private int id;  // kas dos annotations deben ir en este orden justo encima
    @Basic   // campos normales
    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)   // ibdicamos que es un campo solo fecha 
    private Calendar fechaNac;  // alfinal lo cambioa a Calendar porque Date esta medio  deprecated
                                // y mysql admite o Date o Calendar, pero no LocalDate
    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellido, Calendar fechaNac) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
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
    
    
    
}
