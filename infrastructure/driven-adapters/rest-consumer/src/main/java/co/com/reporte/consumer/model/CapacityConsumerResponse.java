package co.com.reporte.consumer.model;

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
public class CapacityConsumerResponse {

  private String id;
  private String name;
  private List<TechnologyConsumerResponse> technologies;
}
