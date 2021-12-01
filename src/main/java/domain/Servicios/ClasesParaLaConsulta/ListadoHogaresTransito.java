package domain.Servicios.ClasesParaLaConsulta;

import java.util.List;

public class ListadoHogaresTransito {
  public Integer total;
  public Integer offset;
  public List<HogarTransito> hogares;

  public Integer getTotal() {
    return total;
  }

  public Integer getOffset() {
    return offset;
  }

  public List<HogarTransito> getHogares() {
    return hogares;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public void setHogares(List<HogarTransito> hogares) {
    this.hogares = hogares;
  }
}
