package com.uc.ms_security.exception;

public class ApplicationException extends RuntimeException {

    private final ErrorCase errorCase;

    public ApplicationException(ErrorCase errorCase, String message) {
        super(message);
        this.errorCase = errorCase;
    }

    public ErrorCase getErrorCase() {
        return errorCase;
    }
}
//se puede programar un disparo a correo electronico ya que pasa n¿muchos problemas que vamos a estar veinedo
//personalizar las excepcoones