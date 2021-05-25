import domain.Servicios.ServicioHogaresTransito;
import domain.Servicios.UsuariosResponse;

import java.io.IOException;

public class EjemploDeUso {
  public static void main(String[] args) throws IOException {
    ServicioHogaresTransito servicioHogaresTransito = ServicioHogaresTransito.instancia();

    UsuariosResponse usuariosResponse = servicioHogaresTransito.tokenUsuario();
    System.out.println(usuariosResponse);


  }


}
/*
public class EjemploDeUso {

    public static void main(String[] args) throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref.listadoDeProvincias();

        listadoDeProvinciasArgentinas.provincias.sort((p1, p2) -> p1.id >= p2.id? 1 : -1);

        for(Provincia unaProvincia:listadoDeProvinciasArgentinas.provincias){
            System.out.println(unaProvincia.id + ") " + unaProvincia.nombre);
        }

        Scanner entradaEscaner = new Scanner(System.in);
        int idProvinciaElegida = Integer.parseInt(entradaEscaner.nextLine());

        Optional<Provincia> posibleProvincia = listadoDeProvinciasArgentinas.provinciaDeId(idProvinciaElegida);

        if(posibleProvincia.isPresent()){
            Provincia provinciaSeleccionada = posibleProvincia.get();
            ListadoDeMunicipios municipiosDeLaProvincia = servicioGeoref.listadoDeMunicipiosDeProvincia(provinciaSeleccionada);
            System.out.println("Los municipios de la provincia "+ provinciaSeleccionada.nombre +" son:");
            for(Municipio unMunicipio: municipiosDeLaProvincia.municipios){
                System.out.println(unMunicipio.nombre);
            }
        }
        else{
            System.out.println("No existe la provincia seleccionada");
        }
    }
}

 */