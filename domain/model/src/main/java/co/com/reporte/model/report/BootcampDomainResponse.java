package co.com.reporte.model.report;

import co.com.reporte.model.capacity.CapacityDomainResponse;
import co.com.reporte.model.person.PersonDomainResponse;
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
public class BootcampDomainResponse {

  private String idBootcamp;
  private String name;
  private String description;
  private LocalDate releaseDate;
  private Integer duration;
  private List<PersonDomainResponse> people;
  private List<CapacityDomainResponse> capacities;
}
