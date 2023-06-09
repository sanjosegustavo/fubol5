package org.informatorio.servicio.entrada;

import org.informatorio.dominio.Jugador;

import java.util.List;

public interface ServicioEntradaArchivo {

    List<Jugador> cargarJugadoresDesdeArchivo(String rutaArchivo);

}
