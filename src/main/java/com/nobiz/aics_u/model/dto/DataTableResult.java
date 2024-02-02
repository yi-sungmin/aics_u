package com.nobiz.aics_u.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableResult<T> {
    /* jquery dataTables 로 반환할 객체 */

    // 전체 데이터
    private T data;
    // 총 개수
    private int recordsFiltered;
    public DataTableResult(T data, int recordsFiltered) {
        this.data = data;
        this.recordsFiltered = recordsFiltered;
    }
}
