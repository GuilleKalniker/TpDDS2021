package domain.Servicios;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;

public interface HogaresTransitoService {
  @POST("usuarios")
  Call<UsuariosResponse> postUsuarios(@Body HashMap<String, String> parameters);

  @GET("hogares")
  Call<ListadoHogaresTransito> getHogares(@Header("bearer") String token, @Query("offset") int offset);
}