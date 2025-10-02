package co.com.reporte.usecase.report;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import co.com.reporte.model.capacity.CapacityTechnologyTotal;
import co.com.reporte.model.gateways.CapacityGateway;
import co.com.reporte.model.gateways.PersonGateway;
import co.com.reporte.model.gateways.ReportRepository;
import co.com.reporte.model.report.Report;
import co.com.reporte.model.report.ReportCreate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    Long totalPeople = 6L;
    when(capacityGateway.getCapacityTechnologyTotalByIdBootcamp(idBootcamp)).thenReturn(Mono.just(
        capacityTechnologyTotal));
    when(personGateway.getTotalPeopleByIdBootcamp(idBootcamp)).thenReturn(Mono.just(totalPeople));
    when(repository.save(any(Report.class))).thenReturn(Mono.just(report));

    // Act
    var result = reportUseCase.createReport(reportCreate);

    // Assert
    StepVerifier
        .create(result)
        .verifyComplete();
  }
}