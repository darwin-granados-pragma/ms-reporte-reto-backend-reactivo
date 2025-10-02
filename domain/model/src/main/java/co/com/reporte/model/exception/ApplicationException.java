package co.com.reporte.model.exception;

import co.com.reporte.model.error.ErrorCode;
import co.com.reporte.model.error.ExceptionCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private final ExceptionCode exceptionCode;
  private final String fullErrorCode;

  public ApplicationException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.exceptionCode = errorCode.getExceptionCode();
    this.fullErrorCode = errorCode.getFullErrorCode();
  }

  public ApplicationException(ErrorCode errorCode, String value) {
    super(errorCode
        .getMessage()
        .concat(value));
    this.exceptionCode = errorCode.getExceptionCode();
    this.fullErrorCode = errorCode.getFullErrorCode();
  }
}
