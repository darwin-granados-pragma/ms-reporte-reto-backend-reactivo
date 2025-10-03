package co.com.reporte.model.capacity;

import co.com.reporte.model.capacity.technology.TechnologyDomainResponse;
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
public class CapacityDomainResponse {

  private String id;
  private String name;
  private List<TechnologyDomainResponse> technologies;
}
