package co.com.reporte.consumer.rest;

import co.com.reporte.consumer.mapper.PersonConsumerMapper;
import co.com.reporte.consumer.model.PersonConsumerResponse;
import co.com.reporte.model.error.ErrorCode;
import co.com.reporte.model.exception.ObjectNotFoundException;
import co.com.reporte.model.gateways.PersonGateway;
import co.com.reporte.model.person.PersonDomainResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PeopleRestConsumer implements PersonGateway {

  private final WebClient client;
  private final PersonConsumerMapper mapper;

  public PeopleRestConsumer(@Qualifier("peopleWebClient") WebClient client,
      PersonConsumerMapper mapper) {
    this.client = client;
    this.mapper = mapper;
  }

  @Override
  public Mono<Long> getTotalPeopleByIdBootcamp(String idBootcamp) {
    return client
        .get()
        .uri("/api/v1/bootcamp/{idBootcamp}/count", idBootcamp)
        .retrieve()
        .bodyToMono(Long.class)
        .onErrorResume(WebClientResponseException.NotFound.class, ex -> {
              log.warn("Bootcamp not found by id={}. The endpoint returned 404.", idBootcamp);
              return Mono.error(new ObjectNotFoundException(ErrorCode.BOOTCAMP_NOT_FOUND, idBootcamp));
            }
        );
  }

  @Override
  public Flux<PersonDomainResponse> getAllPeopleByIdBootcamp(String idBootcamp) {
    return client
        .get()
        .uri("/api/v1/bootcamp/{idBootcamp}/personas", idBootcamp)
        .retrieve()
        .bodyToFlux(PersonConsumerResponse.class)
        .map(mapper::toPersonDomainResponse)
        .onErrorResume(WebClientResponseException.NotFound.class, ex -> {
              log.warn("People list not found for idBootcamp={}. The endpoint returned 404.",
                  idBootcamp
              );
              return Flux.empty();
            }
        );
  }
}
