package controllers;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Persona.Usuario;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import java.util.List;
import java.util.stream.Collectors;
import spark.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    protected Map<String, Object> diccionario = new HashMap<>();

    protected Boolean hayErrores = false;

    public void init(Request req) {
        diccionario = new HashMap<>();
        AdapterJPA.cleanCache();
        setUsuarioLogueado(req);
    }

    public void setModelo(Object value) {
        diccionario.put("modelo", value);
    }

    public void set(String key, Object value) {
        diccionario.put(key, value);
    }

    public void setUsuarioLogueado(Request req) {
        Usuario usuario = RepositorioUsuarios.getInstance().getUsuarioPorNombre(req.cookie("usuario_logueado"));
        diccionario.put("usuario_logueado", usuario);
    }

    public Map<String, Object> getDiccionario() {
        return diccionario;
    }

    public void setError(String error) {
        hayErrores = true;
        set(error, true);
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

    protected String getPublicPathImages() {
        return "src/main/resources/public/images/";
    }

    protected String getFormat(String s) {
        return s.substring(s.indexOf('.'));
    }

    protected List<Caracteristica> parsearCaracteristicas(Request req) {
        return RepositorioMascotas
                .getInstance()
                .getTodasLasCaracteristicas()
                .stream().filter(caracteristica -> req.queryParams(String.valueOf(caracteristica.getId())) != null)
                .collect(Collectors.toList());
    }

    protected void actualizarCaracteristicas(Request req) {
        RepositorioMascotas
                .getInstance()
                .getTodasLasCaracteristicas()
                .forEach(caracteristica -> actualizarCaracteristica(caracteristica, req));
    }

    private void actualizarCaracteristica(Caracteristica caracteristica, Request req) {
        String nuevoValor = req.queryParams(String.valueOf(caracteristica.getId()));
        caracteristica.setActivo(stringToBoolean(nuevoValor));
    }

    protected Boolean stringToBoolean(String string) {
        return string != null;
    }
}
