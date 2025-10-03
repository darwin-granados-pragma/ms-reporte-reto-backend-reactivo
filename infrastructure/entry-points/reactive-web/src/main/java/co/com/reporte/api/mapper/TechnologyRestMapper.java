package co.com.reporte.api.mapper;

import co.com.reporte.api.model.response.TechnologyRestResponse;
import co.com.reporte.model.capacity.technology.TechnologyDomainResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface TechnologyRestMapper {

  TechnologyRestResponse toRestResponse(TechnologyDomainResponse response);
}
