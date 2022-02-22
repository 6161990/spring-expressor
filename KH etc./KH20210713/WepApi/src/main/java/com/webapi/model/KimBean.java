package com.webapi.model;

import lombok.Data;

@Data
public class KimBean {

    public KimBean(String string, int i) {
        this.count = i;
        this.Title = string;
    }
    private String Title;

    private int count;
}
