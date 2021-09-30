package domain.Repositorio;

import domain.Exceptions.NoExisteCentroException;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Pregunta.Pregunta;
import domain.Publicacion.PublicacionAdopcion;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Sistema.CentroDeRescate;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

public class RepositorioCentroDeRescate {
  private static final RepositorioCentroDeRescate INSTANCE = new RepositorioCentroDeRescate();

  public static RepositorioCentroDeRescate getInstance() {
    return INSTANCE;
  }


  public void registrarCentroDeRescate(CentroDeRescate centroDeRescate) {
    AdapterJPA.persist(centroDeRescate);
  }

  public List<CentroDeRescate> getCentrosDeRescateRegistrados() {
    TypedQuery<CentroDeRescate> query = AdapterJPA.entityManager().createQuery("select c from CentroDeRescate c", CentroDeRescate.class);
    return query.getResultList();
  }

  public CentroDeRescate getCentro(long id) {
    return AdapterJPA.entityManager().find(CentroDeRescate.class, id);
  }

  //Esto podría hacerse con un where en la query, pero capaz es menos performante. Total no van a haber demasiados centros.
  public CentroDeRescate getCentroDeRescateMasCercanoA(Ubicacion ubicacion) {
    return getCentrosDeRescateRegistrados().stream()
        .sorted(Comparator.comparing(centro -> centro.getUbicacion().calcularDistanciaA(ubicacion)))
        .findFirst().orElseThrow(() -> new NoExisteCentroException());
  }

  public void generarPublicacion(FormularioMascotaPerdida formulario) {
    CentroDeRescate centroMasCercano = getCentroDeRescateMasCercanoA(formulario.getLugarEncuentro());
    AdapterJPA.persist(centroMasCercano.generarSolicitud(new PublicacionMascotaPerdida(formulario)));
  }

  public void registrarPublicacionAdopcion(PublicacionAdopcion publicacion){
    AdapterJPA.persist(publicacion);
  }
}
