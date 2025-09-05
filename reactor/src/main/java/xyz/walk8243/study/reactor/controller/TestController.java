package xyz.walk8243.study.reactor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestController {
    @GetMapping("/test")
    public Flux<String> test() {
        return Flux.just("Controller動作確認用");
    }
}
