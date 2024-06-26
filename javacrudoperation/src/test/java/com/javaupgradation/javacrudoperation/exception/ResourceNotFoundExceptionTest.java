package com.javaupgradation.javacrudoperation.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testResourceNotFoundException() {
        // Given
        String message = "User not found";

        // When
        ResourceNotFoundException ex = new ResourceNotFoundException(message);

        // Then
        assertEquals(message, ex.getMessage());
    }
}