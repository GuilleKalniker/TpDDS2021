package domain.Persona;

import domain.Mascota.Foto;
import domain.Mascota.MascotaRegistrada;
import domain.Mascota.Sexo;
import domain.Mascota.TipoMascota;
import domain.Sistema.CentroDeRescate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Duenio {
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;
  private TipoDocumento tipoDocumento;
  private Integer nroDocumento;
  private List<Contacto> contacto;
  private List<MascotaRegistrada> mascotas = new ArrayList<>(); // Deberia ser Set pero tendriamos que codearlo, Set de Java es Abstract

  //TODO
  private String usuario;
  private String contrasenia;

  public Duenio(String nombre, String apellido, LocalDate fechaNacimiento, TipoDocumento tipoDocumento, Integer nroDocumento, List<Contacto> contacto) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.contacto = contacto;
  }

  public void registrarMascota(TipoMascota unTipo, String unNombre, String unApodo, Integer unaEdad, Sexo unSexo, String unaDescripcion, ArrayList<Foto> unasFotos) {
    Integer QRMascota = CentroDeRescate.getInstance().otorgarQR();
    MascotaRegistrada mascota = new MascotaRegistrada(unTipo, unNombre, unApodo, unaEdad, unSexo, unaDescripcion, unasFotos, QRMascota, this);
    this.mascotas.add(mascota);
    CentroDeRescate.getInstance().agregarMascotaRegistrada(mascota);
  }

  public void seEncontro(MascotaRegistrada unaMascota) {
    //TODO que esto haga algo, no se aclara que pasa cuando se notifica
  }

}
