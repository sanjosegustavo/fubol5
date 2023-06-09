package org.informatorio.servicio.entrenador.impl;

import org.informatorio.dominio.Entrenador;
import org.informatorio.servicio.entrada.impl.ServicioConsola;
import org.informatorio.servicio.entrenador.ServicioEntrenador;

import java.time.LocalDate;

public class ServicioEntrenadorImpl implements ServicioEntrenador {


    @Override
    public Entrenador crearEntrenador() {
        String nombre = "";
        String apellido = "";
        int diaNacimiento, mesNacimiento, anioNacimiento;
        ServicioConsola servicioConsola = new ServicioConsola();

        nombre = servicioConsola.solicitarString("Nombres del entrenador: ");

        apellido = servicioConsola.solicitarString("Apellido del entrenador: ");

        diaNacimiento = servicioConsola.solicitarInt("Día de nacimiento del entrenador: ");

        mesNacimiento = servicioConsola.solicitarInt("Mes de nacimiento del entrenador: ");

        anioNacimiento = servicioConsola.solicitarInt("Año de nacimiento del entrenador: ");

        return new Entrenador(nombre, apellido, LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento));
    }
}
