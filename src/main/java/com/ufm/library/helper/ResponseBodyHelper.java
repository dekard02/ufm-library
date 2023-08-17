package com.ufm.library.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.ufm.library.dto.api.ErrorResponseBody;
import com.ufm.library.dto.api.ResponseBody;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResponseBodyHelper {

    public ResponseBody success(String fieldName, Object value) {
        return ResponseBody.create()
                .addField("status", "success")
                .addField(fieldName, value);
    }

    public ErrorResponseBody error(String errorMessage) {
        return ErrorResponseBody.builder()
                .status("error")
                .message(errorMessage)
                .build();
    }

    public ErrorResponseBody fail(String failMessage) {
        return ErrorResponseBody.builder()
                .status("fail")
                .message(failMessage)
                .build();
    }

    public ErrorResponseBody.ValidationFail error(String message, Map<String, String> errors) {
        return ErrorResponseBody.ValidationFail.builder()
                .status("fail")
                .message(message)
                .errors(errors)
                .build();
    }

    public ResponseBody page(Page<?> page, String fieldName, List<?> list) {
        var pageInfo = new LinkedHashMap<String, Object>();
        pageInfo.put("pageNumber", page.getNumber() + 1);
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPages", page.getTotalPages());
        pageInfo.put("totalItems", page.getTotalElements());

        return ResponseBody.create()
                .addField("status", "success")
                .addField("page", pageInfo)
                .addField(fieldName, list);
    }
}
