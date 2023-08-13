package com.ufm.library.exception.hanlder;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.stream.Stream;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.helper.ResponseBodyHelper;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler {

    private final ResponseBodyHelper responseBodyHelper;

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<ResponseBody> handle(ApplicationException ex) {
        ResponseBody responseBody;
        if (ex.getStatus().is5xxServerError()) {
            responseBody = responseBodyHelper.error(ex.getMessage());
        } else {
            responseBody = responseBodyHelper.fail(ex.getMessage());
        }

        return ResponseEntity
                .status(ex.getStatus())
                .body(responseBody);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBody validationExceptionHandle(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getAllErrors().forEach(objectError -> {
            String fieldName;
            if (objectError instanceof FieldError) {
                fieldName = ((FieldError) objectError).getField();
            } else {
                fieldName = objectError.getObjectName();
            }
            var errorMessage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return responseBodyHelper
                .error("Dữ liệu nhập vào không hợp lệ", errors);
    }

    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(HttpMessageNotReadableException.class)
    // public ResponseBody jsonParseExceptionHandle(HttpMessageNotReadableException
    // ex) {
    // var errorMessage = ex.getLocalizedMessage();
    //
    // if (errorMessage.startsWith("JSON parse error")) {
    // return responseBodyHelper.error("Invalid value format, type.");
    // }
    // if (errorMessage.startsWith("Required request body is missing")) {
    // return responseBodyHelper.error("Request body is require");
    // }
    //
    // return responseBodyHelper.error(errorMessage);
    // }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseBody propertyReferenceExceptionHandler(PropertyReferenceException ex) {
        return responseBodyHelper.error("Không tim thấy trường " + ex.getPropertyName());
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseBody methodNotSupportedHandler(HttpRequestMethodNotSupportedException ex) {
        return responseBodyHelper
                .fail("Request method " + ex.getMethod() + " not support on this route");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseBody dataIntegrityViolationException(DataIntegrityViolationException ex) {
        var message = "Đã xảy ra lỗi";

        if (ex.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
            var cause = (SQLIntegrityConstraintViolationException) ex.getRootCause();
            if (cause.getErrorCode() == 1062) {
                var value = Stream.of(cause.getMessage().split(" "))
                        .filter(each -> each.startsWith("'") && each.endsWith("'"))
                        .findFirst()
                        .get();
                message = "Đã tồn tại giá trị " + value;
            }
            if (cause.getErrorCode() == 1452) {
                var fieldName = Stream.of(
                        cause.getLocalizedMessage()
                                .split("REFERENCES")[1]
                                .split(" "))
                        .filter(each -> each.startsWith("`") && each.endsWith("`"))
                        .findFirst()
                        .get()
                        .replace("`", "");
                message = "Đã xảy ra lỗi, không thể thêm trường " + fieldName;
            }
        }

        return responseBodyHelper
                .fail(message);
    }

}