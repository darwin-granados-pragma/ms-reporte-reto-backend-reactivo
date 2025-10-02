package co.com.reporte.model.exception;

import co.com.reporte.model.error.ErrorCode;

public class ObjectNotFoundException extends ApplicationException {

  public ObjectNotFoundException(ErrorCode errorCode, String value) {
    super(errorCode, value);
  }

}
