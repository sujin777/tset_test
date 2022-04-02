package com.bccard.template.membership.sample.entity;



import com.bccard.template.common.dto.ResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "샘플 Entity", implementation = ResponseDTO.class)
@Data
public class Sample {

	@JsonIgnore
	@Schema(description = "이름")
	private String name;

	@Schema(description = "아이디")
	//@NotNull
	private String userId;
}
