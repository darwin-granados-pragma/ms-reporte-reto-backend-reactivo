package co.com.reporte.usecase.report;

import co.com.reporte.model.gateways.CapacityGateway;
import co.com.reporte.model.gateways.PersonGateway;
import co.com.reporte.model.gateways.ReportRepository;
import co.com.reporte.model.report.Report;
import co.com.reporte.model.report.ReportCreate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReportUseCase {

  private final ReportRepository repository;
  private final CapacityGateway capacityGateway;
  private final PersonGateway personGateway;

  public Mono<Void> createReport(ReportCreate data) {
    return capacityGateway
        .getCapacityTechnologyTotalByIdBootcamp(data.getIdBootcamp())
        .flatMap(response -> personGateway
            .getTotalPeopleByIdBootcamp(data.getIdBootcamp())
            .flatMap(totalPeople -> Mono
                .fromCallable(() -> Report
                    .builder()
                    .id(UUID
                        .randomUUID()
                        .toString())
                    .idBootcamp(data.getIdBootcamp())
                    .name(data.getName())
                    .description(data.getDescription())
                    .releaseDate(data.getReleaseDate())
                    .duration(data.getDuration())
                    .totalCapacities(response.totalCapacity())
                    .totalTechnologies(response.totalTechnology())
                    .totalPeople(totalPeople)
                    .build())
                .flatMap(repository::save)
                .then()));
  }
}
