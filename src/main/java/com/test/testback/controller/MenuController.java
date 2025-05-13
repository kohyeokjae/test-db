package com.test.testback.controller;

import com.test.testback.dto.request.PostMenuRequestDto;
import com.test.testback.dto.response.MenuResponseDto;
import com.test.testback.dto.response.PostMenuResponseDto;
import com.test.testback.dto.response.ResponseDto;
import com.test.testback.dto.response.RestaurantResponseDto;
import com.test.testback.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant/{id}/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<ResponseDto<PostMenuResponseDto>> createMenu(
            RestaurantResponseDto restaurant,
            @RequestBody PostMenuRequestDto dto) {
        ResponseDto<PostMenuResponseDto> responseDto = menuService.createMenu(restaurant, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> getByIdMenu(RestaurantResponseDto responseDto, @PathVariable Long menuId) {
        ResponseDto<MenuResponseDto> response = menuService.getByIdMenu(responseDto.getId(), menuId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> findByIdRestaurant(
            RestaurantResponseDto restaurant) {
        ResponseDto<List<MenuResponseDto>> responseDto = menuService.findByIdRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    @PutMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            RestaurantResponseDto responseDto,
            @PathVariable Long menuId,
            @RequestBody PostMenuRequestDto dto) {
        ResponseDto<MenuResponseDto> response = menuService.updateMenu(responseDto.getId(), menuId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(RestaurantResponseDto responseDto, @PathVariable Long menuId) {
        ResponseDto<Void> response = menuService.deleteMenu(responseDto.getId(), menuId);
        return ResponseEntity.noContent().build();
    }
}
