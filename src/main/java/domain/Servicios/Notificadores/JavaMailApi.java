package domain.Servicios.Notificadores;

import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Servicios.Notificadores.Mail.Mensaje;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailApi implements Notificador{

  private String correo_envio = "centrodemascotasdds@gmail.com";
  private String contraseña = "tpdds2021";
  private Properties props;
  private Session session;

  public JavaMailApi() {

    this.props = new Properties();
    props.setProperty("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.smtp.port","587");
    props.setProperty("mail.smtp.user", this.correo_envio);
    props.setProperty("mail.smtp.auth", "true");

    this.session = Session.getDefaultInstance(this.props);
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

  private void agregarDestinatarioAlMensaje(MimeMessage message, String correo) {
    try {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
    } catch (Exception e) {
      throw new RuntimeException("no se pudo agreger el destinatario al mensaje");
    }
  }

  /**VISION DE OBSERVER**/

  public void notificar(Mensaje mensaje){
    MimeMessage message = armarMail(mensaje);

    try {
      Transport t = session.getTransport("smtp");;
      t.connect(this.correo_envio, this.contraseña);
      t.sendMessage(message, message.getAllRecipients());
      t.close();

    } catch (NoSuchProviderException e) {
      e.printStackTrace();
      throw new RuntimeException("no se pudo armar al Transport");
    } catch (MessagingException e) {
      e.printStackTrace();
      throw new RuntimeException("no se pudo armar al Transport");
    }
  }

  public MimeMessage armarMail(Mensaje mensaje){
    try {
      MimeMessage message = new MimeMessage(this.session);
      message.setFrom(new InternetAddress(this.correo_envio));

      this.agregarDestinatarioAlMensaje(message, mensaje.getContacto().getEmail());
      message.setSubject(mensaje.getAsunto());
      message.setText(mensaje.getTexto());
      return message;
    }
    catch (Exception e){
      throw new RuntimeException("No se pudo armar el mensaje.");
    }
  }
}


