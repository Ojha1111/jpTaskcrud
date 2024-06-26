package com.javaupgradation.javacrudoperation.repository;


import com.javaupgradation.javacrudoperation.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindById() {
        // Given
        User user = new User(1L, "Amit Ojha", "Amit.Ojha@gmail.com");
        entityManager.persist(user);
        entityManager.flush();

        // When
        Optional<User> found = userRepository.findById(1L);

        // Then
        assertTrue(found.isPresent());
        assertEquals("Amit Ojha", found.get().getName());
        assertEquals("Amit.Ojha@gmail.com", found.get().getEmail());
    }

    @Test
    void testSaveUser() {
        // Given
        User user = new User(null, "Amits Ojha", "Amits.Ojha@gmail.com");

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertNotNull(savedUser.getId()); // ID should be generated
        assertEquals("Amits Ojha", savedUser.getName());
        assertEquals("Amits.Ojha@gmail.com", savedUser.getEmail());
    }

    @Test
    void testDeleteUser() {
        // Given
        User user = new User(1L, "Amits Ojha", "Amits.Ojha@gmail.com");
        entityManager.persist(user);
        entityManager.flush();

        // When
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(1L);

        // Then
        assertFalse(deletedUser.isPresent()); // User should be deleted
    }
}