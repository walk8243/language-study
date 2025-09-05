package xyz.walk8243.study.reactor.domain;

public class Logger {
	public static void log(String message) {
		System.out.printf("[%-18s] %s%n", java.time.LocalTime.now(), message);
	}
}
