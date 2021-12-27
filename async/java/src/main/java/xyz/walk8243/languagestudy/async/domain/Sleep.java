package xyz.walk8243.languagestudy.async.domain;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Sleep {
	
	@SneakyThrows
	public CompletableFuture<String> sleep(String name, Integer seconds) {
		log.info("非同期処理の開始 name: " + name + " seconds: " + String.valueOf(seconds));
		Thread.sleep(seconds * 1000L);
		log.info("非同期処理の終了 name: " + name + " seconds: " + String.valueOf(seconds));

		return CompletableFuture.completedFuture(name);
	}
}
