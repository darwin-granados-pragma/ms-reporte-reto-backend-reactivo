package co.com.reporte.api.mapper;

import co.com.reporte.api.model.response.BootcampRestResponse;
import co.com.reporte.model.report.BootcampDomainResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, uses = {CapacityRestMapper.class, PersonRestMapper.class})
public interface BootcampRestMapper {

  BootcampRestResponse toRestResponse(BootcampDomainResponse response);
}
