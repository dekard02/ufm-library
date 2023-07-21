package com.ufm.library.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ufm.library.entity.key.LocationBookKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationBook {

    @EmbeddedId
    @JsonProperty(access = Access.WRITE_ONLY)
    @Builder.Default
    private LocationBookKey locationBookKey = new LocationBookKey();

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer booksOnLoan;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne
    @MapsId("bookId")
    private Book book;

    @ManyToOne
    @MapsId("locationId")
    private Location location;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(mappedBy = "locationBook")
    private Collection<BookLoanRecordItem> bookLoanRecordItems;

}