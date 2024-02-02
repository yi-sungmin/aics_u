package com.nobiz.aics_u.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExcelDto<T> {
    private String originalFileName;
    private String storedFileName;
    private String storedFileFullPath;

    private List<T> data;
    private Class<T> type;

    public ExcelDto(String originalFileName, List<T> data, Class<T> type) {
        this.originalFileName = originalFileName;
        this.data = data;
        this.type = type;
    }

    public ExcelDto(List<T> data, Class<T> type) {
        this.data = data;
        this.type = type;
    }
}
