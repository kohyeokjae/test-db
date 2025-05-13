package com.test.testback.controller;

import com.test.testback.dto.request.PostRestaurantRequestDto;
import com.test.testback.dto.response.PostRestaurantResponseDto;
import com.test.testback.dto.response.ResponseDto;
import com.test.testback.dto.response.RestaurantResponseDto;
import com.test.testback.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<ResponseDto<PostRestaurantResponseDto>> createRestaurant(
            @RequestBody PostRestaurantRequestDto dto) {
        ResponseDto<PostRestaurantResponseDto> restaurant = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> getRestaurantById(@PathVariable Long id) {
        ResponseDto<RestaurantResponseDto> restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> getAllRestaurants() {
        ResponseDto<List<RestaurantResponseDto>> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> updatePost(
            @PathVariable Long id,
            @RequestBody PostRestaurantRequestDto dto
    ) {
        ResponseDto<RestaurantResponseDto> response = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteRestaurant(@PathVariable Long id) {
        ResponseDto<Void> response = restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
