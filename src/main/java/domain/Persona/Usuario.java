package domain.Persona;

import Funciones.ValidadorContrasenias;
import domain.Exceptions.ContraseniaInvalidaException;
import domain.Exceptions.UsuarioYaRegistradoException;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Repositorio.RepositorioUsuarios;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol")
public class Usuario {
    @Id
    @GeneratedValue
    private long id;

    protected String nombreUsuario;
    protected byte[] salt;
    protected byte[] contraseniaHasheada;

    public Usuario(String nombreUsuario, String contrasenia) {
        validarDatosRegistro(nombreUsuario, contrasenia);
        this.nombreUsuario = nombreUsuario;
        this.salt = ValidadorContrasenias.generarSalt();
        this.contraseniaHasheada = ValidadorContrasenias.passwordToHash(contrasenia, salt);
    }

    public Usuario() {}

    public byte[] getSalt() {
        return salt;
    };

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public byte[] getContraseniaHasheada() { return contraseniaHasheada; }

    public long getId() {
        return id;
    }

    public void validarDatosRegistro(String usuario, String contrasenia) {
        if (RepositorioUsuarios.getInstance().existeUsuario(usuario))
            throw new UsuarioYaRegistradoException("Ya existe un usuario con ese nombre de usuario.");
        if (!ValidadorContrasenias.esContraseniaValida(contrasenia))
            throw new ContraseniaInvalidaException("Contrasenia inválida.");
    }

    public Boolean matcheaContrasenia(String contrasenia) {

        return Arrays.equals(ValidadorContrasenias.passwordToHash(contrasenia, getSalt()), contraseniaHasheada);
    }

    public void registrarse() {
        RepositorioUsuarios.getInstance().registrarUsuario(this);
    }
}
