package co.com.reporte.model.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReportCreate {

  private String idBootcamp;
  private String name;
  private String description;
  private String releaseDate;
  private Integer duration;
}
