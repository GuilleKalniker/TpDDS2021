package domain.Servicios.Notificadores.Mail;

import domain.Persona.AtributosPersona.Contacto;

public class Mensaje {

  private Contacto contacto;
  private String asunto;
  private String texto;

  public Mensaje(Contacto contacto, String asunto, String texto) {
    this.contacto = contacto;
    this.asunto = asunto;
    this.texto = texto;
  }

  public Contacto getContacto() {
    return contacto;
  }

  public String getAsunto() {
    return asunto;
  }

  public String getTexto() {
    return texto;
  }
}
