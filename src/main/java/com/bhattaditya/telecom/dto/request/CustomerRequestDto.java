package com.bhattaditya.telecom.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRequestDto {

    private String name;
    private String email;
    private Long phoneNumber;
}
