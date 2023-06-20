package org.informatorio.servicio.equipo.orden.impl;

import org.informatorio.dominio.Jugador;
import org.informatorio.servicio.equipo.impl.ServicioEquipoImpl;
import org.informatorio.servicio.equipo.orden.ServicioOrdenarEquipo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class OrdenNroCamisetaImpl implements ServicioOrdenarEquipo {

    @Override
    public String ordenar(List<Jugador> jugadores) {
        List<Jugador> jugadoresLinkedList = new LinkedList<>(jugadores);
        jugadoresLinkedList.sort(new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                return Integer.compare(jugador1.getNroDeCamiseta(), jugador2.getNroDeCamiseta());
            }
        });
        return new ServicioEquipoImpl().getStringJugadores(jugadoresLinkedList);
    }
}
