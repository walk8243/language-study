package xyz.walk8243.study.reactor.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import reactor.core.publisher.Mono;

public class RateLimitedHandler {
	private final Duration interval;
	private final AtomicReference<Instant> nextCallTime;

	public RateLimitedHandler(Duration interval) {
		this.interval = interval;
		this.nextCallTime = new AtomicReference<>(Instant.now());
	}

	/**
	 * 指定されたタスクをレート制御付きで実行する
	 * @param task 実行するタスク
	 * @return タスクの結果を含むMono
	 */
	public <R> Mono<R> execute(Supplier<Mono<R>> task) {
		return Mono.defer(() -> {
			Instant now = Instant.now();
			Instant scheduledTime = nextCallTime.getAndUpdate(prev -> {
				Instant newStartTime = prev.isAfter(now) ? prev : now;
				return newStartTime.plus(interval);
			});
			Duration delay = Duration.between(now, scheduledTime);
			if (delay.isNegative()) {
				delay = Duration.ZERO;
			}
			return Mono.delay(delay).then(task.get());
		});
	}
}
