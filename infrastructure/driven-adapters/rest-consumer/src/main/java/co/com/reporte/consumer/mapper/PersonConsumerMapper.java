package co.com.reporte.consumer.mapper;

import co.com.reporte.consumer.model.PersonConsumerResponse;
import co.com.reporte.model.person.PersonDomainResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface PersonConsumerMapper {

  PersonDomainResponse toPersonDomainResponse(PersonConsumerResponse response);
}
