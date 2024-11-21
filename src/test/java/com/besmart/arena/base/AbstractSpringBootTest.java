package com.besmart.arena.base;

import com.besmart.arena.base.containerinitializer.DataBaseContainerInitializer;
import com.besmart.arena.base.containerinitializer.KafkaContainerInitializer;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static java.util.Locale.US;
import static java.util.Locale.setDefault;

@SpringBootTest
@ContextConfiguration(initializers = {DataBaseContainerInitializer.class, KafkaContainerInitializer.class})
public abstract class AbstractSpringBootTest {

    @BeforeAll
    public static void setLocale() {
        setDefault(US);
    }
}
