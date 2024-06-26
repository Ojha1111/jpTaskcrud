package com.javaupgradation.javacrudoperation.service.impl;
import com.javaupgradation.javacrudoperation.dto.UserDTO;
import com.javaupgradation.javacrudoperation.entity.User;
import com.javaupgradation.javacrudoperation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Amit Ojha", "Amit.Ojha@gmail.com");
        userDTO = new UserDTO(1L, "Amit Ojha", "Amit.Ojha@gmail.com");
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<UserDTO> userDTOList = userService.getAllUsers();

        assertNotNull(userDTOList);
        assertEquals(1, userDTOList.size());
        assertEquals(userDTO.getId(), userDTOList.get(0).getId());
    }

    @Test
    void getUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<UserDTO> optionalUserDTO = userService.getUserById(1L);

        assertTrue(optionalUserDTO.isPresent());
        assertEquals(userDTO.getId(), optionalUserDTO.get().getId());
    }

    @Test
    void createUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO createdUserDTO = userService.createUser(userDTO);

        assertNotNull(createdUserDTO);
        assertEquals(userDTO.getId(), createdUserDTO.getId());
    }

    @Test
    void updateUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        Optional<UserDTO> updatedUserDTO = userService.updateUser(1L, userDTO);

        assertTrue(updatedUserDTO.isPresent());
        assertEquals(userDTO.getId(), updatedUserDTO.get().getId());
    }

    @Test
    void deleteUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(any(User.class));

        boolean isDeleted = userService.deleteUser(1L);

        assertTrue(isDeleted);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void deleteUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        boolean isDeleted = userService.deleteUser(1L);

        assertFalse(isDeleted);
        verify(userRepository, times(0)).delete(any(User.class));
    }
}
