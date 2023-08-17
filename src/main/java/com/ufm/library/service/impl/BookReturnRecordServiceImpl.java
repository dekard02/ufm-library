package com.ufm.library.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.BookReturnRecordDto.Request;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.BookReturnRecordMapper;
import com.ufm.library.entity.Librarian;
import com.ufm.library.entity.QBookLoanRecord;
import com.ufm.library.entity.Student;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.exception.NotFoundException;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.BookLoanRecordRepository;
import com.ufm.library.repository.BookReturnRecordRepository;
import com.ufm.library.repository.LocationBookRepository;
import com.ufm.library.security.UserDetails;
import com.ufm.library.service.BookReturnRecordService;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class BookReturnRecordServiceImpl implements BookReturnRecordService {

    private final BookReturnRecordRepository bookReturnRecordRepo;
    private final BookLoanRecordRepository bookLoanRecordRepo;
    private final LocationBookRepository locationBookRepo;
    private final BookReturnRecordMapper bookReturnRecordMapper;
    private final ResponseBodyHelper responseBodyHelper;

    @Override
    public ResponseBody getAllBookReturnRecords(Predicate predicate, Pageable pageable) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        var finalPredicate = predicate;
        if (userDetails.getProfile() instanceof Student) {
            var student = (Student) userDetails.getProfile();
            finalPredicate = QBookLoanRecord.bookLoanRecord.student.id.eq(student.getId())
                    .and(finalPredicate);
        }

        var page = bookReturnRecordRepo.findAll(finalPredicate, pageable);
        var returnRecords = page.getContent()
                .stream()
                .map(bookReturnRecordMapper::bookReturnRecordToResDto)
                .collect(Collectors.toList());
        return responseBodyHelper.page(page, "bookReturnRecords", returnRecords);
    }

    @Override
    public ResponseBody getBookReturnRecord(Long id) {
        var returnRecordDto = bookReturnRecordRepo.findById(id)
                .map(bookReturnRecordMapper::bookReturnRecordToDetailDto)
                .orElseThrow(() -> new NotFoundException(
                        "Không tìm thấy phiếu trả với mã " + id));

        var userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (userDetails.getProfile() instanceof Student) {
            var predicate = QBookLoanRecord.bookLoanRecord.id
                    .eq(returnRecordDto.getBookLoanRecordId())
                    .and(QBookLoanRecord.bookLoanRecord.student.id
                            .eq(userDetails.getUsername()));
            boolean canAcess = bookLoanRecordRepo.exists(predicate);
            if (!canAcess) {
                throw new ApplicationException("Bạn không có quyền xem phiếu trả này",
                        HttpStatus.FORBIDDEN);
            }
        }

        return responseBodyHelper.success("bookReturnRecord", returnRecordDto);
    }

    private void decreaseOnLoan(Long loanRecordId) {
        bookLoanRecordRepo.findById(loanRecordId)
                .ifPresent(loanRecord -> {
                    loanRecord.getBookLoanRecordItems().forEach(item -> {
                        var locationBook = item.getLocationBook();
                        var newOnLoan = locationBook.getBooksOnLoan() - item.getQuantity();
                        locationBook.setBooksOnLoan(newOnLoan);
                        locationBookRepo.save(locationBook);
                    });
                });
    }

    @Override
    @Transactional
    public ResponseBody saveBookReturnRecord(Request bookReturnRecordDto) {
        bookLoanRecordRepo.findById(bookReturnRecordDto.getBookLoanRecord())
                .ifPresent(record -> {
                    if (record.getBookReturnRecord() != null) {
                        throw new ApplicationException("Phiếu mượn với mã " + record.getId()
                                + " đã có phiếu trả",
                                HttpStatus.BAD_REQUEST);
                    }
                });

        var userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        var librarian = (Librarian) userDetails.getProfile();

        decreaseOnLoan(bookReturnRecordDto.getBookLoanRecord());

        var returnRecord = bookReturnRecordMapper.bookReturnRecordReqDtoToBookReturnRecord(
                bookReturnRecordDto,
                librarian,
                bookLoanRecordRepo);
        returnRecord.setId(null);

        bookReturnRecordRepo.save(returnRecord);

        return responseBodyHelper
                .success("bookReturnRecord",
                        bookReturnRecordMapper.bookReturnRecordToDetailDto(returnRecord));
    }

    @Override
    @Transactional
    public ResponseBody updateBookReturnRecord(Long id, Request bookReturnRecordDto) {
        var oldReturnRecord = bookReturnRecordRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu trả với mã " + id));

        bookLoanRecordRepo.findById(bookReturnRecordDto.getBookLoanRecord())
                .ifPresent(record -> {
                    if (record.getBookReturnRecord() != null
                            && record.getId() != oldReturnRecord.getBookLoanRecord()
                                    .getId()) {
                        throw new ApplicationException(
                                "Phiếu mượn với mã " + record.getId()
                                        + " đã có phiếu trả",
                                HttpStatus.BAD_REQUEST);
                    }
                });

        oldReturnRecord.getBookLoanRecord()
                .getBookLoanRecordItems()
                .forEach(item -> {
                    var locationBook = item.getLocationBook();
                    var onLoan = locationBook.getBooksOnLoan() + item.getQuantity();
                    locationBook.setBooksOnLoan(onLoan);
                    locationBookRepo.save(locationBook);
                });

        var librarian = (Librarian) SecurityContextHolder.getContext()
                .getAuthentication()
                .getCredentials();
        decreaseOnLoan(bookReturnRecordDto.getBookLoanRecord());

        var returnRecord = bookReturnRecordMapper.bookReturnRecordReqDtoToBookReturnRecord(
                bookReturnRecordDto,
                librarian,
                bookLoanRecordRepo);
        returnRecord.setReturnDate(oldReturnRecord.getReturnDate());
        returnRecord.setId(id);
        bookReturnRecordRepo.save(returnRecord);

        return responseBodyHelper
                .success("bookReturnRecord",
                        bookReturnRecordMapper.bookReturnRecordToDetailDto(returnRecord));
    }

    @Override
    public void deleteBookReturnRecord(Long id) {
        var returnRecord = bookReturnRecordRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Không tìm thấy phiếu trả với mã " + id));
        bookReturnRecordRepo.delete(returnRecord);
    }

}
