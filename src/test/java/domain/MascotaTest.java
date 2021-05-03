package domain;

import domain.Mascota.Foto;
import domain.Mascota.Mascota;
import domain.Mascota.Sexo;
import domain.Mascota.TipoMascota;
import domain.Persona.UsuarioDuenio;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MascotaTest {

  private UsuarioDuenio crearUsuario(){
    //TODO
    return new UsuarioDuenio();
  }
  private Mascota mascotaNueva() {
    Foto fotoUno = new Foto("https://i.pinimg.com/originals/a2/cf/c4/a2cfc463c9e624185615b8eaca7d9aad.jpg");
    ArrayList<Foto> fotos = new ArrayList<>();
    fotos.add(fotoUno);
    Mascota mascota = new Mascota("Pukuru","Puku",7,
                                  Sexo.MASCULINO, TipoMascota.GATO,"",
                                  fotos, this.crearUsuario());
    return mascota;
  }
}
