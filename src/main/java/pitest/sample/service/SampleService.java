package pitest.sample.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SampleService {

    public Mono<Void> doSomething() {
        return Mono.empty();
    }

}
