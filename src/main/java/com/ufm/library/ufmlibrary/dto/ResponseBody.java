package com.ufm.library.ufmlibrary.dto;

import java.util.LinkedHashMap;

public class ResponseBody extends LinkedHashMap<String, Object> {
    private ResponseBody() {
    }

    public static ResponseBody create() {
        return new ResponseBody();
    }

    public ResponseBody addField(String fieldName, Object value) {
        this.put(fieldName, value);
        return this;
    }
}
