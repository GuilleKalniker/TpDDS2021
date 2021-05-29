package domain.Servicios;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Servicios.ClasesParaLaConsulta.ListadoHogaresTransito;
import domain.Servicios.ClasesParaLaConsulta.Request;
import domain.Servicios.ClasesParaLaConsulta.UsuariosResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioHogaresTransito {

  private static ServicioHogaresTransito instancia = null;
  private static int maximaCantidadRegistrosDefault = 200;
  private static final String urlApi = "https://api.refugiosdds.com.ar/api/";
  private Retrofit retrofit;

  private String token;

  private ServicioHogaresTransito() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    this.token = getToken("src/lista_contraseñas_no_seguras/bearer_token.json");
  }

  public static ServicioHogaresTransito instancia() {
    if(instancia== null) {
      instancia = new ServicioHogaresTransito();
    }
    return instancia;
  }

  public List<HogarTransito> solicitarTodosLosHogares() {
    ListadoHogaresTransito listado = this.listaHogaresTransito(1);
    List<HogarTransito> listaHogares = new ArrayList<>();

    for(Integer i = 1; i <= Math.ceil(listado.total / 10); i++){
      listaHogares.addAll(listaHogaresTransito(i).hogares);
    }

    return listaHogares;
  }

  //funciona  probarlo aqui domain.Servicios.pruebaConsulta
  //cada consulta solo trae un archivo con 10 hogares de 40, habria que ver la manera de verificar de habernos traido todos
  public ListadoHogaresTransito listaHogaresTransito(Integer numeroDePagina) {
    try {
      HogaresTransitoService refugiosService = this.retrofit.create(HogaresTransitoService.class);
      Call<ListadoHogaresTransito> requestHogares = refugiosService.getHogares("Bearer " + this.token, numeroDePagina);
      Response<ListadoHogaresTransito> responseHogaresTransito = requestHogares.execute();
      return responseHogaresTransito.body();
    }
    catch (Exception e) {
      throw new RuntimeException("No se pudo realizar la consulta");
    }
  }

  private String requestToken(String email) {
    try {
      HogaresTransitoService refugiosService = this.retrofit.create(HogaresTransitoService.class);
      Call<UsuariosResponse> requestToken = refugiosService.getToken(new Request("Saitteld1987@fleckens.hu"));
      Response<UsuariosResponse> responseTokenUsuarios = requestToken.execute();
      return responseTokenUsuarios.body().getToken(); //aca podria devolver directamente el objeto UsuarioResponse y mapearlo con el Gson para convertirlo en un archivo
    }
    catch (Exception e){
      throw new RuntimeException("No se pudo realizar la consulta");
    }
  }

  // tal vez crear funciones para hacer una consulta obtener un token y crear un .JSON
  // tal vez una funcion para actualizar el .JSON
  // no se que mas, tengpo sueño :C
  private String getToken(String path) {
    try {
      File bearerTokenFile = new File(path);
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new FileReader(bearerTokenFile));
      UsuariosResponse token = gson.fromJson(reader, UsuariosResponse.class);
      //TODO tal vez antes de devolverlo crear un archivo .JSON y guardar el token ahi
      return token.getToken();
    } catch (Exception e) {
      //realizamos una consulta para obtener un id?:P
      throw new RuntimeException("No se encontro el archivo");
    }
  }
}
