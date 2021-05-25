package domain.Servicios;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;

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
  }

  public static ServicioHogaresTransito instancia(){
    if(instancia== null){
      instancia = new ServicioHogaresTransito();
    }
    return instancia;
  }

  public UsuariosResponse tokenUsuario() throws IOException {
    HogaresTransitoService refugiosService = this.retrofit.create(HogaresTransitoService.class);
    HashMap<String, String> params = new HashMap<>();
    params.put("email", "msisro@frba.utn.edu.ar");
    Call<UsuariosResponse> requestToken = refugiosService.postUsuarios(params);
    Response<UsuariosResponse> responseTokenUsuarios = requestToken.execute();
    return responseTokenUsuarios.body();
  }

  public ListadoHogaresTransito listaHogaresTransito() throws IOException {
    HogaresTransitoService refugiosService = this.retrofit.create(HogaresTransitoService.class);
    Call<ListadoHogaresTransito> requestHogares = refugiosService.getHogares(this.token, 1);
    Response<ListadoHogaresTransito> responseHogaresTransito = requestHogares.execute();
    return responseHogaresTransito.body();
  }
}
