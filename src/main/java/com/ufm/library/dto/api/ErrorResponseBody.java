package com.ufm.library.dto.api;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ErrorResponseBody {

    private String status;

    private String message;

    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = true)
    public static class ValidationFail extends ErrorResponseBody {

        @Builder.Default
        private Map<String, String> errors = new LinkedHashMap<>();
    }
}
