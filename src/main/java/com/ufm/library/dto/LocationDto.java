package com.ufm.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private Boolean isDeleted;
}
