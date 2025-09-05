package xyz.walk8243.study.reactor.controller;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.walk8243.study.reactor.domain.RateLimitedHandler;

@RestController
public class RateLimitController {
    private static final Duration RATE_LIMIT_INTERVAL = Duration.ofSeconds(1);

    @GetMapping("/rate-limit")
    public Flux<ApiResponse> getRateLimitedData() {
        log("Starting rate-limited API calls...");
        // ★★★次にAPI呼び出しを開始して良い時刻を管理する★★★
        final RateLimitedHandler handler = new RateLimitedHandler(RATE_LIMIT_INTERVAL);

        // 最初のページから開始
        return handler.execute(() -> callApi(1))
                .expand(previousResponse -> {
                    if (previousResponse.nextPage().isEmpty()) {
                        return Mono.empty(); // 次のページがなければ終了
                    }

                    // ★ expand内の再帰呼び出しでも同じヘルパーメソッドを使用
                    return handler.execute(() -> callApi(previousResponse.nextPage().get()));
                })
                .doOnComplete(() -> log("Completed all API calls."));
    }

    // 擬似的なAPI呼び出し (ランダムな時間がかかる)
    private Mono<ApiResponse> callApi(int page) {
        return Mono.fromCallable(() -> {
            long processingTime = 50 + (long) (Math.random() * 300); // 50ms〜350msの処理時間
            log("Calling API for page %d (processing will take %d ms)".formatted(page, processingTime));
            Thread.sleep(processingTime);

            if (page < 5) {
                return new ApiResponse(page, page + 1); // 次のページあり
            } else {
                return new ApiResponse(page, Optional.empty()); // 最後のページ
            }
        });
    }

    private void log(String message) {
        System.out.printf("[%-18s] %s\n", LocalTime.now(), message);
    }

    static record ApiResponse(int currentPage, Optional<Integer> nextPage) {
        ApiResponse(int currentPage, Integer nextPage) {
            this(currentPage, Optional.ofNullable(nextPage));
        }
    }
}
