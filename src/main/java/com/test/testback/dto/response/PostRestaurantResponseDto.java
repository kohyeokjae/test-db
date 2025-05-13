package com.test.testback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostRestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
}
