package co.com.reporte.api.mapper;

import co.com.reporte.api.model.request.ReportCreateRequest;
import co.com.reporte.model.report.ReportCreate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ReportRestMapper {

  ReportCreate toReport(ReportCreateRequest request);
}
