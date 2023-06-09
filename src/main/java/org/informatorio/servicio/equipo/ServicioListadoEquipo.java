package org.informatorio.servicio.equipo;

public interface ServicioListadoEquipo {

    void listarNombreMasEntrenadorYCapitan();

    void listarNombreMasEntrenadorYJugadores();

    void listarNombreMasEntrenadorYJugadoresOrdenadosPorNombreJugador();

    void listarNombreMasEntrenadorYJugadoresOrdenadosPorNroCamiseta();

    void listarNombreMasEntrenadorYJugadoresOrdenadosPorPosicionYNroCamiseta();

    void exportarListaDeJugadores();

    void exportarTodosLosEquiposOrdenadosPorNombreJugador(boolean esOrdenadoPorNombre, boolean esOrdenadoPorNroCamiseta,
                                                          boolean esOrdenadoPorPosicionYNroCamiseta);

    void exportarTodosLosEquiposOrdenadosPorNroCamiseta();

    void exportarTodosLosEquiposOrdenadosPorPosicionYNroCamiseta();

}
