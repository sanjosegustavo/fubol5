package org.informatorio;

import org.informatorio.servicio.GestionMenu;
import org.informatorio.servicio.inicio.impl.ServicioInicioImpl;
import org.informatorio.servicio.entrada.impl.ServicioConsola;

public class App 
{
    public static void main( String[] args )
    {
        ServicioConsola.abrirScanner();
        new ServicioInicioImpl().iniciar();
        new GestionMenu().gestionarMenu();
        ServicioConsola.cerrarScanner();
    }
}


