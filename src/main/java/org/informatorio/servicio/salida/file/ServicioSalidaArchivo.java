package org.informatorio.servicio.salida.file;

import org.informatorio.dominio.Equipo;
import org.informatorio.dominio.Jugador;

import java.io.IOException;
import java.util.List;

public interface ServicioSalidaArchivo {
    void exportarJugadoresEquipo(List<Jugador> jugadores, String rutaDeDestino) throws IOException;

    void exportarAArchivo(String listado, String rutaDeDestino) throws IOException;
}
