package com.nobiz.aics_u.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {
    /* 성공했을 경우 담는 객체 */

    private T data;
    private String message;
    public ResponseDto(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
