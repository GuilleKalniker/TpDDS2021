package domain.Servicios.Notificadores;

import domain.Mascota.DatosMascotaPerdida;
import domain.Persona.Duenio;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailApi implements Notificador {

  private String correoEnvio;
  private String contraseña;
  private Properties props;
  private Session session;

  public JavaMailApi(String correoEnvio, String contraseña) {
    this.correoEnvio = correoEnvio;
    this.contraseña = contraseña;

    this.props = new Properties();
    props.setProperty("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.smtp.port","587");
    props.setProperty("mail.smtp.user", this.correoEnvio);
    props.setProperty("mail.smtp.auth", "true");

    this.session = Session.getDefaultInstance(this.props);
  }

  public void notificar(Duenio duenio, DatosMascotaPerdida datosMascotaPerdida) {
    MimeMessage message = armarMensaje(duenio, datosMascotaPerdida);

    try {
      Transport t = session.getTransport("smtp");;
      t.connect(this.correoEnvio, this.contraseña);
      t.sendMessage(message, message.getAllRecipients());
      t.close();
    }
    catch (Exception e) {
      throw new RuntimeException("No se pudo armar al Transport");
    }
  }

  public MimeMessage armarMensaje(Duenio duenio, DatosMascotaPerdida datosMascotaPerdida){
    try {
      MimeMessage message = new MimeMessage(this.session);
      message.setFrom(new InternetAddress(this.correoEnvio));

      duenio.getDatosPersonales().getContactos().stream()
          .forEach(contacto -> this.agregarDestinatarioAlMensaje(message, contacto.getEmail()));

      message.setSubject("Hola " + duenio.getDatosPersonales().getNombre() + duenio.getDatosPersonales().getApellido()+ " entontraron a tu mascota perdida :D!!!");
      message.setText( "holaaa:D");

      return message;
    }
    catch (Exception e){
      throw new RuntimeException("no se pudo armar el mensaje");
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
