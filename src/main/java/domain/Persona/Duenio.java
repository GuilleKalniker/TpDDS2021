package domain.Persona;

import domain.Mascota.Foto;
import domain.Mascota.MascotaRegistrada;
import domain.Mascota.Sexo;
import domain.Mascota.TipoMascota;
import domain.Sistema.CentroDeRescate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Duenio extends Usuario{
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;
  private TipoDocumento tipoDocumento;
  private Integer nroDocumento;
  private List<Contacto> contactos;
  private List<MascotaRegistrada> mascotas = new ArrayList<>();

  public Duenio(String usuario, String contrasenia, String nombre, String apellido, LocalDate fechaNacimiento, TipoDocumento tipoDocumento, Integer nroDocumento, List<Contacto> contactos) throws Exception {
    this.nombreDeUsuario = usuario;
    this.contrasenia = contrasenia;

    this.registrarse();

    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.contactos = contactos;
    if (contactos.isEmpty()) {
      throw new Exception();
    }
  }



  /*
  * registrarMascota(7)
  * Consigue del centro un QR nuevo para la mascota que se va a registrar, luego crea el objeto mascota con todos los parametros de la funcion + this, para que mascota conozca a su duenio
  * Posteriormente agrega a la mascota recien registrada a la lista de mascotas del duenio y del centro de rescate.
  */
  public void registrarMascota(TipoMascota unTipo, String unNombre, String unApodo, Integer unaEdad, Sexo unSexo, String unaDescripcion, ArrayList<Foto> unasFotos) {
    Integer QRMascota = CentroDeRescate.getInstance().otorgarQR();
    MascotaRegistrada mascota = new MascotaRegistrada(unTipo, unNombre, unApodo, unaEdad, unSexo, unaDescripcion, unasFotos, QRMascota, this);
    this.mascotas.add(mascota);
    CentroDeRescate.getInstance().agregarMascotaRegistrada(mascota);
  }

  public List<MascotaRegistrada> getMascotas() {
    return mascotas;
  }

  /*
  * seEncontro(1)
  * Notifica al duenio de que se encontro una de sus mascotas (tal vez deberia verificarse que mascota pertenezca al duenio)
  */
  public void seEncontro(MascotaRegistrada unaMascota) {
    //TODO Comportamiento no definido, se hace "notificacion".
    System.out.println("Se encontro a " + unaMascota.getNombre());
  }

}
