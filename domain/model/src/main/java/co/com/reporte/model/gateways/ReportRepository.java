package co.com.reporte.model.gateways;

import co.com.reporte.model.report.Report;
import reactor.core.publisher.Mono;

public interface ReportRepository {

  Mono<Report> save(Report report);

  Mono<Report> findTopByOrderByTotalPeopleDesc();

  Mono<Report> findByIdBootcamp(String idBootcamp);
}
