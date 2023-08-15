package com.ufm.library.validation.validator;

import java.util.List;
import java.util.stream.IntStream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ufm.library.dto.BookLoanRecordItemDto;
import com.ufm.library.validation.annotation.ValidRecordItems;

public class RecordItemsValidator
                implements ConstraintValidator<ValidRecordItems, List<BookLoanRecordItemDto.Request>> {

        @Override
        public boolean isValid(List<BookLoanRecordItemDto.Request> items,
                        ConstraintValidatorContext context) {
                var message = "";
                var totalQuantity = items
                                .stream()
                                .flatMapToInt(item -> IntStream.of(item.getQuantity()))
                                .sum();
                if (totalQuantity > 2) {
                        message += "Số lượng sách mượn tối đa là 2";
                }

                var firstItem = items.get(0);

                var hasOneLocation = items.stream()
                                .allMatch(item -> item.getLocation() == firstItem.getLocation());
                if (!hasOneLocation) {
                        message += message.equals("")
                                        ? ". "
                                        : "";
                        message += "Sách mượn phải ở cùng một cơ sở";
                }
                if (!hasOneLocation) {
                        message += message.equals("")
                                        ? ""
                                        : ". ";
                        message += "Sách mượn phải ở cùng một cơ sở";
                }

                var uniqueLocationBook = items.stream()
                                .anyMatch(item -> item.getLocation() == firstItem.getLocation()
                                                && item.getBook() == firstItem.getBook());
                if (!uniqueLocationBook) {
                        message += message.equals("")
                                        ? ""
                                        : ". ";
                        message += "Sách mượn bị trùng nhau";
                }
                context.buildConstraintViolationWithTemplate(message)
                                .addConstraintViolation()
                                .disableDefaultConstraintViolation();
                return totalQuantity > 2 && !hasOneLocation;
        }
}
