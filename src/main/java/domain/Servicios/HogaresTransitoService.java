package domain.Servicios;

import domain.Servicios.ClasesParaLaConsulta.ListadoHogaresTransito;
import domain.Servicios.ClasesParaLaConsulta.UsuariosRequest;
import domain.Servicios.ClasesParaLaConsulta.UsuariosResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;

public interface HogaresTransitoService {
  @POST("usuarios")
  Call<UsuariosResponse> postUsuarios(@Body HashMap<String, String> parameters);

  @GET("hogares")
  Call<ListadoHogaresTransito> getHogares(@Header("Authorization") String token, @Query("offset") int offset);

  @POST("usuarios")
  Call<UsuariosResponse> getToken(@Body UsuariosRequest request);
}