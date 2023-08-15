package com.ufm.library.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BookLoanRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String note;

    @ManyToOne
    private Librarian librarian;

    @ManyToOne
    private Student student;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Builder.Default
    @OneToMany(mappedBy = "bookLoanRecord", cascade = CascadeType.REMOVE)
    private List<BookLoanRecordItem> bookLoanRecordItems = new ArrayList<BookLoanRecordItem>();

    @OneToOne(mappedBy = "bookLoanRecord")
    private BookReturnRecord bookReturnRecord;
}
