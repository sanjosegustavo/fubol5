package org.informatorio.servicio.jugador;

import org.informatorio.dominio.Jugador;

public interface ServicioJugador {
    void buscarJugadorPorNombre();

    Jugador crearJugador();
}
