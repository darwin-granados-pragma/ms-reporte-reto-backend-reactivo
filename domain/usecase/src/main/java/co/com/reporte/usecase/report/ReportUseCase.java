package co.com.reporte.usecase.report;

import co.com.reporte.model.gateways.CapacityGateway;
import co.com.reporte.model.gateways.PersonGateway;
import co.com.reporte.model.gateways.ReportRepository;
import co.com.reporte.model.person.PersonDomainResponse;
import co.com.reporte.model.report.BootcampDomainResponse;
import co.com.reporte.model.report.Report;
import co.com.reporte.model.report.ReportCreate;
import java.util.List;
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
        .flatMap(response -> Mono
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
                .totalPeople(0L)
                .build())
            .flatMap(repository::save))
        .then();
  }

  public Mono<Void> upgradePeopleCountInReport(String idBootcamp) {
    return repository
        .findByIdBootcamp(idBootcamp)
        .flatMap(report -> {
          report.setTotalPeople(report.getTotalPeople() + 1);
          return repository.save(report);
        })
        .then();
  }

  public Mono<BootcampDomainResponse> getTopBootcampByPeople() {
    return repository
        .findTopByOrderByTotalPeopleDesc()
        .flatMap(report -> personGateway
            .getAllPeopleByIdBootcamp(report.getIdBootcamp())
            .collectList()
            .flatMap(people -> mapToBootcampResponse(report, people)));
  }

  private Mono<BootcampDomainResponse> mapToBootcampResponse(Report report,
      List<PersonDomainResponse> people) {
    return capacityGateway
        .getCapacitiesByIdBootcamp(report.getIdBootcamp())
        .collectList()
        .map(responseList -> BootcampDomainResponse
            .builder()
            .idBootcamp(report.getIdBootcamp())
            .name(report.getName())
            .description(report.getDescription())
            .releaseDate(report.getReleaseDate())
            .duration(report.getDuration())
            .people(people)
            .capacities(responseList)
            .build());
  }
}
