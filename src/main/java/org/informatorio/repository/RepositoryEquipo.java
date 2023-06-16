package org.informatorio.repository;

import org.informatorio.dominio.Equipo;

public interface RepositoryEquipo {

    void guardarEquipo(Equipo equipo);

    void borrarEquipo(Equipo equipo);

    Equipo buscarEquipo(String nombre);
}
