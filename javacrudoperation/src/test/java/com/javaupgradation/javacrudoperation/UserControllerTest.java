package com.javaupgradation.javacrudoperation;

import com.javaupgradation.javacrudoperation.dto.UserDTO;
import com.javaupgradation.javacrudoperation.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerTest userControllerTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsersTest() {
        // Mock the service call
        when(userService.getAllUsers()).thenReturn(Arrays.asList(
                new UserDTO(1L, "John Doe", "john@example.com"),
                new UserDTO(2L, "Jane Doe", "jane@example.com")
        ));

        // Make the GET request
        ResponseEntity<UserDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/users", UserDTO[].class);
        UserDTO[] users = responseEntity.getBody();

        // Assert the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(users).hasSize(2);
    }

    @Test
    public void getUserByIdTest() {
        // Mock the service call
        when(userService.getUserById(1L)).thenReturn(new UserDTO(1L, "John Doe", "john@example.com"));

        // Make the GET request
        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/users/1", UserDTO.class);
        UserDTO user = responseEntity.getBody();

        // Assert the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("John Doe");
    }

    @Test
    public void createUserTest() {
        UserDTO userDTO = new UserDTO(null, "John Doe", "john@example.com");

        // Mock the service call
        when(userService.createUser(userDTO)).thenReturn(new UserDTO(1L, "John Doe", "john@example.com"));

        // Make the POST request
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(userDTO);
        ResponseEntity<UserDTO> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/users", requestEntity, UserDTO.class);
        UserDTO user = responseEntity.getBody();

        // Assert the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("John Doe");
    }

    @Test
    public void updateUserTest() {
        UserDTO userDTO = new UserDTO(null, "John Doe Updated", "john@example.com");

        // Mock the service call
        when(userService.updateUser(1L, userDTO)).thenReturn(new UserDTO(1L, "John Doe Updated", "john@example.com"));

        // Make the PUT request
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(userDTO);
        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/users/1", HttpMethod.PUT, requestEntity, UserDTO.class);
        UserDTO user = responseEntity.getBody();

        // Assert the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("John Doe Updated");
    }

    @Test
    public void deleteUserTest() {
        // Mock the service call
        when(userService.getUserById(1L)).thenReturn(new UserDTO(1L, "John Doe", "john@example.com"));

        // Make the DELETE request
        ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/users/1", HttpMethod.DELETE, null, Void.class);

        // Assert the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}