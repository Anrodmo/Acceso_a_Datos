

package com.mycompany.ad_jdaprueba;

import java.util.Calendar;
import jdaprueba.logica.Alumno;
import jdaprueba.logica.ControladoraLogica;



public class AD_jdaPrueba {

    public static void main(String[] args) {
        
        ControladoraLogica controlLogico = new ControladoraLogica();
        Calendar fecha = Calendar.getInstance();
        fecha.set(1977, 01, 27);
        Alumno alu = new Alumno(15, "Angel Jose", "Rodríguez", fecha );
                
        controlLogico.crearAlumno(alu);
        
        
        
    }
}
