package com.javaupgradation.javacrudoperation;

import com.javaupgradation.javacrudoperation.controller.UserController;
import com.javaupgradation.javacrudoperation.dto.UserDTO;
import com.javaupgradation.javacrudoperation.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        setup();
        UserDTO user1 = new UserDTO(1L, "Amit Ojha", "Amit.Ojha@gmail.com");
        UserDTO user2 = new UserDTO(2L, "Amits Ojha", "Amits.Ojha@gmail.com");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Amit Ojha")))
                .andExpect(jsonPath("$[0].email", is("Amit.Ojha@gmail.com")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Amits Ojha")))
                .andExpect(jsonPath("$[1].email", is("Amits.Ojha@gmail.com")));
    }

    @Test
    void testGetUserById() throws Exception {
        setup();
        UserDTO user = new UserDTO(1L, "Amit Ojha", "Amit.Ojha@gmail.com");

        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Amit Ojha")))
                .andExpect(jsonPath("$.email", is("Amit.Ojha@gmail.com")));
    }

    @Test
    void testCreateUser() throws Exception {
        setup();
        UserDTO user = new UserDTO(1L, "Amit Ojha", "Amit.Ojha@gmail.com");

        when(userService.createUser(any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Amit Ojha\", \"email\": \"Amit.Ojha@gmail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Record has been saved: " + user));

    }

    @Test
    void testUpdateUser() throws Exception {
        setup();
        UserDTO user = new UserDTO(1L, "Amit Ojha", "Amit.Ojha@gmail.com");

        when(userService.updateUser(anyLong(), any(UserDTO.class))).thenReturn(Optional.of(user));

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Amit Ojha\", \"email\": \"Amit.Ojha@gmail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Record has been updated: " + user));
    }

    @Test
    void testDeleteUser() throws Exception {
        setup();
        when(userService.deleteUser(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Record has been deleted"));
    }
}