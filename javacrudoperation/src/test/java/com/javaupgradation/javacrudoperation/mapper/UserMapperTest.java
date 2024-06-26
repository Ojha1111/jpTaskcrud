package com.javaupgradation.javacrudoperation.mapper;


import com.javaupgradation.javacrudoperation.dto.UserDTO;
import com.javaupgradation.javacrudoperation.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {

    @Test
    void testToDTO() {
        // Given
        User user = new User(1L, "Amit Ojha", "Amit.Ojha@gmail.com");

        // When
        UserDTO userDTO = UserMapper.toDTO(user);

        // Then
        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

    @Test
    void testToEntity() {
        // Given
        UserDTO userDTO = new UserDTO(1L, "Amit Ojha", "Amit.Ojha@gmail.com");

        // When
        User user = UserMapper.toEntity(userDTO);

        // Then
        assertNotNull(user);
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getEmail(), user.getEmail());
    }
}