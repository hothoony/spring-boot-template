package study.springbootbasic.homeGreeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void pathRootShouldReturnDefaultMessage() {
        String result = restTemplate.getForObject("http://localhost:" + port + "/", String.class);
        assertThat(result).contains("Hello World");
    }

    @Test
    void pathRestShouldReturnDefaultMessage() {
        String result = restTemplate.getForObject("http://localhost:" + port + "/rest", String.class);
        assertThat(result).contains("Hello Rest");
    }
}
