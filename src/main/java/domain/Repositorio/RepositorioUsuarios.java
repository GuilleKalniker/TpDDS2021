package domain.Repositorio;

import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.Duenio;
import domain.Persona.Usuario;

import javax.persistence.*;
import java.util.List;

public class RepositorioUsuarios {

  private static RepositorioUsuarios INSTANCE = null;

  public static RepositorioUsuarios getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new RepositorioUsuarios();
    }
    return INSTANCE;
  }

  public void registrarUsuario(Usuario usuario) {
    AdapterJPA.beginTransaction();
    AdapterJPA.persist(usuario);
    AdapterJPA.commit();
  }

  public void persistirContacto(Contacto contacto) {
    AdapterJPA.beginTransaction();
    AdapterJPA.persist(contacto);
    AdapterJPA.commit();
  }

  public List<Duenio> getDueniosRegistrados() {
    TypedQuery<Duenio> query = AdapterJPA.entityManager().createQuery("select d from Duenio d", Duenio.class);
    return query.getResultList();
  }

  /* TODO
  public Set<Administrador> getAdministradoresRegistrados() {
    return administradoresRegistrados.keySet();
  }

  public Set<Voluntario> getVoluntariosRegistrados() {
    return voluntariosRegistrados.keySet();
  }*/

  public Boolean existeUsuario(String nombreUsuario){
    TypedQuery<Usuario> query = AdapterJPA.entityManager().createQuery("select d from Usuario d where d.nombreUsuario = :username", Usuario.class);
    query.setParameter("username", nombreUsuario);
    return !query.getResultList().isEmpty();
  }

  public Usuario getUsuario(long id) {
    return AdapterJPA.entityManager().find(Usuario.class, id);
  }

  //TODO recibiria una mascota como parametro
  public Duenio getDuenioPorID(String ID) {
    return getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(ID))
        .findFirst().orElseThrow(() -> new IDNoSeCorrespondeException("No se encontro el duenio a partir del ID de la mascota"));
  }

  public Usuario getUsuarioPorNombre(String nombreUsuario) {
    Usuario u;
    try {
      TypedQuery<Usuario> query = AdapterJPA.entityManager().createQuery("select d from Usuario d where d.nombreUsuario = :username", Usuario.class);
      query.setParameter("username", nombreUsuario);
      u = query.getSingleResult();
    }
    catch(Exception e) {
      u = null;
    }
    return u;
  }
}
