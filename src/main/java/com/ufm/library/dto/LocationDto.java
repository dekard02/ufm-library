package com.ufm.library.dto;

import lombok.Data;

@Data
public class LocationDto {
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private Boolean isDeleted;
}
