package co.com.reporte.consumer.mapper;

import co.com.reporte.consumer.model.CapacityConsumerResponse;
import co.com.reporte.consumer.model.CapacityTechTotalRestResponse;
import co.com.reporte.model.capacity.CapacityDomainResponse;
import co.com.reporte.model.capacity.CapacityTechnologyTotal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CapacityMapper {

  CapacityTechnologyTotal toDomain(CapacityTechTotalRestResponse response);

  CapacityDomainResponse toCapacityResponse(CapacityConsumerResponse response);
}
