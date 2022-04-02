package com.bccard.template.membership.sample.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "샘플 DTO")
@Data
public class SampleDTO {

	private String userId;
}
