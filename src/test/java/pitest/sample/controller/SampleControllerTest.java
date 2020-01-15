package pitest.sample.controller;

import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import io.restassured.module.webtestclient.response.WebTestClientResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import pitest.sample.service.SampleService;
import reactor.core.publisher.Mono;

import static io.restassured.module.webtestclient.RestAssuredWebTestClient.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.empty;

@WebFluxTest(SampleController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@ContextConfiguration(classes = {SampleController.class, WebClientAutoConfiguration.class})
public class SampleControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean(reset = MockReset.NONE)
    private SampleService service;

    @BeforeAll
    public void setup() {
        RestAssuredWebTestClient.webTestClient(webClient);

        when(service.doSomething())
            .thenReturn(empty())
            .thenReturn(Mono.error(new RuntimeException("Internal error for test")));
    }

    @Test
    public void validate_1_ok() {
        WebTestClientResponse response = given().spec(given())
            .post("/my_endpoint");

        assertThat(response.statusCode()).isEqualTo(204);
    }

    @Test
    public void validate_2_internalError() {
        WebTestClientResponse response = given().spec(given())
            .post("/my_endpoint");

        assertThat(response.statusCode()).isEqualTo(500);
    }
}