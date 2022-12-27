package com.project.controlfood.interfaces.http.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDetails {

    private Map<String, String> violations;
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;

}
