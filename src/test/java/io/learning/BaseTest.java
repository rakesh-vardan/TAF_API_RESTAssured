package io.learning;

import io.learning.config.APIConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = APIConfiguration.class)
public abstract class BaseTest {
}
