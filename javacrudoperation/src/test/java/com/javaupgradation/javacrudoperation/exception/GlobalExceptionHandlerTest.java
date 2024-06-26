package com.javaupgradation.javacrudoperation.exception;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void testHandleResourceNotFoundException() {
        // Given
        ResourceNotFoundException ex = new ResourceNotFoundException("User not found");

        // When
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleResourceNotFoundException(ex);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());
    }
}