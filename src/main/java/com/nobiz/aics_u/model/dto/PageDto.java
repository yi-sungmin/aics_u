package com.nobiz.aics_u.model.dto;

import lombok.Getter;

import java.net.BindException;
import java.util.regex.Pattern;

@Getter
public class PageDto extends UserSession {
    /* 공통 그리드 페이지 관련 객체 */

    String sortMn;
    String sortDir;
    int start;
    int length;
    int startRow;
    int endRow;

    public void setSortMn(String sortMn) throws BindException {
        if(getMatcher(sortMn)){
            throw new BindException("[Error] : invalid character");
        }
        this.sortMn = sortMn;
    }

    public void setSortDir(String sortDir) throws BindException {
        if(getMatcher(sortDir)){
            throw new BindException("[Error] : invalid character");
        }
        this.sortDir = sortDir;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setPaging(){
        startRow = start + 1;
        endRow = start + length;
    }

    private boolean getMatcher(String str) {
    	return str != null && p.matcher(str).find();
    }
    Pattern p = Pattern.compile("[^a-zA-Z_]");

}
