package com.test.testback.service;

import com.test.testback.dto.request.PostRestaurantRequestDto;
import com.test.testback.dto.response.PostRestaurantResponseDto;
import com.test.testback.dto.response.ResponseDto;

import com.test.testback.dto.response.RestaurantResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface RestaurantService {
    ResponseDto<PostRestaurantResponseDto> createRestaurant(PostRestaurantRequestDto dto);

    ResponseDto<RestaurantResponseDto> getRestaurantById(Long id);

    ResponseDto<List<RestaurantResponseDto>> getAllRestaurants();

    ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, PostRestaurantRequestDto dto);

    ResponseDto<Void> deleteRestaurant(Long id);
}
