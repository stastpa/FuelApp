package cz.cvut.fit.tjv.fuelapp.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @Test
    void testHandleIllegalArgument() {
        RuntimeException illegalArgumentEx = new RuntimeException("Supplied argument was illegal");

        ResponseEntity<Object> responseEntity = restExceptionHandler.handleIllegalArgument(illegalArgumentEx, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Supplied argument was illegal", responseEntity.getBody());
    }

    @Test
    void testHandleNonExistingEntity() {
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException("Supplied entity does not exist");

        ResponseEntity<Object> responseEntity = restExceptionHandler.handleNonExistingEntity(entityNotFoundException, webRequest);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals("Supplied entity does not exist", responseEntity.getBody());
    }
}