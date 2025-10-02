package co.com.reporte.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "reporte")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReporteDocument {

  @Id
  private String id;

  @Field("id_bootcamp")
  private String idBootcamp;

  @Field("nombre")
  private String name;

  @Field("descripcion")
  private String description;

  @Field("fecha_lanzamiento")
  private String releaseDate;

  @Field("duracion")
  private Integer duration;

  @Field("total_capacidades")
  private Long totalCapacities;

  @Field("total_tecnologias")
  private Long totalTechnologies;

  @Field("total_personas")
  private Long totalPeople;
}
