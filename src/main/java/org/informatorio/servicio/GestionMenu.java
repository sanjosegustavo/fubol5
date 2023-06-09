package org.informatorio.servicio;

import org.informatorio.servicio.entrada.impl.ServicioConsola;
import org.informatorio.servicio.equipo.ServicioEquipo;
import org.informatorio.servicio.equipo.impl.ServicioEquipoImpl;
import org.informatorio.servicio.equipo.impl.ServicioListadoEquipoImpl;
import org.informatorio.servicio.jugador.ServicioJugador;
import org.informatorio.servicio.jugador.impl.ServicioJugadorImpl;

public class GestionMenu {
    private String[] menuOpciones =  {
            "1  - Crear equipo.",
            "2  - Borrar equipo.",
            "3  - Buscar jugadores por nombre.",
            "4  - Buscar equipo.",
            "5  - Ver detalles de equipo.",
            "6  - Ver detalles de equipo - ordenado por 'nombre de jugador'.",
            "7  - Ver detalles de equipo - ordenado por 'número de camiseta'.",
            "8  - Ver detalles de equipo - ordenado por 'posición y número de camiseta'.",
            "9  - Exportar lista de jugadores de un equipo a un archivo txt.",
            "10  - Exportar lista de equipos ordenados por 'nombre de jugador' a un archivo txt.",
            "11 - Exportar lista de equipos ordenados por 'número de camiseta' a un archivo txt.",
            "12 - Exportar lista de equipos ordenados por 'posición y número de camiseta' a un archivo txt.",
            "0  - SALIR."
    };

    private void mostrarTitulo() {
        System.out.println();
        System.out.println("**************************");
        System.out.println("*** FUT5 APP ***");
        System.out.println("**************************");
        System.out.println();
    }

    private int getOpcion() {
        for (String menuOpcione : menuOpciones) {
            System.out.println(menuOpcione);
        }
        System.out.println("Seleccione una opción");
        int opcion = ServicioConsola.scanner.nextInt();
        ServicioConsola.scanner.nextLine();
        return opcion;
    }

    private void ejecutarOpcion(int opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case 1:
                this.crearEquipo();
                break;
            case 2:
                this.borrarEquipo();
                break;
            case 3:
                this.buscarJugadorPorNombre();
                break;
            case 4:
                this.buscarEquipo();
                break;
            case 5:
                this.mostrarDetallesEquipo();
                break;
            case 6:
                this.mostrarDetallesEquipoOrdenadoPorNombreJugador();
                break;
            case 7:
                this.verDetallesEquipoOrdenadoPorNroCamiseta();
                break;
            case 8:
                this.verDetallesEquipoOrdenadoPorPosicionYNroCamiseta();
                break;
            case 9:
                this.exportarListaDeJugadoresArchivoTxt();
                break;
            case 10:
                this.exportarListaEquiposOrdenadosPorNombreJugadorArchivoTxt();
                break;
            case 11:
                this.exportarListaEquiposOrdenadosPorNroCamisetaArchivoTxt();
                break;
            case 12:
                this.exportarListaEquiposOrdenadosPorPosicionYNroCamisetaArchivoTxt();
                break;
        }
    }

    private void borrarEquipo() {
        System.out.println("Eliminar un equipo dado su nombre.");
        new ServicioEquipoImpl().borrarEquipo();
        System.out.println("-----------------------------");
    }

    private void exportarListaEquiposOrdenadosPorPosicionYNroCamisetaArchivoTxt() {
        System.out.println("Exportar lista de equipos ordenados por posición y número de camiseta a un archivo txt.");
        new ServicioListadoEquipoImpl().exportarTodosLosEquiposOrdenadosPorNombreJugador(false,
                false, true);
    }

    private void exportarListaEquiposOrdenadosPorNroCamisetaArchivoTxt() {
        System.out.println("Exportar lista de equipos ordenados por número de camiseta a un archivo txt.");
        new ServicioListadoEquipoImpl().exportarTodosLosEquiposOrdenadosPorNombreJugador(false,
                true, false);
    }

    private void exportarListaEquiposOrdenadosPorNombreJugadorArchivoTxt() {
        System.out.println("Exportar lista de equipos ordenados por nombre de jugador a un archivo txt.");
        new ServicioListadoEquipoImpl().exportarTodosLosEquiposOrdenadosPorNombreJugador(true,
                false, false);
    }


    private void exportarListaDeJugadoresArchivoTxt() {
        System.out.println("Exportar la lista de jugadores a un archivo 'txt'.");
        new ServicioListadoEquipoImpl().exportarListaDeJugadores();
        System.out.println("-----------------------------");
    }


    private void crearEquipo() {
        System.out.println("Crear un equipo, sus jugadores y entrenador.");
        ServicioEquipo servicioEquipo = new ServicioEquipoImpl();
        servicioEquipo.crearEquipo();
        System.out.println("-----------------------------");
    }

    private void buscarJugadorPorNombre() {
        System.out.println("Buscar un jugador por su nombre, donde se muestra: + \n +" +
                "su nombre, apellido, posición, si es capitán o no y el nombre de su equipo.");
        ServicioJugador servicioJugador = new ServicioJugadorImpl();
        servicioJugador.buscarJugadorPorNombre();
        System.out.println("-----------------------------");
    }


    private void buscarEquipo() {
        System.out.println("Buscar un equipo por su nombre, donde se muestra: + \n" +
                "su nombre, nombre de entrenador y nombre del capitán del equipo.");
        new ServicioListadoEquipoImpl().listarNombreMasEntrenadorYJugadores();
        System.out.println("-----------------------------");
    }

    private void mostrarDetallesEquipo() {
        System.out.println("Buscar un equipo por su nombre, donde se muestre: + \n" +
                "su nombre, nombre del entrenador y la lista de los jugadores del equipo.");
        new ServicioListadoEquipoImpl().listarNombreMasEntrenadorYJugadores();
        System.out.println("-----------------------------");
    }


    private void verDetallesEquipoOrdenadoPorPosicionYNroCamiseta() {
        System.out.println("Buscar un equipo por su nombre, donde se muestra: + \n" +
                "su nombre, nombre del entrenador y la lista de los jugadores del equipo ordenados por + \n " +
                "posición y número de camiseta.");
        new ServicioListadoEquipoImpl().listarNombreMasEntrenadorYJugadoresOrdenadosPorPosicionYNroCamiseta();
        System.out.println("-----------------------------");
    }

    private void verDetallesEquipoOrdenadoPorNroCamiseta() {
        System.out.println("Buscar un equipo por su nombre, donde se muestra: + \n" +
                "su nombre, nombre del entrenador y la lista de los jugadores del equipo ordenados por número de camiseta.");
        new ServicioListadoEquipoImpl().listarNombreMasEntrenadorYJugadoresOrdenadosPorNroCamiseta();
        System.out.println("-----------------------------");
    }

    private void mostrarDetallesEquipoOrdenadoPorNombreJugador() {

        System.out.println("Buscar un equipo por su nombre, donde se muestra: + \n" +
                "su nombre, nombre del entrenador y la lista de los jugadores del equipo ordenados por su nombre.");
        new ServicioListadoEquipoImpl().listarNombreMasEntrenadorYJugadoresOrdenadosPorNombreJugador();
        System.out.println("-----------------------------");
    }


    public void gestionarMenu(){
        int opcionSeleccionada = -1;
        this.mostrarTitulo();

        do {
            opcionSeleccionada = this.getOpcion();
        } while (opcionSeleccionada < 0 || opcionSeleccionada > 12);

        if (opcionSeleccionada != 0) {
            this.ejecutarOpcion(opcionSeleccionada);
            this.gestionarMenu();
        } else {
            System.out.println("Hasta luego.");
        }

    }
}
