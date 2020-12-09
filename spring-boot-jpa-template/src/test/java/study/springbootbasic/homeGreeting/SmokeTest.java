package study.springbootbasic.homeGreeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private HomeRestController homeRestController;

    @Test
    void beanShouldNotNull() {
        assertThat(homeController).isNotNull();
        assertThat(homeRestController).isNotNull();
    }
}
