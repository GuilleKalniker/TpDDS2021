package domain.Publicacion;

import domain.Persona.Duenio;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;

public class PublicacionAdoptante {
  private Duenio duenioAsociado;
  private List<String> preferencias = new ArrayList<>();
  private List<PublicacionAdopcion> sugerenciasAdopcion = new ArrayList<>();
  private CentroDeRescate centroDeRescate;

  public PublicacionAdoptante(Duenio duenioAsociado, CentroDeRescate centroDeRescate) {
    this.duenioAsociado = duenioAsociado;
    this.centroDeRescate = centroDeRescate;
  }

  public List<String> getPreferencias() {
    return preferencias;
  }

  public void agregarPreferencia(String preferencia) {
    preferencias.add(preferencia);
  }

  public void quitarPreferencia(String preferencia) {
    preferencias.remove(preferencia);
  }

  public void recibirSugerenciaAdopcion(PublicacionAdopcion publicacionAdopcion) {
    sugerenciasAdopcion.add(publicacionAdopcion);
    duenioAsociado.notificarme("Nueva sugerencia semanal", "Aca tenes una mascota que podrias estar interesado en adoptar.");
  }

  public void revisarSugerenciasRecibidas() {
    // TODO: Implementar, ciclaria por las sugerenciasAdopcion (GUI)
    // Si le gusto alguna, centroDeRescate.publicacionAdopcionMatcheada(duenioAsociado.getDatosPersonales(), publicacion)
  }

  public void revisarPublicacionesCentro() {
    centroDeRescate.getPublicacionesAdopcion();
    // TODO: Revisarlas
    // Si le gusto alguna, centroDeRescate.publicacionAdopcionMatcheada(duenioAsociado.getDatosPersonales(), publicacion)
  }
}
