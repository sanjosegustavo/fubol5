package org.informatorio.servicio.entrada.impl;

import org.informatorio.dominio.Equipo;
import org.informatorio.dominio.Jugador;
import org.informatorio.dominio.Posicion;
import org.informatorio.servicio.entrada.ServicioEntradaArchivo;

import org.apache.commons.io.FileUtils;
import org.informatorio.servicio.equipo.impl.ServicioEquipoImpl;
import org.informatorio.servicio.validacion.ServicioValidacion;
import org.informatorio.servicio.validacion.impl.ServicioValidacionImpl;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ServicioEntradaArchivoImpl implements ServicioEntradaArchivo {

    @Override
    public List<Jugador> cargarJugadoresDesdeArchivo(String rutaArchivo) {
        List<Jugador> jugadores = new ArrayList<>();
        ServicioValidacion servicioValidacionImpl = new ServicioValidacionImpl();

        try{
            List<String> lineas = FileUtils.readLines(new File(rutaArchivo), StandardCharsets.UTF_8);

            for (String linea: lineas) {
                String[] partes = linea.split(";");

                String nombre = partes[0];
                String apellido = partes[1];
                String altura = partes[2];
                String goles = partes[3];
                String posicion = partes[4];
                String esCapitan = partes[5];
                String nroCamiseta = partes[6];

                double doubleAltura = Double.parseDouble(altura);
                int intGoles  = Integer.parseInt(goles);
                Posicion posicionObject = new Posicion(posicion);
                int intNroCamiseta = Integer.parseInt(nroCamiseta);

                // control capitan
                boolean booleanEsCapitan = esCapitan.equalsIgnoreCase("true") &&
                        !servicioValidacionImpl.hayCapitan(jugadores);

                Jugador jugador = new Jugador(nombre, apellido, doubleAltura, intGoles, posicionObject,
                        booleanEsCapitan, intNroCamiseta);
                jugadores.add(jugador);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException n){
            System.out.println("Hoal soy un error");
            throw new RuntimeException(n);
        }
        return jugadores;
    }
}
