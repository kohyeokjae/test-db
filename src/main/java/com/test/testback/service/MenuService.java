package com.test.testback.service;

import com.test.testback.dto.request.PostMenuRequestDto;
import com.test.testback.dto.response.MenuResponseDto;
import com.test.testback.dto.response.PostMenuResponseDto;
import com.test.testback.dto.response.ResponseDto;
import com.test.testback.dto.response.RestaurantResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface MenuService {
    ResponseDto<PostMenuResponseDto> createMenu(RestaurantResponseDto restaurant, PostMenuRequestDto dto);

    ResponseDto<List<MenuResponseDto>> findByIdRestaurant(RestaurantResponseDto restaurant);

    ResponseDto<MenuResponseDto> getByIdMenu(Long id);

    ResponseDto<MenuResponseDto> updateMenu(Long id, PostMenuRequestDto dto);

    ResponseDto<Void> deleteMenu(Long restaurantId, Long id);
}
