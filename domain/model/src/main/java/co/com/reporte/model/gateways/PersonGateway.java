package co.com.reporte.model.gateways;

import co.com.reporte.model.person.PersonDomainResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonGateway {

  Mono<Long> getTotalPeopleByIdBootcamp(String idBootcamp);

  Flux<PersonDomainResponse> getAllPeopleByIdBootcamp(String idBootcamp);
}
