package com.test.testback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
}
