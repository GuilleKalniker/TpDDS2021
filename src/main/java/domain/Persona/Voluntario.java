package domain.Persona;

import domain.Mascota.PublicacionMascotaPerdida;

public class Voluntario extends Usuario{
  CentroDeRescate centroDesignado;

  Voluntario(String nombreDeUsuario, String contrasenia, CentroDeRescate centroDesignado) {
    super(nombreDeUsuario, contrasenia);
    this.centroDesignado = centroDesignado;
  }

  void aprobarPublicacion(PublicacionMascotaPerdida unaPublicacion) {
    // TODO
  }
}
