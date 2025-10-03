package co.com.reporte.api.handler;

import co.com.reporte.api.mapper.BootcampRestMapper;
import co.com.reporte.api.mapper.ReportRestMapper;
import co.com.reporte.api.model.request.ReportCreateRequest;
import co.com.reporte.model.report.ReportCreate;
import co.com.reporte.usecase.report.ReportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportHandler {

  private final ReportUseCase useCase;
  private final ReportRestMapper mapper;
  private final BootcampRestMapper bootcampMapper;
  private final RequestValidator requestValidator;

  public Mono<ServerResponse> createReport(ServerRequest serverRequest) {
    log.info("Received request to create a report at path={} method={}",
        serverRequest.path(),
        serverRequest.method()
    );
    return serverRequest
        .bodyToMono(ReportCreateRequest.class)
        .flatMap(request -> requestValidator
            .validate(request)
            .then(Mono.defer(() -> {
              ReportCreate report = mapper.toReport(request);
              return useCase
                  .createReport(report)
                  .flatMap(response -> ServerResponse
                      .noContent()
                      .build());
            })));
  }

  public Mono<ServerResponse> upgradePeopleCountInReport(ServerRequest serverRequest) {
    log.info("Received request to upgrade people count in report at path={} method={}",
        serverRequest.path(),
        serverRequest.method()
    );
    return Mono.defer(() -> {
      String idBootcamp = serverRequest.pathVariable("idBootcamp");
      return useCase
          .upgradePeopleCountInReport(idBootcamp)
          .then(ServerResponse
              .noContent()
              .build());
    });
  }

  public Mono<ServerResponse> getTopBootcampByPeople(ServerRequest serverRequest) {
    log.info("Received request to retrieve top bootcamp by people at path={} method={}",
        serverRequest.path(),
        serverRequest.method()
    );
    return useCase
        .getTopBootcampByPeople()
        .map(bootcampMapper::toRestResponse)
        .flatMap(response -> ServerResponse
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(response));
  }
}
