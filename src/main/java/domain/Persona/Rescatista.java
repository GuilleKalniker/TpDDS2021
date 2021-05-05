package domain.Persona;

import com.sun.istack.internal.NotNull;
import domain.Mascota.EstadoMascotaPerdida;
import domain.Mascota.Foto;
import domain.Sistema.CentroDeRescate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Rescatista {
  private String nombre;
  private String apellido;
  private LocalDate fechaDeNacimiento;
  private TipoDocumento tipoDocumento;
  private Integer nroDocumento;
  private String direccion;
  private List<Contacto> contactos;

  public Rescatista(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDocumento, Integer nroDocumento, String direccion, @NotNull List<Contacto> contactos) throws Exception {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.direccion = direccion;

    if (contactos.isEmpty()) {
      throw new Exception();
    }

    this.contactos = contactos;
  }

  /*
  * notificarMascotaEncontrada(5)
  * Valida el QR a traves del centro, quien devuelve una excepcion en caso de que este no se encuentre en el listado.
  * Si es invalido, se notifica.
  * Si es valido, se crea un EstadoMascotaPerdida a partir de los parametros de la funcion, y se lo agrega a la lista de estados del centro.
  * Tambien solicita que se notifique la mascota al duenio, funcionalidad que puede ser erronea al ser inmediata.
  */
  public void notificarMascotaEncontrada(String unaDescripcion, List<Foto> unasFotos, String unLugar, LocalDate unaFecha, Integer unQR){
    try {
      CentroDeRescate.getInstance().validarQR(unQR);
      EstadoMascotaPerdida estadoMascota = new EstadoMascotaPerdida(this, unaDescripcion, unasFotos, unLugar, unaFecha, unQR);
      CentroDeRescate.getInstance().agregarEstadoMascotaPerdida(estadoMascota);
      // El enunciado dice que se avisa a las mascotas de los ultimos 10 dias y aca se estaria haciendo automaticamente:
      //CentroDeRescate.getInstance().notificarMascotaEncontrada(estadoMascota);
    }
    catch (Exception qrInvalido) {
      System.out.println("Se leyo un QR invalido.");
    }
  }
}
