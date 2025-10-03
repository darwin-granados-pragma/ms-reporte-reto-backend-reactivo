package co.com.reporte.api.model.response;

import java.time.LocalDate;
import java.util.List;
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
public class BootcampRestResponse {

  private String idBootcamp;
  private String name;
  private String description;
  private LocalDate releaseDate;
  private Integer duration;
  private List<PersonRestResponse> people;
  private List<CapacityRestResponse> capacities;
}
