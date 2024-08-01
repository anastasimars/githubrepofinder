package com.example.githubrepofinder.exception;

import com.example.githubrepofinder.model.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class GitHubExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleUsernameNotFoundException
            (UsernameNotFoundException exception){
        ErrorMessageResponse errorMessage = new ErrorMessageResponse(404, exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


}
