package com.ufm.library.ufmlibrary.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationBookKey implements Serializable {
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "book_id")
    private Long bookId;
}
