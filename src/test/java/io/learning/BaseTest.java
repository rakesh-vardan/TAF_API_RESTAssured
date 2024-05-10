package io.learning;

import io.learning.config.TestConfig;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestConfig.class)
public abstract class BaseTest {
}
