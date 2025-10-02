package co.com.reporte.api.handler;

import co.com.reporte.api.mapper.ReportRestMapper;
import co.com.reporte.api.model.request.ReportCreateRequest;
import co.com.reporte.model.report.ReportCreate;
import co.com.reporte.usecase.report.ReportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
