package com.ufm.library.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLoanRecordItemKey implements Serializable {
    @Column(name = "book_loan_record_id")
    private Long bookLoanRecordId;

    @Embedded
    private LocationBookKey locationBookKey = new LocationBookKey();

}
