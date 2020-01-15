package pitest.sample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pitest.sample.service.SampleService;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService service;

    @PostMapping(value = "/my_endpoint")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> doSomething() {
        return service.doSomething();
    }
}
