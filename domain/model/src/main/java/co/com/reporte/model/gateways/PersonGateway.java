package co.com.reporte.model.gateways;

import reactor.core.publisher.Mono;

public interface PersonGateway {

  Mono<Long> getTotalPeopleByIdBootcamp(String idBootcamp);
}
