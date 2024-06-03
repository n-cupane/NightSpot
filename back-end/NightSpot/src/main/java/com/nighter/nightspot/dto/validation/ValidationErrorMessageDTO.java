package com.nighter.nightspot.dto.validation;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ValidationErrorMessageDTO {

    private LocalDateTime timeStamp = LocalDateTime.now();
    private int statusCode;
    private String statusString;
    private String targetUrl;
    private Map<String,String> fieldErrors;
}
