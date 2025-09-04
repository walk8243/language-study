package xyz.walk8243.study.reactor.controller;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RateLimitController {
    private static final Duration RATE_LIMIT_INTERVAL = Duration.ofSeconds(1);

    @GetMapping("/rate-limit")
    public Flux<ApiResponse> getRateLimitedData() {
        // ★★★次にAPI呼び出しを開始して良い時刻を管理する★★★
        final AtomicReference<Instant> nextCallTime = new AtomicReference<>(Instant.now());

        // 最初のページから開始
        return Flux.just(new ApiResponse(0, 1))
                .expand(apiResponse -> {
                    if (apiResponse.nextPage().isEmpty()) {
                        return Mono.empty(); // 次のページがなければ終了
                    }

                    // --- ここからが修正ロジック ---
                    Instant now = Instant.now();

                    // 1. 次に呼び出すべき時刻を計算して、アトミックに更新する
                    Instant scheduledTime = nextCallTime.getAndUpdate(prev -> {
                        // 前の予定時刻が現在より後なら、その時刻を基準にする
                        // そうでなければ（処理が遅延した場合）、現在時刻を基準にする
                        Instant newStartTime = prev.isAfter(now) ? prev : now;
                        // 基準時刻にインターバルを加算したものが、"次"の呼び出し時刻
                        return newStartTime.plus(RATE_LIMIT_INTERVAL);
                    });

                    // 2. 今から呼び出し予定時刻まで、どれだけ待つべきか計算
                    Duration delay = Duration.between(now, scheduledTime);
                    if (delay.isNegative()) {
                        delay = Duration.ZERO; // 予定時刻を過ぎていたら待たない
                    }

                    // 3. 計算した時間だけ待ってから、APIを呼び出すMonoを返す
                    return Mono.delay(delay)
                            .then(callApi(apiResponse.nextPage().get())); // .then()でdelayの完了後にAPIを呼び出す
                });
    }

    // 擬似的なAPI呼び出し (ランダムな時間がかかる)
    public Mono<ApiResponse> callApi(int page) {
        return Mono.fromCallable(() -> {
            long processingTime = 50 + (long) (Math.random() * 300); // 50ms〜350msの処理時間
            System.out.printf("[%-18s] Calling API for page %d (processing will take %d ms)\n",
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
