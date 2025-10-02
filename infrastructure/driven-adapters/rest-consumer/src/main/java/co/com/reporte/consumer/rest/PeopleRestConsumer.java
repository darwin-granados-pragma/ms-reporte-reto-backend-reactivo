package co.com.reporte.consumer.rest;

import co.com.reporte.model.error.ErrorCode;
import co.com.reporte.model.exception.ObjectNotFoundException;
import co.com.reporte.model.gateways.PersonGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PeopleRestConsumer implements PersonGateway {

  private final WebClient client;

  public PeopleRestConsumer(@Qualifier("peopleWebClient") WebClient client) {
    this.client = client;
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
}
