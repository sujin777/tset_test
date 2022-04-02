package com.bccard.template.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommonDTO implements Serializable {

	private static final long serialVersionUID = -3644478902715276246L;

	// 샘플성. header의 txId
	private String hTxId;

}
