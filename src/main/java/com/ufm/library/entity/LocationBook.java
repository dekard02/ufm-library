package com.ufm.library.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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
    @Builder.Default
    private LocationBookKey locationBookKey = new LocationBookKey();

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer booksOnLoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("locationId")
    private Location location;
}