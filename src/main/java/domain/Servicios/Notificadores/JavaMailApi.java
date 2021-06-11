package domain.Servicios.Notificadores;

import domain.Mascota.FormularioMascotaPerdida;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.Duenio;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailApi implements Notificador{

  private String correo_envio;
  private String contraseña;
  private Properties props;
  private Session session;

  public JavaMailApi(String correo_envio, String contraseña) {
    this.correo_envio = correo_envio;
    this.contraseña = contraseña;

    this.props = new Properties();
    props.setProperty("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.smtp.port","587");
    props.setProperty("mail.smtp.user", this.correo_envio);
    props.setProperty("mail.smtp.auth", "true");

    this.session = Session.getDefaultInstance(this.props);
  }

  public void notificarDuenio(Duenio duenio, FormularioMascotaPerdida formularioMascotaPerdida) {
    MimeMessage message = armarMensajeMascotaEncontrada(duenio, formularioMascotaPerdida);

    try {
      Transport t = session.getTransport("smtp");;
      t.connect(this.correo_envio, this.contraseña);
      t.sendMessage(message, message.getAllRecipients());
      t.close();
    }
    catch (Exception e) {
      throw new RuntimeException("no se pudo armar al Transport");
    }
  }

  public void notificarRescatista(DatosPersonales datosRescatista, DatosPersonales datosDuenio) {
    MimeMessage message = armarMensajeRescatista(datosRescatista, datosDuenio);

    try {
      Transport t = session.getTransport("smtp");;
      t.connect(this.correo_envio, this.contraseña);
      t.sendMessage(message, message.getAllRecipients());
      t.close();
    }
    catch (Exception e) {
      throw new RuntimeException("no se pudo armar al Transport");
    }
  }



  public MimeMessage armarMensajeRescatista(DatosPersonales datosRescatista, DatosPersonales datosDuenio){
    try {
      MimeMessage message = new MimeMessage(this.session);
      message.setFrom(new InternetAddress(this.correo_envio));

      datosRescatista.getContactos().stream()
          .forEach(contacto -> this.agregarDestinatarioAlMensaje(message, contacto.getEmail()));

      message.setSubject("Apareció un dueño!");
      message.setText("Estimado " + datosRescatista.getNombre() + " " + datosRescatista.getApellido() + ", "
          + datosDuenio.getNombre() + " " + datosDuenio.getApellido() + " ha encontrado a su mascota en una de tus " +
          "publicaciones. \nContacto: " + datosDuenio.getContactos().toString());

      return message;
    }

    catch (Exception e){
      throw new RuntimeException("No se pudo armar el mensaje.");
    }
  }

  public MimeMessage armarMensajeMascotaEncontrada(Duenio duenio, FormularioMascotaPerdida formularioMascotaPerdida){
    try {
      MimeMessage message = new MimeMessage(this.session);
      message.setFrom(new InternetAddress(this.correo_envio));

      duenio.getDatosPersonales().getContactos().stream()
          .forEach(contacto -> this.agregarDestinatarioAlMensaje(message, contacto.getEmail()));

      message.setSubject("Hola " + duenio.getDatosPersonales().getNombre() + duenio.getDatosPersonales().getApellido()+ " encontraron a tu mascota perdida :D!!!");
      message.setText( "Hola, nos comunicamos para informarte que tu mascota ha sido encontrada.");

      return message;
    }

    catch (Exception e){
      throw new RuntimeException("No se pudo armar el mensaje.");
    }
  }

  private void agregarDestinatarioAlMensaje(MimeMessage message, String correo) {
    try {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
    } catch (Exception e) {
      throw new RuntimeException("no se pudo agreger el destinatario al mensaje");
    }
  }

}
