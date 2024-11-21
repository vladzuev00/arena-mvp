package com.besmart.arena.config.property;

import com.besmart.arena.base.AbstractSpringBootTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static com.besmart.arena.testutil.ConstraintViolationUtil.validateExpectingOneViolation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class WebClientPropertyTest extends AbstractSpringBootTest {

    @Autowired
    private WebClientProperty property;

    @Autowired
    private Validator validator;

    @Test
    public void propertyShouldBeCreated() {
        WebClientProperty expected = new WebClientProperty(
                "aod-http-client",
                100000,
                100000,
                100000,
                5,
                100000,
                100000,
                100000
        );
        assertEquals(expected, property);
    }

    @Test
    public void propertyShouldBeValid() {
        WebClientProperty givenProperty = new WebClientProperty(
                "aod-http-client",
                100000,
                100000,
                100000,
                5,
                100000,
                100000,
                100000
        );

        Set<ConstraintViolation<WebClientProperty>> violations = validator.validate(givenProperty);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfConnectionProviderNameIsBlank() {
        WebClientProperty givenProperty = WebClientProperty.builder()
                .connectTimeoutMs(100000)
                .readTimeoutMs(100000)
                .writeTimeoutMs(100000)
                .maxConnections(5)
                .pendingAcquireTimeoutMs(100000)
                .maxIdleTimeoutMs(100000)
                .maxLifetimeMs(100000)
                .build();

        validator.validate(givenProperty);
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfConnectTimeoutMsIsNull() {
        WebClientProperty givenProperty = WebClientProperty.builder()
                .connectionProviderName("aod-http-client")
                .readTimeoutMs(100000)
                .writeTimeoutMs(100000)
                .maxConnections(5)
                .pendingAcquireTimeoutMs(100000)
                .maxIdleTimeoutMs(100000)
                .maxLifetimeMs(100000)
                .build();

        validateExpectingOneViolation(givenProperty, validator, "must not be null");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfConnectTimeoutMsIsNotPositive() {
        WebClientProperty givenProperty = new WebClientProperty(
                "aod-http-client",
                -100000,
                100000,
                100000,
                5,
                100000,
                100000,
                100000
        );

        validateExpectingOneViolation(givenProperty, validator, "must be greater than 0");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfReadTimeoutMsIsNull() {
        WebClientProperty givenProperty = WebClientProperty.builder()
                .connectionProviderName("aod-http-client")
                .connectTimeoutMs(100000)
                .writeTimeoutMs(100000)
                .maxConnections(5)
                .pendingAcquireTimeoutMs(100000)
                .maxIdleTimeoutMs(100000)
                .maxLifetimeMs(100000)
                .build();

        validateExpectingOneViolation(givenProperty, validator, "must not be null");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfReadTimeoutMsIsNotPositive() {
        WebClientProperty givenProperty = new WebClientProperty(
                "aod-http-client",
                100000,
                -100000,
                100000,
                5,
                100000,
                100000,
                100000
        );

        validateExpectingOneViolation(givenProperty, validator, "must be greater than 0");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfWriteTimeoutMsIsNull() {
        WebClientProperty givenProperty = WebClientProperty.builder()
                .connectionProviderName("aod-http-client")
                .connectTimeoutMs(100000)
                .readTimeoutMs(100000)
                .maxConnections(5)
                .pendingAcquireTimeoutMs(100000)
                .maxIdleTimeoutMs(100000)
                .maxLifetimeMs(100000)
                .build();

        validateExpectingOneViolation(givenProperty, validator, "must not be null");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfWriteTimeoutMsIsNotPositive() {
        WebClientProperty givenProperty = new WebClientProperty(
                "aod-http-client",
                100000,
                100000,
                -100000,
                5,
                100000,
                100000,
                100000
        );

        validateExpectingOneViolation(givenProperty, validator, "must be greater than 0");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfMaxConnectionsIsNull() {
        WebClientProperty givenProperty = WebClientProperty.builder()
                .connectionProviderName("aod-http-client")
                .connectTimeoutMs(100000)
                .readTimeoutMs(100000)
                .writeTimeoutMs(100000)
                .pendingAcquireTimeoutMs(100000)
                .maxIdleTimeoutMs(100000)
                .maxLifetimeMs(100000)
                .build();

        validateExpectingOneViolation(givenProperty, validator, "must not be null");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfMaxConnectionsIsNotPositive() {
        WebClientProperty givenProperty = new WebClientProperty(
                "aod-http-client",
                100000,
                100000,
                100000,
                -5,
                100000,
                100000,
                100000
        );

        validateExpectingOneViolation(givenProperty, validator, "must be greater than 0");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfPendingAcquireTimeoutMsIsNull() {
        WebClientProperty givenProperty = WebClientProperty.builder()
                .connectionProviderName("aod-http-client")
                .connectTimeoutMs(100000)
                .readTimeoutMs(100000)
                .writeTimeoutMs(100000)
                .maxConnections(5)
                .maxIdleTimeoutMs(100000)
                .maxLifetimeMs(100000)
                .build();

        validateExpectingOneViolation(givenProperty, validator, "must not be null");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfPendingAcquireTimeoutMsIsNotPositive() {
        WebClientProperty givenProperty = new WebClientProperty(
                "aod-http-client",
                100000,
                100000,
                100000,
                5,
                -100000,
                100000,
                100000
        );

        validateExpectingOneViolation(givenProperty, validator, "must be greater than 0");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfMaxIdleTimeoutMsIsNull() {
        WebClientProperty givenProperty = WebClientProperty.builder()
                .connectionProviderName("aod-http-client")
                .connectTimeoutMs(100000)
                .readTimeoutMs(100000)
                .writeTimeoutMs(100000)
                .maxConnections(5)
                .pendingAcquireTimeoutMs(100000)
                .maxLifetimeMs(100000)
                .build();

        validateExpectingOneViolation(givenProperty, validator, "must not be null");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfMaxIdleTimeoutMsIsNotPositive() {
        WebClientProperty givenProperty = new WebClientProperty(
                "aod-http-client",
                100000,
                100000,
                100000,
                5,
                100000,
                -100000,
                100000
        );

        validateExpectingOneViolation(givenProperty, validator, "must be greater than 0");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfMaxLifetimeMsIsNull() {
        WebClientProperty givenProperty = WebClientProperty.builder()
                .connectionProviderName("aod-http-client")
                .connectTimeoutMs(100000)
                .readTimeoutMs(100000)
                .writeTimeoutMs(100000)
                .maxConnections(5)
                .pendingAcquireTimeoutMs(100000)
                .maxIdleTimeoutMs(100000)
                .build();

        validateExpectingOneViolation(givenProperty, validator, "must not be null");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfMaxLifetimeMsIsNotPositive() {
        WebClientProperty givenProperty = new WebClientProperty(
                "aod-http-client",
                100000,
                100000,
                100000,
                5,
                100000,
                100000,
                -100000
        );

        validateExpectingOneViolation(givenProperty, validator, "must be greater than 0");
    }
}
