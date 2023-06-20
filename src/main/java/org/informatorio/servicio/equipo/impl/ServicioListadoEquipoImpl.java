package org.informatorio.servicio.equipo.impl;

import org.informatorio.dominio.Equipo;
import org.informatorio.dominio.Jugador;
import org.informatorio.repository.impl.RepositoryEquipoImpl;
import org.informatorio.servicio.entrada.impl.ServicioConsola;
import org.informatorio.servicio.equipo.ServicioListadoEquipo;
import org.informatorio.servicio.equipo.orden.ServicioOrdenarEquipo;
import org.informatorio.servicio.equipo.orden.impl.OrdenNombreImpl;
import org.informatorio.servicio.equipo.orden.impl.OrdenNroCamisetaImpl;
import org.informatorio.servicio.equipo.orden.impl.OrdenPosicionCamisetaImpl;
import org.informatorio.servicio.inicio.impl.ServicioInicioImpl;
import org.informatorio.servicio.salida.file.ServicioSalidaArchivo;
import org.informatorio.servicio.salida.file.impl.ServicioSalidaArchivoImpl;

import java.io.IOException;

public class ServicioListadoEquipoImpl implements ServicioListadoEquipo {

    ServicioOrdenarEquipo servicioOrdenarEquipo;


    @Override
    public void listarNombreMasEntrenadorYCapitan() {
        System.out.println("*** Listado: Nombre, entrenador y capitán de un equipo ***");
        String nombreEquipo = new ServicioConsola().solicitarString("Ingrese el nombre del equipo: ");
        ServicioEquipoImpl servicioEquipoImpl = new ServicioEquipoImpl();
        System.out.println();
        String respuesta = "";

        Equipo equipo = new RepositoryEquipoImpl().buscarEquipo(nombreEquipo);
        if (equipo != null) {
            Jugador capitan = servicioEquipoImpl.getCapitan(equipo);
            String nombreCapitan = (capitan != null ? capitan.getNombre() + " " + capitan.getApellido() : "Sin capitán");
            respuesta = servicioEquipoImpl.getNombreMasEntrenador(equipo) +
                    "\n* Capitán: " + nombreCapitan.toUpperCase();
            System.out.println(respuesta);
        }
    }

    @Override
    public void exportarListaDeJugadores() {
        System.out.println("*** Exportar lista de jugadores de un equipo ***");
        String nombreEquipo = new ServicioConsola().solicitarString("Ingrese el nombre del equipo: ");

        Equipo equipo = new RepositoryEquipoImpl().buscarEquipo(nombreEquipo);
        if (equipo != null) {
            try {
                String rutaArchivoSalida = "src/main/java/org/informatorio/resources/jugadores_equipo_salida.txt";
                ServicioSalidaArchivo salidaArchivo = new ServicioSalidaArchivoImpl();
                salidaArchivo.exportarJugadoresEquipo(equipo.getListaDeJugadores(), rutaArchivoSalida);
                System.out.println("Lista de jugadores exportados en archivo 'jugadores_equipo_salida.txt'.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No se pudo exportar la lista de jugadores.");
            }
        }
    }

    @Override
    public void exportarEquiposOrdenados(boolean esOrdenadoPorNombre, boolean esOrdenadoPorNroCamiseta,
                                         boolean esOrdenadoPorPosicionYNroCamiseta) {
        String listado = "";
        String listadoEquipos = "";

        for (Equipo equipo : ServicioInicioImpl.listaEquipos) {
            if (esOrdenadoPorNombre) {
                servicioOrdenarEquipo = new OrdenNombreImpl();
            } else if (esOrdenadoPorNroCamiseta) {
                servicioOrdenarEquipo = new OrdenNroCamisetaImpl();
            } else if (esOrdenadoPorPosicionYNroCamiseta) {
                servicioOrdenarEquipo = new OrdenPosicionCamisetaImpl();
            }
            listado = servicioOrdenarEquipo.ordenar(equipo.getListaDeJugadores());

            listadoEquipos = listadoEquipos +
                            equipo.getNombre() + "\n" +
                            listado +
                            "\n\n";
        }
        try {
            String rutaArchivoSalida = "src/main/java/org/informatorio/resources/equipos.txt";
            new ServicioSalidaArchivoImpl().exportarAArchivo(listadoEquipos, rutaArchivoSalida);
            System.out.println("Lista de equipos ordenados por nombre de jugador exportada en archivo 'equipos.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo exportar el listado.");
        }
    }

    @Override
    public void listarDatosEquipo(boolean esOrdenadoPorNombre, boolean esOrdenadoPorNroCamiseta,
                               boolean esOrdenadoPorPosicionYNroCamiseta) {
        String nombreEquipo = new ServicioConsola().solicitarString("Ingrese el nombre del Equipo: ");
        System.out.println();
        String listado = "";
        String listadoDeJugadores = "";
        Equipo equipo = new RepositoryEquipoImpl().buscarEquipo(nombreEquipo);

        if (equipo != null) {
            if (esOrdenadoPorNombre) {
                servicioOrdenarEquipo = new OrdenNombreImpl();
                listadoDeJugadores = servicioOrdenarEquipo.ordenar(equipo.getListaDeJugadores());
            } else if (esOrdenadoPorNroCamiseta) {
                servicioOrdenarEquipo = new OrdenNroCamisetaImpl();
                listadoDeJugadores = servicioOrdenarEquipo.ordenar(equipo.getListaDeJugadores());
            } else if (esOrdenadoPorPosicionYNroCamiseta) {
                servicioOrdenarEquipo = new OrdenNombreImpl();
                listadoDeJugadores = servicioOrdenarEquipo.ordenar(equipo.getListaDeJugadores());
            } else {
                listadoDeJugadores = new ServicioEquipoImpl().getStringJugadores(equipo.getListaDeJugadores());
            }

            listado = new ServicioEquipoImpl().getNombreMasEntrenador(equipo) +
                    "\n* Jugadores: " +
                    "\n" + listadoDeJugadores;
        }
            System.out.println(listado);

    }


}



