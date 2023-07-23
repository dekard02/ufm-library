package com.ufm.library.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.ufm.library.dto.api.ResponseBody;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResponseBodyHelper {

    public ResponseBody success(String fieldName, Object value) {
        return ResponseBody.create()
                .addField("message", "success")
                .addField(fieldName, value);
    }

    public ResponseBody error(String errorMessage) {
        return ResponseBody.create()
                .addField("status", "error")
                .addField("message", errorMessage);
    }

    public ResponseBody fail(String failMessage) {
        return ResponseBody.create()
                .addField("status", "fail")
                .addField("message", failMessage);
    }

    public ResponseBody error(String message, Map<String, String> errors) {
        return this.error(message)
                .addField("errors", errors);
    }

    public ResponseBody page(Page<?> page, String fieldName, List<?> list) {
        var pageInfo = new HashMap<>();
        pageInfo.put("pageNumber", page.getNumber());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPages", page.getTotalPages());
        pageInfo.put("totalItems", page.getTotalElements());

        return ResponseBody.create()
                .addField("status", "success")
                .addField("page", pageInfo)
                .addField(fieldName, list);
    }
}
