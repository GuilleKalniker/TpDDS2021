package controllers;

import domain.Persona.Usuario;
import domain.Repositorio.RepositorioUsuarios;
import spark.Request;

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
}
