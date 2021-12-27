package xyz.walk8243.languagestudy.async.service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.walk8243.languagestudy.async.domain.Sleep;
import xyz.walk8243.languagestudy.async.domain.SleepRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class Schedule {
	
	private final Sleep sleep;
	private static List<SleepRequest> REQUESTS = Arrays.asList(
		SleepRequest.builder().name("sleep1").seconds(5).build(),
		SleepRequest.builder().name("sleep2").seconds(1).build(),
		SleepRequest.builder().name("sleep3").seconds(3).build()
	);

	@Scheduled(fixedRate = 10000)
	public void doParallel() {
		final List<String> results = REQUESTS.parallelStream()
			.map(request -> sleep.sleep(request.getName(), request.getSeconds()))
			.map(CompletableFuture::join)
			.collect(Collectors.toList());

		log.info(results.toString());
	}
}
