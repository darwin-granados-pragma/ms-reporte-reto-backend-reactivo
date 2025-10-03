package co.com.reporte.usecase.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import co.com.reporte.model.capacity.CapacityDomainResponse;
import co.com.reporte.model.capacity.CapacityTechnologyTotal;
import co.com.reporte.model.capacity.technology.TechnologyDomainResponse;
import co.com.reporte.model.gateways.CapacityGateway;
import co.com.reporte.model.gateways.PersonGateway;
import co.com.reporte.model.gateways.ReportRepository;
import co.com.reporte.model.person.PersonDomainResponse;
import co.com.reporte.model.report.Report;
import co.com.reporte.model.report.ReportCreate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ReportUseCaseTest {

  private final ReportCreate reportCreate = ReportCreate
      .builder()
      .idBootcamp("test id bootcamp")
      .build();
  private final Report report = Report
      .builder()
      .idBootcamp(reportCreate.getIdBootcamp())
      .totalPeople(0L)
      .build();

  private final CapacityTechnologyTotal capacityTechnologyTotal = new CapacityTechnologyTotal(2L,
      4L
  );

  @Mock
  private ReportRepository repository;
  @Mock
  private CapacityGateway capacityGateway;
  @Mock
  private PersonGateway personGateway;
  @InjectMocks
  private ReportUseCase reportUseCase;

  @Test
  void shouldCreateReportSuccessfully() {
    // Arrange
    String idBootcamp = reportCreate.getIdBootcamp();
    when(capacityGateway.getCapacityTechnologyTotalByIdBootcamp(idBootcamp)).thenReturn(Mono.just(
        capacityTechnologyTotal));
    when(repository.save(any(Report.class))).thenReturn(Mono.just(report));

    // Act
    var result = reportUseCase.createReport(reportCreate);

    // Assert
    StepVerifier
        .create(result)
        .verifyComplete();
  }

  @Test
  void shouldGetTopBootcampByPeopleSuccessfully() {
    // Arrange
    TechnologyDomainResponse technologyDomainResponse1 = TechnologyDomainResponse
        .builder()
        .id("tech-1")
        .name("Java")
        .build();
    TechnologyDomainResponse technologyDomainResponse2 = TechnologyDomainResponse
        .builder()
        .id("tech-2")
        .name("Spring")
        .build();
    CapacityDomainResponse capacity1 = CapacityDomainResponse
        .builder()
        .id("cap-1")
        .name("cap-name")
        .technologies(List.of(technologyDomainResponse1, technologyDomainResponse2))
        .build();
    CapacityDomainResponse capacity2 = CapacityDomainResponse
        .builder()
        .id("cap-2")
        .name("cap-name-2")
        .technologies(List.of(technologyDomainResponse1))
        .build();

    PersonDomainResponse person1 = PersonDomainResponse
        .builder()
        .name("Person One")
        .email("person1@test.com")
        .build();
    PersonDomainResponse person2 = PersonDomainResponse
        .builder()
        .name("Person Two")
        .email("person2@test.com")
        .build();

    when(repository.findTopByOrderByTotalPeopleDesc()).thenReturn(Mono.just(report));
    when(capacityGateway.getCapacitiesByIdBootcamp(anyString())).thenReturn(Flux.just(capacity1,
        capacity2
    ));
    when(personGateway.getAllPeopleByIdBootcamp(anyString())).thenReturn(Flux.just(person1,
        person2
    ));

    // Act
    var result = reportUseCase.getTopBootcampByPeople();

    // Assert
    StepVerifier
        .create(result)
        .assertNext(bootcampResponse -> {
          assertEquals(report.getIdBootcamp(), bootcampResponse.getIdBootcamp());
          assertEquals(report.getName(), bootcampResponse.getName());
          assertEquals(2,
              bootcampResponse
                  .getCapacities()
                  .size()
          );
          assertEquals(2,
              bootcampResponse
                  .getPeople()
                  .size()
          );
        })
        .verifyComplete();
  }

  @Test
  void shouldUpgradePeopleCountInReportSuccessfully() {
    // Arrange
    String idBootcamp = "test-bootcamp";
    Report updatedReport = Report
        .builder()
        .id("report-id")
        .idBootcamp(idBootcamp)
        .totalPeople(1L)
        .build();

    when(repository.findByIdBootcamp(idBootcamp)).thenReturn(Mono.just(report));
    when(repository.save(any(Report.class))).thenReturn(Mono.just(updatedReport));

    // Act
    var result = reportUseCase.upgradePeopleCountInReport(idBootcamp);

    // Assert
    StepVerifier
        .create(result)
        .verifyComplete();
  }
}