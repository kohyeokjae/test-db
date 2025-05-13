package com.test.testback.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMenuRequestDto {
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private String description;
    @NotNull
    private Long restaurantId;
}
