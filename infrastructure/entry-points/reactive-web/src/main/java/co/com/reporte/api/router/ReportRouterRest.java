package co.com.reporte.api.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import co.com.reporte.api.error.ErrorResponse;
import co.com.reporte.api.error.GlobalErrorWebFilter;
import co.com.reporte.api.handler.ReportHandler;
import co.com.reporte.api.model.request.ReportCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class ReportRouterRest {

  private static final String PATH = "/api/v1/reporte";
  private static final String PATH_TOP_BOOTCAMP = PATH + "/top-bootcamp";
  private static final String PATH_ID_BOOTCAMP = PATH + "/{idBootcamp}/personas";

  private final ReportHandler reportHandler;
  private final GlobalErrorWebFilter globalErrorWebFilter;

  @Bean
  @RouterOperations({@RouterOperation(method = RequestMethod.POST,
      path = PATH,
      beanClass = ReportHandler.class,
      beanMethod = "createReport",
      operation = @Operation(operationId = "createReport",
          summary = "Crear reporte",
          description = "Recibe datos del bootcamp y devuelve el reporte creado",
          requestBody = @RequestBody(required = true,
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = ReportCreateRequest.class)
              )
          ),
          responses = {
              @ApiResponse(responseCode = "204", description = "Reporte creado correctamente"
              ), @ApiResponse(responseCode = "400",
              description = "Parámetros inválidos o faltantes",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )}
      )
  ), @RouterOperation(method = RequestMethod.GET,
      path = PATH_TOP_BOOTCAMP,
      beanClass = ReportHandler.class,
      beanMethod = "getTopBootcampByPeople",
      operation = @Operation(operationId = "getTopBootcampByPeople",
          summary = "Obtener top bootcamp por personas",
          description = "Devuelve el bootcamp con más personas inscritas",
          responses = {
              @ApiResponse(responseCode = "200", description = "Bootcamp obtenido correctamente")}
      )
  ), @RouterOperation(method = RequestMethod.POST,
      path = PATH_ID_BOOTCAMP,
      beanClass = ReportHandler.class,
      beanMethod = "upgradePeopleCountInReport",
      operation = @Operation(operationId = "upgradePeopleCountInReport",
          summary = "Actualizar conteo de personas en reporte",
          description = "Actualiza el conteo de personas inscritas en un bootcamp específico",
          responses = {@ApiResponse(responseCode = "204",
              description = "Conteo de personas actualizado correctamente"
          )}
      )
  )}
  )
  public RouterFunction<ServerResponse> routerFunction() {
    return route(POST(PATH), reportHandler::createReport)
        .andRoute(GET(PATH_TOP_BOOTCAMP), reportHandler::getTopBootcampByPeople)
        .andRoute(POST(PATH_ID_BOOTCAMP), reportHandler::upgradePeopleCountInReport)
        .filter(globalErrorWebFilter);
  }
}
