package xyz.walk8243.study.reactor.controller;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import xyz.walk8243.study.reactor.domain.ApiResponse;
import xyz.walk8243.study.reactor.repository.CallApiRepository;

@RestController
public class ZipController {
	@Autowired
	private CallApiRepository callApiRepository;

	@GetMapping("/parallel")
	public Flux<ApiResponse> parallel() {
		int callCount = 5; // 並列で呼び出す回数
		return Flux.range(1, callCount)
			.parallel(callCount)
			.runOn(Schedulers.parallel())
			.flatMap(i -> callApiRepository.callApi(i)) // callApi()はMono<ApiResponse>を返す想定
			.sorted(Comparator.comparingInt(ApiResponse::currentPage)); // 結果をページ順にソート
	}

	@GetMapping("/sequential")
	public Flux<ApiResponse> sequential() {
		int callCount = 5; // 順次呼び出す回数
		return Flux.range(1, callCount)
			.publishOn(Schedulers.single())
			.flatMap(i -> callApiRepository.callApi(i)); // callApi()はMono<ApiResponse>を返す想定
	}

	@GetMapping("/zip")
	public Flux<ApiResponse> zip() {
		final Mono<ApiResponse> apiCall1 = callApiRepository.callApi(1);
		final Mono<ApiResponse> apiCall2 = callApiRepository.callApi(2);
		return Mono.zip(apiCall1, apiCall2)
			.flatMapMany(tuple -> {
				ApiResponse response1 = tuple.getT1();
				ApiResponse response2 = tuple.getT2();
				// ここでresponse1とresponse2を使った処理を行う
				return Flux.just(response1, response2);
			});
	}
}
