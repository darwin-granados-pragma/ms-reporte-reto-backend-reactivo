package co.com.reporte.model.gateways;

import co.com.reporte.model.capacity.CapacityDomainResponse;
import co.com.reporte.model.capacity.CapacityTechnologyTotal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapacityGateway {

  Mono<CapacityTechnologyTotal> getCapacityTechnologyTotalByIdBootcamp(String idBootcamp);
  Flux<CapacityDomainResponse> getCapacitiesByIdBootcamp(String idBootcamp);
}
