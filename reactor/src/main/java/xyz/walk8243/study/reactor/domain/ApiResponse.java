package xyz.walk8243.study.reactor.domain;

import java.util.Optional;

public record ApiResponse(int currentPage, Optional<Integer> nextPage) {
	public ApiResponse(int currentPage, Integer nextPage) {
		this(currentPage, Optional.ofNullable(nextPage));
	}
}
