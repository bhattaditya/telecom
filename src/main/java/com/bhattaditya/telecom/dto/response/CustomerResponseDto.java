package com.bhattaditya.telecom.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {

    private Integer id;
    private String name;
    private String email;
    private Long phoneNumber;
}
