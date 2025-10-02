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
public class Report {

  private String id;
  private String idBootcamp;
  private String name;
  private String description;
  private String releaseDate;
  private Integer duration;
  private Long totalCapacities;
  private Long totalTechnologies;
  private Long totalPeople;
}
