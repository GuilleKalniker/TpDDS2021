package domain.Mascota;

// Al momento de encontrarse una mascota, el sistema creara la publicacion a la par de DatosMascotasPerdidas

import domain.Mascota.AtributosMascota.Foto;
import domain.Persona.AtributosPersona.Contacto;
import domain.Repositorio.RepositorioMascotas;

import java.util.ArrayList;
import java.util.List;

public class PublicacionMascotaPerdida {
  String nombreRescatista;
  String apellidoRescatista;
  Contacto contactoRescatista;
  List<Foto> fotosMascota = new ArrayList<>();
  CentroDeRescate centroMasCercano;

  PublicacionMascotaPerdida(DatosMascotaPerdida datosMascotaPerdida) {
    filtrarInformacionSensible(datosMascotaPerdida);
    encontrarCentroMasCercano(datosMascotaPerdida);
    RepositorioMascotas.getInstance().agregarPublicacionMascotaPerdida(this);
  }

  private void encontrarCentroMasCercano(DatosMascotaPerdida datosMascotaPerdida) {
    // TODO
  }

  private void filtrarInformacionSensible(DatosMascotaPerdida datosMascotaPerdida) {
    // TODO
  }

  CentroDeRescate getCentro() {
    return centroMasCercano;
  }
}
