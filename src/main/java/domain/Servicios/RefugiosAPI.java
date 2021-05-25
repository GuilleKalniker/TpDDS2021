package domain.Servicios;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;

public class RefugiosAPI {

  private static RefugiosAPI instancia = null;
  private static int maximaCantidadRegistrosDefault = 200;
  private static final String urlApi = "https://api.refugiosdds.com.ar/api/";
  private Retrofit retrofit;

  private RefugiosAPI() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RefugiosAPI instancia(){
    if(instancia== null){
      instancia = new RefugiosAPI();
    }
    return instancia;
  }

  public ListadoHogaresTransito listaHogaresTransito() {
    RefugiosAPI refugiosService = this.retrofit.create(RefugiosAPI.class);
    Call<ListadoHogaresTransito> requestHogares = refugiosService
  }
/*


  public ListadoDeProvincias listadoDeProvincias() throws IOException {
    RefugiosAPI georefService = this.retrofit.create(RefugiosAPI.class);
    Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias();
    Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();
  }

  public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
    RefugiosAPI georefService = this.retrofit.create(RefugiosAPI.class);
    Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
    Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }*/
}
