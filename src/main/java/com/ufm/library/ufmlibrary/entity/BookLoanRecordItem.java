package com.ufm.library.ufmlibrary.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.ufm.library.ufmlibrary.entity.key.BookLoanRecordItemKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookLoanRecordItem {

    @EmbeddedId
    private BookLoanRecordItemKey bookLoanRecordItemKey;

    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 50)
    private String note;

    @ManyToOne
    @MapsId("locationBookKey")
    private LocationBook locationBook;

    @ManyToOne
    @MapsId("bookLoanRecordId")
    private BookLoanRecord bookLoanRecord;
}
