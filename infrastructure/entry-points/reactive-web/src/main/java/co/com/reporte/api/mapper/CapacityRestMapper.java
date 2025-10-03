package co.com.reporte.api.mapper;

import co.com.reporte.api.model.response.CapacityRestResponse;
import co.com.reporte.model.capacity.CapacityDomainResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, uses = {TechnologyRestMapper.class})
public interface CapacityRestMapper {

  CapacityRestResponse toRestResponse(CapacityDomainResponse response);
}
