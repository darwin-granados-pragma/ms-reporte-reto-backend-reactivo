package co.com.reporte.consumer.rest;

import co.com.reporte.consumer.mapper.CapacityMapper;
import co.com.reporte.consumer.model.CapacityRestResponse;
import co.com.reporte.model.capacity.CapacityTechnologyTotal;
import co.com.reporte.model.error.ErrorCode;
import co.com.reporte.model.exception.ObjectNotFoundException;
import co.com.reporte.model.gateways.CapacityGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CapacityRestConsumer implements CapacityGateway {


  private final WebClient client;
  private final CapacityMapper capacityMapper;

  public CapacityRestConsumer(@Qualifier("capacityWebClient") WebClient client,
      CapacityMapper capacityMapper) {
    this.client = client;
    this.capacityMapper = capacityMapper;
  }

  @Override
  public Mono<CapacityTechnologyTotal> getCapacityTechnologyTotalByIdBootcamp(String idBootcamp) {
    return client
        .get()
        .uri("/api/v1/bootcamp/{id}/count", idBootcamp)
        .retrieve()
        .bodyToMono(CapacityRestResponse.class)
        .map(capacityMapper::toDomain)
        .onErrorResume(WebClientResponseException.NotFound.class, ex -> {
              log.warn("Bootcamp not found by id={}. The endpoint returned 404.", idBootcamp);
              return Mono.error(new ObjectNotFoundException(ErrorCode.BOOTCAMP_NOT_FOUND, idBootcamp));
            }
        );
  }
}
