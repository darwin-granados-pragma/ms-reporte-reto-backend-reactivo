package co.com.reporte.mongo.adapter;

import co.com.reporte.model.gateways.ReportRepository;
import co.com.reporte.model.report.Report;
import co.com.reporte.mongo.document.ReporteDocument;
import co.com.reporte.mongo.helper.AdapterOperations;
import co.com.reporte.mongo.repository.ReportDBRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class ReportRepositoryAdapter extends
    AdapterOperations<Report, ReporteDocument, String, ReportDBRepository> implements
    ReportRepository {

  public ReportRepositoryAdapter(ReportDBRepository repository, ObjectMapper mapper) {
    super(repository, mapper, d -> mapper.map(d, Report.class));
  }

  @Override
  public Mono<Report> save(Report report) {
    log.info("Saving report with name: {}", report.getName());
    return super
        .save(report)
        .doOnSuccess(reportSaved -> log.debug("Report saved: {}", reportSaved));
  }

  @Override
  public Mono<Report> findTopByOrderByTotalPeopleDesc() {
    log.info("Finding report with highest total people");
    return super.repository
        .findTopByOrderByTotalPeopleDesc()
        .map(this::toEntity)
        .doOnSuccess(report -> log.debug("Found report with highest total people: {}", report));
  }

  @Override
  public Mono<Report> findByIdBootcamp(String idBootcamp) {
    log.info("Finding report by bootcamp ID: {}", idBootcamp);
    return super.repository
        .findByIdBootcamp(idBootcamp)
        .map(this::toEntity)
        .doOnSuccess(report -> log.debug("Found report: {}", report));
  }
}
