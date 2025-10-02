package co.com.reporte.mongo.repository;

import co.com.reporte.mongo.document.ReporteDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface ReportDBRepository extends ReactiveMongoRepository<ReporteDocument, String>,
    ReactiveQueryByExampleExecutor<ReporteDocument> {

}
