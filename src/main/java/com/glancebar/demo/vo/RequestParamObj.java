package com.glancebar.demo.vo;

import com.glancebar.demo.validator.annotation.RequestParamConstraint;

import javax.validation.constraints.NotNull;

/**
 * @author YISHEN CAI
 */
@RequestParamConstraint
public class RequestParamObj {
    @NotNull(message = "page can't be null")
    private Integer page;

    @NotNull(message = "size can't be null")
    private Integer size;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
