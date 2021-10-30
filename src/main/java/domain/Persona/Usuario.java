package domain.Persona;

import Funciones.ValidadorContrasenias;
import domain.Exceptions.ContraseniaInvalidaException;
import domain.Exceptions.UsuarioYaRegistradoException;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Repositorio.RepositorioUsuarios;

import javax.persistence.*;


@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol")
public class Usuario {
    @Id
    @GeneratedValue
    private long id;

    protected String nombreUsuario;
    protected String salt;
    protected String contraseniaHasheada;

    public Usuario(String nombreUsuario, String contrasenia) {
        validarDatosRegistro(nombreUsuario, contrasenia);
        this.nombreUsuario = nombreUsuario;
        this.salt = ValidadorContrasenias.generarSalt();
        this.contraseniaHasheada = ValidadorContrasenias.passwordToHash(contrasenia, getSalt());
    }

    public Usuario() {}

    public String getSalt() {
        return salt;
    };

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseniaHasheada() { return contraseniaHasheada; }

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
        return ValidadorContrasenias.passwordToHash(contrasenia, getSalt()).equals(contraseniaHasheada);
    }

    public void registrarse() {
        RepositorioUsuarios.getInstance().registrarUsuario(this);
    }

}
