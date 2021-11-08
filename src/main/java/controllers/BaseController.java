package controllers;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Persona.Usuario;
import domain.Repositorio.RepositorioUsuarios;
import spark.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    protected Map<String, Object> diccionario = new HashMap<>();

    public void setModelo(Object value) {
        diccionario.put("modelo", value);
    }

    public void set(String key, Object value) {
        diccionario.put(key, value);
    }

    public void setUsuarioLogueado(Request req) {
        Usuario usuario = RepositorioUsuarios.getInstance().getUsuarioPorNombre(req.cookie("usuario_logueado"));
        System.out.println("Usuario logueado: " + usuario==null?usuario.getNombreUsuario():"undefined");
        diccionario.put("usuario_logueado", usuario);
    }

    public Map<String, Object> getDiccionario() {
        return diccionario;
    }

    //--------------utils-----------------

    protected LocalDate stringToLocalDate(String s) {
        LocalDate fecha;
        try{
            fecha = LocalDate.parse(s);
        }catch(DateTimeParseException e){
            fecha = null;
        }
        return fecha;
    }

    protected TipoDocumento stringToTipoDocumento(String s) {
        return Arrays.stream(TipoDocumento.values()).filter(tipo -> tipo.name().equals(s)).findFirst().get();
    }

    protected TipoMascota stringToTipoMascota(String s) {
        return Arrays.stream(TipoMascota.values()).filter(tipo -> tipo.name().equals(s)).findFirst().get();
    }

    protected Sexo stringToSexo(String s) {
        return Arrays.stream(Sexo.values()).filter(tipo -> tipo.name().equals(s)).findFirst().get();
    }

    protected Caracteristica stringToCaracteristica(String s) {
        return Arrays.stream(Caracteristica.values()).filter(tipo -> tipo.name().equals(s)).findFirst().get();
    }

    protected String generatePath() {
        LocalDateTime now = LocalDateTime.now();
        String nowString = String.valueOf(now.getYear())
                + String.valueOf(now.getMonthValue())
                + String.valueOf(now.getDayOfMonth())
                + "-"
                + String.valueOf(now.getHour())
                + String.valueOf(now.getMinute())
                + String.valueOf(now.getSecond())
                + String.valueOf(now.getNano());
        return nowString;
    }

    protected String generateProfilePath(Duenio duenio) {
        return String.valueOf(duenio.getId());
    }

    protected String getPublicPath() {
        return "src/main/resources/public/";
    }

    protected String getFormat(String s) {
        return s.substring(s.indexOf('.'));
    }
}
