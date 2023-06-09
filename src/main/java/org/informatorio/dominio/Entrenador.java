package org.informatorio.dominio;

import java.time.LocalDate;

public class Entrenador extends Persona {
    private LocalDate fechaDeNacimiento;


    public Entrenador(String nombre, String apellido,  LocalDate fechaDeNacimiento) {
        super(nombre, apellido);
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public LocalDate getFechaDeNacimiento() {

        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
}
