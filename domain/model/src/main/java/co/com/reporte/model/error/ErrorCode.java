package co.com.reporte.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  BOOTCAMP_NOT_FOUND("BOOTCAMP-NOT-FOUND",
      ExceptionCode.NOT_FOUND,
      "No se encontró el bootcamp con id: "
  ),
  ;

  private final String fullErrorCode;
  private final ExceptionCode exceptionCode;
  private final String message;
}
