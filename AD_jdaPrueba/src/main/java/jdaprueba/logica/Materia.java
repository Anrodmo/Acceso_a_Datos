
package jdaprueba.logica;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;


@Entity
public class Materia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    private String nombre;
    private String tipo;
    @ManyToOne
    private Carrera carreraEnMateria;

    public Materia() {
    }

    public Materia(int id, String nombre, String tipo, Carrera carreraEnMateria) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.carreraEnMateria = carreraEnMateria;
    }

    public Carrera getCarreraEnMateria() {
        return carreraEnMateria;
    }

    public void setCarreraEnMateria(Carrera carreraEnMateria) {
        this.carreraEnMateria = carreraEnMateria;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
