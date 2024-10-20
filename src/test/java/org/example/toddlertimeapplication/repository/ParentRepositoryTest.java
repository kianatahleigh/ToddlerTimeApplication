package org.example.toddlertimeapplication.repository;

import org.example.toddlertimeapplication.model.Parent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")  // Use a separate profile for testing
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    private Parent parent;

    @BeforeEach
    public void setUp() {
        // Initialize a Parent object before each test
        parent = new Parent();
        parent.setFirstName("John");
        parent.setLastName("Doe");
        parent.setEmail("john.doe@example.com");
        parent.setPhoneNumber("1234567890");
        parent.setPassword("password123");
        // Save the parent to the repository for testing
        parentRepository.save(parent);
    }

    @Test
    public void testFindByEmail() {
        Optional<Parent> foundParent = parentRepository.findByEmail("john.doe@example.com");

        assertThat(foundParent).isPresent();
        assertThat(foundParent.get().getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    public void testFindByPhoneNumber() {
        Parent foundParent = parentRepository.findByPhoneNumber("1234567890");

        assertThat(foundParent).isNotNull();
        assertThat(foundParent.getPhoneNumber()).isEqualTo("1234567890");
    }

    @Test
    public void testFindByEmail_NotFound() {
        Optional<Parent> foundParent = parentRepository.findByEmail("nonexistent@example.com");

        assertThat(foundParent).isNotPresent();
    }

    @Test
    public void testFindByPhoneNumber_NotFound() {
        Parent foundParent = parentRepository.findByPhoneNumber("0987654321");

        assertThat(foundParent).isNull();  // Should return null for a nonexistent phone number
    }
}
