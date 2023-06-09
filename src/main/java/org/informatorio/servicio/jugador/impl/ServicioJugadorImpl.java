package org.informatorio.servicio.jugador.impl;

import org.informatorio.dominio.Equipo;
import org.informatorio.dominio.Jugador;
import org.informatorio.dominio.Posicion;
import org.informatorio.servicio.entrada.impl.ServicioConsola;
import org.informatorio.servicio.inicio.impl.ServicioInicioImpl;
import org.informatorio.servicio.jugador.ServicioJugador;

public class ServicioJugadorImpl implements ServicioJugador {

    @Override
    public void buscarJugadorPorNombre() {
        System.out.println("*** Busqueda de jugadores por su nombre ***");
        String nombre = new ServicioConsola().solicitarString("Ingrese el nombre del jugador: ");

        String respuesta = "";

        for (Equipo equipo: ServicioInicioImpl.listaEquipos) {
            for (Jugador jugador: equipo.getListaDeJugadores()) {
                if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                    respuesta = respuesta +
                            "* Nombre: " + jugador.getNombre().toUpperCase() + " " + jugador.getApellido().toUpperCase() +
                            "\n* Posición: " + jugador.getPosicion().getNombre().toUpperCase() +
                            "\n* Capitán: " + (jugador.getEsCapitan() ? "Si" : "No") +
                            "\n* Equipo: " + jugador.getEquipo().getNombre().toUpperCase() +
                            "\n\n";
                }
            }
        }

        if (respuesta.equals("")) {
            System.out.println("No se encontraron jugadores con el nombre " + nombre);
        } else {
            System.out.println(respuesta);
        }
    }

    @Override
    public Jugador crearJugador() {
        ServicioConsola servicioConsola = new ServicioConsola();

        System.out.println(" * Ingrese datos del jugador *");
        String nombre = servicioConsola.solicitarString("Nombres: ");
        String apellido = servicioConsola.solicitarString("Apellido: ");
        double altura = servicioConsola.solicitarDouble("Altura: ");
        int goles = servicioConsola.solicitarInt("Cantidad de goles: ");
        int nroCamiseta = servicioConsola.solicitarInt("Número de camiseta: ");

        boolean esCapitan;
        String respuestaCapitan;
        do {
            respuestaCapitan = servicioConsola.solicitarSiNo("¿Es capitán?");
            esCapitan = respuestaCapitan.equalsIgnoreCase("S");
        } while (respuestaCapitan.equalsIgnoreCase("S") &&
                    respuestaCapitan.equalsIgnoreCase("N"));

        System.out.println();
        int respuestaPosicion;
        int indice;
        Posicion posicionJugador = null;
        do {
            System.out.println("- Posiciones: ");
            for (Posicion posicion : ServicioInicioImpl.listaPosiciones) {
                indice = ServicioInicioImpl.listaPosiciones.indexOf(posicion);
                System.out.println("  " + (indice + 1) + ")" + posicion.getNombre());
            }
            respuestaPosicion = servicioConsola.solicitarInt(" - Ingrese la posición: ");
            posicionJugador = ServicioInicioImpl.listaPosiciones.get(respuestaPosicion - 1);
        } while (posicionJugador == null);

        return new Jugador(nombre, apellido, altura, goles, posicionJugador, esCapitan, nroCamiseta);
    }


}
