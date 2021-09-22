package domain.Repositorio;

import Funciones.ValidadorContrasenias;
import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Exceptions.UsuarioYaRegistradoException;
import domain.Persona.Administrador;
import domain.Persona.Duenio;
import domain.Persona.Usuario;
import domain.Persona.Voluntario;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

  public void registrarDuenio(Duenio duenio) {
    AdapterJPA.beginTransaction();
    AdapterJPA.persist(duenio);
    AdapterJPA.commit();
  }

  public void registrarAdministrador(Administrador administrador) {
    AdapterJPA.beginTransaction();
    AdapterJPA.persist(administrador);
    AdapterJPA.commit();
  }

  public void registrarVoluntario(Voluntario voluntario) {
    AdapterJPA.beginTransaction();
    AdapterJPA.persist(voluntario);
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


  //TODO hacerlo apto para administradores y voluntarios. Propongo crearles una superclase abstracta Usuario.
  public Boolean existeUsuario(String nombreUsuario){
    return getDuenioPorUsuario(nombreUsuario) != null;
  }


  //TODO recibiria una mascota como parametro
  public Duenio getDuenioPorID(String ID) {
    return getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(ID))
        .findFirst().orElseThrow(() -> new IDNoSeCorrespondeException("No se encontro el duenio a partir del ID de la mascota"));
  }

  public Usuario getDuenioPorUsuario(String nombreUsuario) {
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
