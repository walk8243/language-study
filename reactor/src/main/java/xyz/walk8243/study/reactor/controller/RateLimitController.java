package xyz.walk8243.study.reactor.controller;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RateLimitController {
    private static final Duration RATE_LIMIT_INTERVAL = Duration.ofSeconds(1);

    @GetMapping("/rate-limit")
    public Flux<ApiResponse> getRateLimitedData() {
        return Flux.just(new ApiResponse(0, 1)) // 最初のページ番号から開始
                .expand(apiResponse -> {
                    // ★★★ ここがポイント ★★★
                    // まず1秒待ってから、flatMapで実際のAPI呼び出しを行う
                    return Mono.delay(RATE_LIMIT_INTERVAL)
                            .flatMap(tick -> callApi(apiResponse.nextPage.orElse(1)));
                })
                .takeUntil(apiResponse -> apiResponse.nextPage.isEmpty());
    }

    // 擬似的なAPI呼び出し (ランダムな時間がかかる)
    public Mono<ApiResponse> callApi(int page) {
        return Mono.fromCallable(() -> {
            long processingTime = 50 + (long) (Math.random() * 300); // 50ms〜350msの処理時間
            System.out.printf("[%s] Calling API for page %d (processing will take %d ms)\n",
                    LocalTime.now(), page, processingTime);
            Thread.sleep(processingTime);

            if (page < 5) {
                return new ApiResponse(page, page + 1); // 次のページあり
            } else {
                return new ApiResponse(page, Optional.empty()); // 最後のページ
            }
        });
    }

    static record ApiResponse(int currentPage, Optional<Integer> nextPage) {
        ApiResponse(int currentPage, Integer nextPage) {
            this(currentPage, Optional.ofNullable(nextPage));
        }
    }
}
