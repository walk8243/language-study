package xyz.walk8243.study.reactor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RateLimitController {
    @GetMapping("/rate-limit")
    public Flux<String> getRateLimitedData() {
        return Flux.just("rate", "limit", "controller", "example");
    }
}
