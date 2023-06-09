package org.informatorio.servicio.equipo.impl;

import org.informatorio.dominio.Entrenador;
import org.informatorio.dominio.Equipo;
import org.informatorio.dominio.Jugador;
import org.informatorio.servicio.entrada.ServicioEntradaArchivo;
import org.informatorio.servicio.entrada.impl.ServicioConsola;
import org.informatorio.servicio.entrada.impl.ServicioEntradaArchivoImpl;
import org.informatorio.servicio.entrenador.impl.ServicioEntrenadorImpl;
import org.informatorio.servicio.equipo.ServicioEquipo;
import org.informatorio.servicio.inicio.impl.ServicioInicioImpl;
import org.informatorio.servicio.jugador.ServicioJugador;
import org.informatorio.servicio.jugador.impl.ServicioJugadorImpl;
import org.informatorio.servicio.validacion.ServicioValidacion;
import org.informatorio.servicio.validacion.impl.ServicioValidacionImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServicioEquipoImpl implements ServicioEquipo {


    @Override
    public void crearEquipo() {
        String entrada = "";
        String nombreEquipo = "";
        int dia, mes, anio;
        ServicioConsola servicioConsola = new ServicioConsola();

        do {

            nombreEquipo = servicioConsola.solicitarString("Nombre del equipo: ");

            dia = servicioConsola.solicitarInt("Día de creación: ");

            mes = servicioConsola.solicitarInt("Mes de creación: ");

            anio = servicioConsola.solicitarInt("Año de creación: ");

            System.out.println();

            Entrenador entrenador = new ServicioEntrenadorImpl().crearEntrenador();

            List<Jugador> jugadores = this.cargarJugadores();

            Equipo equipo = new Equipo(nombreEquipo, LocalDate.of(anio, mes, dia),
                    jugadores, entrenador);

            entrada = servicioConsola.solicitarSiNo("¿Cargar otro equipo?");

            ServicioInicioImpl.listaEquipos.add(equipo);

        } while (!entrada.equalsIgnoreCase("N"));
    }


    private List<Jugador> cargarJugadores() {
        String agregaDesdeArchivo = new ServicioConsola().solicitarSiNo("¿Agregar jugadores desde archivo 'jugadores.txt'?");

        if (agregaDesdeArchivo.equalsIgnoreCase("S")) {
            String rutaArchivo = "src/main/java/org/informatorio/resources/jugadores.txt";
            ServicioEntradaArchivo entradaArchivo = new ServicioEntradaArchivoImpl();
            return entradaArchivo.cargarJugadoresDesdeArchivo(rutaArchivo);
        } else {
            return this.cargarJugadoresPorConsola();
        }
    }

    private List<Jugador> cargarJugadoresPorConsola() {
        List<Jugador> jugadores;
        ServicioValidacion servicioValidacionImpl = new ServicioValidacionImpl();

        ServicioJugador servicioJugador = new ServicioJugadorImpl();
        jugadores = new ArrayList<>();
        Jugador jugador = null;

        for (int i = 0; i < 5; i++) {
            jugador = servicioJugador.crearJugador();
            jugador.setEsCapitan(jugador.getEsCapitan() && !servicioValidacionImpl.hayCapitan(jugadores));

            while (servicioValidacionImpl.existeElNroCamiseta(jugador.getNroDeCamiseta(), jugadores)) {
                int nroCamiseta = new ServicioConsola().solicitarInt("Número de camiseta repetido, ingrese otro: ");
                jugador.setNroDeCamiseta(nroCamiseta);
            }
            jugadores.add(jugador);
            System.out.println();
        }
        return jugadores;
    }


    @Override
    public void borrarEquipo() {
        System.out.println("*** Borrar un equipo ***");
        String nombreEquipo = new ServicioConsola().solicitarString("Nombre del equipo a borrar: ");

        List<Equipo> equipoLinkedList = new LinkedList<>();
        equipoLinkedList.addAll(ServicioInicioImpl.listaEquipos);
        boolean seEncontro = false;
        for (Equipo equipo: equipoLinkedList) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)){
                seEncontro = true;
                equipoLinkedList.remove(equipo);
                break;
            }
        }
        ServicioInicioImpl.listaEquipos.clear();
        ServicioInicioImpl.listaEquipos.addAll(equipoLinkedList);
        if (seEncontro) {
            System.out.println("El equipo " + nombreEquipo + " fue borrado.");
        } else {
            System.out.println(nombreEquipo + " no figura en la lista de equipos.");
        }
    }


    @Override
    public Jugador getCapitan(Equipo equipo) {
        Jugador capitan = null;
        for (Jugador jugador : equipo.getListaDeJugadores()) {
            if (jugador.getEsCapitan()) {
                capitan = jugador;
                break;
            }
        }
        return capitan;
    }


    @Override
    public Equipo buscarEquipo(String nombre) {
        Equipo equipoEncontrado = null;
        for (Equipo equipo: ServicioInicioImpl.listaEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombre)){
                equipoEncontrado = equipo;
                break;
            }
        }
        if (equipoEncontrado == null) {
            System.out.println("No se encontró el equipo " + nombre);
        }
        return equipoEncontrado;
    }

    @Override
    public String getStringJugadores(List<Jugador> jugadores){
        String respuesta = "";
        for (Jugador jugador : jugadores) {
            respuesta = respuesta +
                    " - " + jugador.getNombre().toUpperCase() + " " + jugador.getApellido().toUpperCase() +
                    " " + jugador.getNroDeCamiseta() +
                    "\n";
        }
        return respuesta;
    }

    @Override
    public String getNombreMasEntrenador(Equipo equipo) {
        String entrenador = (equipo.getEntrenador() != null ?
                equipo.getEntrenador().getNombre() + " " + equipo.getEntrenador().getApellido() :
                "Sin entrenador");
        String respuesta = "" +
                "* Nombre: " + equipo.getNombre().toUpperCase() +
                "\n* Entrenador: " + entrenador.toUpperCase();
        return respuesta;
    }



}
