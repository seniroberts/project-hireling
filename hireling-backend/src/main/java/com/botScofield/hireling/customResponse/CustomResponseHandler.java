package com.botScofield.hireling.customResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class CustomResponseHandler {
    public static ResponseEntity<Object> customResponseBuilder(String message, HttpStatus httpStatus,
                                                               Object responseObject) {
        // Build the response
        Map<String, Object> response = new HashMap<>();
        response.put("Message", message);
        response.put("Status", httpStatus);
        response.put("Content", responseObject);

        return new ResponseEntity<>(response, httpStatus);

    }
}