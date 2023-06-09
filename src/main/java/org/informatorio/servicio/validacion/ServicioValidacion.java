package org.informatorio.servicio.validacion;

import org.informatorio.dominio.Jugador;

import java.util.List;

public interface ServicioValidacion {

    boolean hayCapitan(List<Jugador> jugadores);

    boolean existeElNroCamiseta(int nroCamiseta, List<Jugador> jugadores);
}
