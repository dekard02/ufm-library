package com.ufm.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import com.ufm.library.dto.BookLoanRecordDto.Request;
import com.ufm.library.dto.BookLoanRecordItemDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.BookLoanRecordItemMapper;
import com.ufm.library.dto.mapper.BookLoanRecordMapper;
import com.ufm.library.entity.BookLoanRecord;
import com.ufm.library.entity.Librarian;
import com.ufm.library.entity.QBookLoanRecord;
import com.ufm.library.entity.QBookReturnRecord;
import com.ufm.library.entity.Student;
import com.ufm.library.entity.key.LocationBookKey;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.BookLoanRecordItemRepository;
import com.ufm.library.repository.BookLoanRecordRepository;
import com.ufm.library.repository.LocationBookRepository;
import com.ufm.library.repository.StudentRepository;
import com.ufm.library.security.UserDetails;
import com.ufm.library.service.BookLoanRecordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookLoanRecordServiceImpl implements BookLoanRecordService {

    private final BookLoanRecordRepository bookLoanRecordRepo;
    private final BookLoanRecordItemRepository bookLoanRecordItemRepo;
    private final StudentRepository studentRepo;
    private final LocationBookRepository locationBookRepo;
    private final BookLoanRecordMapper bookLoanRecordMapper;
    private final BookLoanRecordItemMapper bookLoanRecordItemMapper;
    private final ResponseBodyHelper responseBodyHelper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResponseBody getAllBookLoanRecords(Predicate predicate, Pageable pageable, Boolean returned) {
        var finalPredicate = predicate;
        var userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (userDetails.getProfile() instanceof Student) {
            var student = (Student) userDetails.getProfile();
            finalPredicate = QBookLoanRecord.bookLoanRecord.student.id.eq(student.getId())
                    .and(finalPredicate);
        }

        if (returned != null && !returned)

        {
            finalPredicate = QBookLoanRecord.bookLoanRecord.id
                    .notIn(JPAExpressions
                            .selectFrom(QBookReturnRecord.bookReturnRecord)
                            .select(QBookReturnRecord.bookReturnRecord.bookLoanRecord.id))
                    .and(finalPredicate);
        } else if (returned != null && returned) {
            finalPredicate = QBookLoanRecord.bookLoanRecord.bookReturnRecord.id.isNotNull()
                    .and(finalPredicate);
        }

        var page = bookLoanRecordRepo.findAll(finalPredicate, pageable);
        var bookLoanRecords = page
                .stream()
                .map(bookLoanRecordMapper::bookLoanRecordTobookLoanRecordResDto)
                .collect(Collectors.toList());
        return responseBodyHelper.page(page, "bookLoanRecords", bookLoanRecords);
    }

    @Override
    public ResponseBody getBookLoanRecord(Long id) {
        var bookLoanRecordDto = bookLoanRecordRepo.findById(id)
                .map(bookLoanRecordMapper::bookLoanRecordToDetailDto)
                .orElseThrow(() -> new ApplicationException(
                        "Không tìm thấy phiếu mượn với mã " + id, HttpStatus.NOT_FOUND));

        var userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (userDetails.getProfile() instanceof Student) {
            if (bookLoanRecordDto.getStudent().getId() != userDetails.getUsername()) {
                throw new AccessDeniedException("Bạn không có quyền xem phiếu mượn này");
            }
        }

        return responseBodyHelper.success("bookLoanRecords", bookLoanRecordDto);
    }

    private void saveBookLoanRecordItems(List<BookLoanRecordItemDto.Request> items,
            BookLoanRecord loanRecord) {
        items.stream().forEach(item -> {
            var loanRecordItem = bookLoanRecordItemMapper.reqDtoToBookLoanRecord(
                    item,
                    loanRecord,
                    locationBookRepo);

            // decrease on loan number
            var optionalLocationBook = locationBookRepo.findById(
                    new LocationBookKey(item.getLocation(), item.getBook()));
            optionalLocationBook.ifPresent((locationBook) -> {
                var newOnLoan = locationBook.getBooksOnLoan() + item.getQuantity();
                locationBook.setBooksOnLoan(newOnLoan);
                locationBookRepo.save(locationBook);
            });

            loanRecord.getBookLoanRecordItems().add(loanRecordItem);
            bookLoanRecordItemRepo.save(loanRecordItem);
        });
    }

    @Override
    @Transactional
    public ResponseBody saveBookLoanRecord(Request bookLoanRecordDto) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        var librarian = (Librarian) userDetails.getProfile();

        var loanRecord = bookLoanRecordMapper.bookLoanRecordReqDtoToBookLoanRecord(
                bookLoanRecordDto,
                librarian,
                studentRepo);
        loanRecord.setId(null);
        bookLoanRecordRepo.save(loanRecord);

        saveBookLoanRecordItems(bookLoanRecordDto.getBooks(), loanRecord);

        return responseBodyHelper
                .success("bookLoanRecord",
                        bookLoanRecordMapper.bookLoanRecordToDetailDto(loanRecord));
    }

    @Override
    @Transactional
    public ResponseBody updateBookLoanRecord(Long id, Request bookLoanRecordDto) {
        var oldLoanRecord = bookLoanRecordRepo.findById(id)
                .orElseThrow(
                        () -> new ApplicationException("Không tìm thấy phiếu mượn với mã " + id,
                                HttpStatus.NOT_FOUND));

        var oldLoanBooks = oldLoanRecord.getBookLoanRecordItems();
        oldLoanBooks.forEach(item -> {
            var locationBook = item.getLocationBook();
            var onLoan = locationBook.getBooksOnLoan() - item.getQuantity();
            locationBook.setBooksOnLoan(onLoan);
            locationBookRepo.save(locationBook);
        });
        bookLoanRecordItemRepo.deleteAll(oldLoanBooks);
        oldLoanRecord.setBookLoanRecordItems(new ArrayList<>());

        var userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        var librarian = (Librarian) userDetails.getProfile();

        var loanRecord = bookLoanRecordMapper.bookLoanRecordReqDtoToBookLoanRecord(
                bookLoanRecordDto,
                librarian,
                studentRepo);
        loanRecord.setLoanDate(oldLoanRecord.getLoanDate());
        loanRecord.setId(id);
        bookLoanRecordRepo.save(loanRecord);

        saveBookLoanRecordItems(bookLoanRecordDto.getBooks(), loanRecord);

        return responseBodyHelper
                .success("bookLoanRecord",
                        bookLoanRecordMapper.bookLoanRecordToDetailDto(loanRecord));
    }

    @Override
    public void deleteBookLoanRecord(Long id) {
        bookLoanRecordRepo.findById(id).ifPresentOrElse(
                (loanRecord) -> {
                    if (loanRecord.getBookReturnRecord() != null) {
                        throw new ApplicationException("Không thể xóa phiếu mượn đã có phiếu trả",
                                HttpStatus.BAD_REQUEST);
                    } else {
                        bookLoanRecordRepo.delete(loanRecord);
                    }
                },
                () -> {
                    throw new ApplicationException("Không tìm thấy phiệu mượn với mã " + id,
                            HttpStatus.NOT_FOUND);
                });
    }

}
