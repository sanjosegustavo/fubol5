package org.informatorio.servicio.equipo;

public interface ServicioListadoEquipo {

    void listarNombreMasEntrenadorYCapitan();

    void listarDatosEquipo(boolean esOrdenadoPorNombre, boolean esOrdenadoPorNroCamiseta,
                           boolean esOrdenadoPorPosicionYNroCamiseta);

    void exportarListaDeJugadores();

    void exportarEquiposOrdenados(boolean esOrdenadoPorNombre, boolean esOrdenadoPorNroCamiseta,
                                  boolean esOrdenadoPorPosicionYNroCamiseta);

}
