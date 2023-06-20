package org.informatorio.servicio.equipo.orden;

import org.informatorio.dominio.Jugador;

import java.util.List;

public interface ServicioOrdenarEquipo {

    String ordenar(List<Jugador> jugadores);
}
