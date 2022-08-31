package training.peopleandcars.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import training.peopleandcars.exception.ModelNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleModelNotFound(ModelNotFoundException exception){
        Map<String,String> errorPayLoad = new HashMap<>();
        errorPayLoad.put("messageError",exception.getMessage());
        return new ResponseEntity<>(errorPayLoad, HttpStatus.NOT_FOUND);
    }

}
