package xyz.walk8243.languagestudy.async.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SleepRequest {
	private String name;
	private Integer seconds;
}
