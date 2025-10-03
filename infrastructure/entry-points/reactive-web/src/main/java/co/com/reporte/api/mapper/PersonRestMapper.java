package co.com.reporte.api.mapper;

import co.com.reporte.api.model.response.PersonRestResponse;
import co.com.reporte.model.person.PersonDomainResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface PersonRestMapper {

  PersonRestResponse toRestResponse(PersonDomainResponse response);
}
