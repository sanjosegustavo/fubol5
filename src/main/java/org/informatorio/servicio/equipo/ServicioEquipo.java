package org.informatorio.servicio.equipo;

import org.informatorio.dominio.Entrenador;
import org.informatorio.dominio.Equipo;
import org.informatorio.dominio.Jugador;

import java.time.LocalDate;
import java.util.List;

public interface ServicioEquipo {

    void crearEquipo();

    void borrarEquipo();

    Equipo buscarEquipo (String nombre);

    Jugador getCapitan(Equipo equipo);

    String getStringJugadores(List<Jugador> jugadores);

    String getNombreMasEntrenador(Equipo equipo);

}
