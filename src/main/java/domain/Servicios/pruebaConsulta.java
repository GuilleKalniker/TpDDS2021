package domain.Servicios;


import domain.Servicios.ClasesParaLaConsulta.ListadoHogaresTransito;

import java.io.IOException;

public class pruebaConsulta {

    public static void main(String[] args) throws IOException {
        System.out.println("adios mundo :C");

       ListadoHogaresTransito lista =  ServicioHogaresTransito.instancia().listaHogaresTransito();
       System.out.println(lista.total);
    }
}
