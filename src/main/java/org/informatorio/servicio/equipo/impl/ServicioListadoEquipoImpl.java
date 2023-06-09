package org.informatorio.servicio.equipo.impl;

import org.informatorio.dominio.Equipo;
import org.informatorio.dominio.Jugador;
import org.informatorio.servicio.entrada.impl.ServicioConsola;
import org.informatorio.servicio.equipo.ServicioEquipo;
import org.informatorio.servicio.equipo.ServicioListadoEquipo;
import org.informatorio.servicio.inicio.ServicioInicio;
import org.informatorio.servicio.inicio.impl.ServicioInicioImpl;
import org.informatorio.servicio.salida.file.ServicioSalidaArchivo;
import org.informatorio.servicio.salida.file.impl.ServicioSalidaArchivoImpl;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ServicioListadoEquipoImpl implements ServicioListadoEquipo {


    @Override
    public void listarNombreMasEntrenadorYCapitan() {
        System.out.println("*** Listado: Nombre, entrenador y capitán de un equipo ***");
        String nombreEquipo = new ServicioConsola().solicitarString("Ingrese el nombre del equipo: ");
        ServicioEquipoImpl servicioEquipoImpl = new ServicioEquipoImpl();
        System.out.println();
        String respuesta = "";

        Equipo equipo = servicioEquipoImpl.buscarEquipo(nombreEquipo);
        if (equipo != null) {
            Jugador capitan = servicioEquipoImpl.getCapitan(equipo);
            String nombreCapitan = (capitan != null ? capitan.getNombre() + " " + capitan.getApellido() : "Sin capitán");
            respuesta = servicioEquipoImpl.getNombreMasEntrenador(equipo) +
                    "\n* Capitán: " + nombreCapitan.toUpperCase();
            System.out.println(respuesta);
        }
    }

    @Override
    public void listarNombreMasEntrenadorYJugadores() {
        System.out.println("*** Listado: Nombre, entrenador y jugadores de un equipo ***");
        String listado = this.listarNombreMasEntrenadorYJugadores(false,
                false, false);
        System.out.println(listado);
    }

    @Override
    public void listarNombreMasEntrenadorYJugadoresOrdenadosPorNombreJugador() {
        System.out.println("*** Listado: Nombre, entrenador y jugadores de un equipo - Ordenado por nombre de jugador ***");
        String listado = this.listarNombreMasEntrenadorYJugadores(true,
                false, false);
        System.out.println(listado);
    }

    @Override
    public void listarNombreMasEntrenadorYJugadoresOrdenadosPorNroCamiseta() {
        System.out.println("*** Listado: Nombre, entrenador y jugadores de un equipo - " +
                "Ordenado por número de camiseta ***");
        String listado = this.listarNombreMasEntrenadorYJugadores(false,
                true, false);
        System.out.println(listado);
    }

    @Override
    public void listarNombreMasEntrenadorYJugadoresOrdenadosPorPosicionYNroCamiseta() {
        System.out.println("*** Listado: Nombre, entrenador y jugadores de un equipo - " +
                "Ordenado por posición y número de camiseta ***");
        String listado = this.listarNombreMasEntrenadorYJugadores(false,
                false, true);
        System.out.println(listado);
    }

    @Override
    public void exportarListaDeJugadores() {
        System.out.println("*** Exportar lista de jugadores de un equipo ***");
        String nombreEquipo = new ServicioConsola().solicitarString("Ingrese el nombre del equipo: ");

        Equipo equipo = new ServicioEquipoImpl().buscarEquipo(nombreEquipo);
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
    public void exportarTodosLosEquiposOrdenadosPorNombreJugador(boolean esOrdenadoPorNombre, boolean esOrdenadoPorNroCamiseta,
                                                                 boolean esOrdenadoPorPosicionYNroCamiseta) {
        String listado = "";
        String listadoEquipos = "";
        for (Equipo equipo : ServicioInicioImpl.listaEquipos) {
            if (esOrdenadoPorNombre) {
                listado = this.getStringJugadoresOrdenadosPorNombre(equipo.getListaDeJugadores());
            } else if (esOrdenadoPorNroCamiseta) {
                listado = this.getStringJugadoresOrdenadosPorNroCamiseta(equipo.getListaDeJugadores());
            } else if (esOrdenadoPorPosicionYNroCamiseta) {
                listado = this.getStringJugadoresOrdenadosPorPosicionYNroCamiseta(equipo.getListaDeJugadores());
            }

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
    public void exportarTodosLosEquiposOrdenadosPorNroCamiseta() {

    }

    @Override
    public void exportarTodosLosEquiposOrdenadosPorPosicionYNroCamiseta() {

    }

    private String listarNombreMasEntrenadorYJugadores(boolean esOrdenadoPorNombre, boolean esOrdenadoPorNroCamiseta,
                                                     boolean esOrdenadoPorPosicionYNroCamiseta) {
        String nombreEquipo = new ServicioConsola().solicitarString("Ingrese el nombre del Equipo: ");
        System.out.println();
        String listado = "";
        String listadoDeJugadores = "";
        Equipo equipo = new ServicioEquipoImpl().buscarEquipo(nombreEquipo);

        if (equipo != null) {
            if (esOrdenadoPorNombre) {
                listadoDeJugadores = this.getStringJugadoresOrdenadosPorNombre(equipo.getListaDeJugadores());
            } else if (esOrdenadoPorNroCamiseta) {
                listadoDeJugadores = this.getStringJugadoresOrdenadosPorNroCamiseta(equipo.getListaDeJugadores());
            } else if (esOrdenadoPorPosicionYNroCamiseta) {
                listadoDeJugadores = this.getStringJugadoresOrdenadosPorPosicionYNroCamiseta(equipo.getListaDeJugadores());
            } else {
                listadoDeJugadores = new ServicioEquipoImpl().getStringJugadores(equipo.getListaDeJugadores());
            }

            listado = new ServicioEquipoImpl().getNombreMasEntrenador(equipo) +
                    "\n* Jugadores: " +
                    "\n" + listadoDeJugadores;
        }
            return listado;
    }

    private String getStringJugadoresOrdenadosPorNombre(List<Jugador> jugadores) {
        List<Jugador> jugadoresLinkedList = new LinkedList<>(jugadores);
        jugadoresLinkedList.sort(new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                return jugador1.getNombre().compareToIgnoreCase(jugador2.getNombre());
            }
        });
        return new ServicioEquipoImpl().getStringJugadores(jugadoresLinkedList);
    }

    private String getStringJugadoresOrdenadosPorNroCamiseta(List<Jugador> jugadores) {
        List<Jugador> jugadoresLinkedList = new LinkedList<>(jugadores);
        jugadoresLinkedList.sort(new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                return Integer.compare(jugador1.getNroDeCamiseta(), jugador2.getNroDeCamiseta());
            }
        });
        return new ServicioEquipoImpl().getStringJugadores(jugadoresLinkedList);
    }

    private String getStringJugadoresOrdenadosPorPosicionYNroCamiseta(List<Jugador> jugadores) {
        List<Jugador> jugadoresLinkedList = new LinkedList<>(jugadores);
        jugadoresLinkedList.sort(new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                if (jugador1.getPosicion().getNombre().compareToIgnoreCase(jugador2.getPosicion().getNombre()) == 0) {
                    return Integer.compare(jugador1.getNroDeCamiseta(), jugador2.getNroDeCamiseta());
                } else {
                    return jugador1.getPosicion().getNombre().compareToIgnoreCase(jugador2.getPosicion().getNombre());
                }
            }
        });
        return new ServicioEquipoImpl().getStringJugadores(jugadoresLinkedList);
    }


}



