package co.com.reporte.consumer.mapper;

import co.com.reporte.consumer.model.CapacityRestResponse;
import co.com.reporte.model.capacity.CapacityTechnologyTotal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CapacityMapper {

  CapacityTechnologyTotal toDomain(CapacityRestResponse response);
}
