package domain.Servicios;

//import com.google.gson.Gson;
//import com.google.gson.stream.JsonReader;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Repositorio.RepositorioMascotas;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Servicios.ClasesParaLaConsulta.ListadoHogaresTransito;
import domain.Servicios.ClasesParaLaConsulta.UsuariosRequest;
import domain.Servicios.ClasesParaLaConsulta.UsuariosResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioHogaresTransito {

  private static ServicioHogaresTransito instancia = null;
  private static int maximaCantidadRegistrosDefault = 200;
  private static final String urlApi = "https://api.refugiosdds.com.ar/api/";
  private Retrofit retrofit;

  private String token;

  private ServicioHogaresTransito() {
    /*this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    this.token = getToken("src/lista_contraseñas_no_seguras/bearer_token.json");*/
  }

  public static ServicioHogaresTransito getInstance() {
    if(instancia== null) {
      instancia = new ServicioHogaresTransito();
    }
    return instancia;
  }

  public List<HogarTransitoAdaptado> solicitarTodosLosHogares() {
    List<HogarTransito> listaHogares = new ArrayList<>();

    for(Integer i = 1; i <= Math.ceil(this.cantidadHogaresTotales() / 10); i++){
      listaHogares.addAll(listaHogaresTransito(i).hogares);
    }

    List<HogarTransitoAdaptado> listaHogaresAdaptados = listaHogares.stream().map(HogarTransito::adaptarHogar).collect(Collectors.toList());

    return listaHogaresAdaptados;
  }

  public ListadoHogaresTransito listaHogaresTransito(Integer numeroDePagina) {
    try {
      HogaresTransitoService refugiosService = this.retrofit.create(HogaresTransitoService.class);
      Call<ListadoHogaresTransito> requestHogares = refugiosService.getHogares("Bearer " + this.token, numeroDePagina);
      Response<ListadoHogaresTransito> responseHogaresTransito = requestHogares.execute();
      return responseHogaresTransito.body();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("No se pudo realizar la consulta");
    }
  }

  public Integer cantidadHogaresTotales() {
    return this.listaHogaresTransito(1).total;
  }

  private String requestToken(String email) {
    try {
      HogaresTransitoService refugiosService = this.retrofit.create(HogaresTransitoService.class);
      Call<UsuariosResponse> requestToken = refugiosService.getToken(new UsuariosRequest("Saitteld1987@fleckens.hu"));
      Response<UsuariosResponse> responseTokenUsuarios = requestToken.execute();
      return responseTokenUsuarios.body().getToken();
    }
    catch (Exception e) {
      throw new RuntimeException("No se pudo realizar la consulta");
    }
  }

  private String getToken(String path) {
    /*try {
      File bearerTokenFile = new File(path);
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new FileReader(bearerTokenFile));
      UsuariosResponse token = gson.fromJson(reader, UsuariosResponse.class);
      return token.getToken();
    } catch (Exception e) {
      throw new RuntimeException("No se encontro el archivo");
    }*/

    return "Cerro el server :(";
  }

  public List<HogarTransitoAdaptado> filtrarHogaresPara(FormularioMascotaPerdida formularioMascotaPerdida, Double radio){
    MascotaRegistrada mascota = RepositorioMascotas.getInstance().buscarMascotaPorID(formularioMascotaPerdida.getIDMascotaPerdida());

    return solicitarTodosLosHogares().stream()
        .filter(hogar -> hogar.esAdecuado(mascota, radio, formularioMascotaPerdida.getLugarEncuentro()))
        .collect(Collectors.toList());
  }
}
