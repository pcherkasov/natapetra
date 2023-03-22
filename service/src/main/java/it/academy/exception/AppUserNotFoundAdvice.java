package it.academy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//лучше переименовать класс в ExceptionHandler и сделать в нём несколько методов, которые и будут отвечать за перехват исключений.
@ControllerAdvice
public class AppUserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AppUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(AppUserNotFoundException ex) {
        return ex.getMessage();
    }
}
