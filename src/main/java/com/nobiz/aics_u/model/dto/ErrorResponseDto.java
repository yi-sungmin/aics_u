package com.nobiz.aics_u.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    /* 오류 결과 객체 */

    private Date timestamp;
    private int status;
    private String message;
}
