package xyz.walk8243.study.reactor.controller;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.walk8243.study.reactor.domain.ApiResponse;
import xyz.walk8243.study.reactor.domain.Logger;
import xyz.walk8243.study.reactor.domain.RateLimitedHandler;
import xyz.walk8243.study.reactor.repository.CallApiRepository;

@RestController
@RequiredArgsConstructor
public class RateLimitController {
    private static final Duration RATE_LIMIT_INTERVAL = Duration.ofSeconds(1);
    private final CallApiRepository callApiRepository;

    @GetMapping("/rate-limit")
    public Flux<ApiResponse> getRateLimitedData() {
        Logger.log("Starting rate-limited API calls...");
        // ★★★次にAPI呼び出しを開始して良い時刻を管理する★★★
        final RateLimitedHandler handler = new RateLimitedHandler(RATE_LIMIT_INTERVAL);

        // 最初のページから開始
        return handler.execute(() -> callApiRepository.callApi(1))
                .expand(previousResponse -> {
                    if (previousResponse.nextPage().isEmpty()) {
                        return Mono.empty(); // 次のページがなければ終了
                    }

                    // ★ expand内の再帰呼び出しでも同じヘルパーメソッドを使用
                    return handler.execute(() -> callApiRepository.callApi(previousResponse.nextPage().get()));
                })
                .doOnComplete(() -> Logger.log("Completed all API calls."));
    }
}
