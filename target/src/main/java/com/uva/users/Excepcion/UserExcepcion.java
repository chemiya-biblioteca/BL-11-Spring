package com.uva.users.Excepcion;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UserExcepcion extends RuntimeException{
    public UserExcepcion(String mensaje) {
    super(mensaje);
}
}

