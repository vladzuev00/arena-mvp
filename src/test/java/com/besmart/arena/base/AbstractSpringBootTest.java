package com.besmart.arena.base;

import com.besmart.arena.base.containerinitializer.DataBaseContainerInitializer;
import com.besmart.arena.base.containerinitializer.KafkaContainerInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = {DataBaseContainerInitializer.class, KafkaContainerInitializer.class})
public abstract class AbstractSpringBootTest {

}
