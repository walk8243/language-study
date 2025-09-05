package xyz.walk8243.study.reactor.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import xyz.walk8243.study.reactor.domain.ApiResponse;
import xyz.walk8243.study.reactor.domain.Logger;

@Repository
public class CallApiRepository {

	/**
	 * 擬似的なAPI呼び出し (ランダムな時間がかかる)
	 * @param page 呼び出すページ番号
	 * @return APIのレスポンスを表すMono
	 */
	public Mono<ApiResponse> callApi(int page) {
		return Mono.fromCallable(() -> {
			long processingTime = 50 + (long) (Math.random() * 300); // 50ms〜350msの処理時間
			Logger.log("Calling API for page %d (processing will take %d ms)".formatted(page, processingTime));
			Thread.sleep(processingTime);

			if (page < 5) {
				return new ApiResponse(page, page + 1); // 次のページあり
			} else {
				return new ApiResponse(page, Optional.empty()); // 最後のページ
			}
		});
	}
}
