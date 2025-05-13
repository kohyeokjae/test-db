package com.test.testback.service.serviceImpl;

import com.test.testback.dto.request.PostRestaurantRequestDto;
import com.test.testback.dto.response.*;
import com.test.testback.entity.Menu;
import com.test.testback.entity.Restaurant;
import com.test.testback.repository.RestaurantRepository;
import com.test.testback.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public ResponseDto<PostRestaurantResponseDto> createRestaurant(PostRestaurantRequestDto dto) {
        PostRestaurantResponseDto responseDto = null;

        try {
            Restaurant newRestaurant = Restaurant.builder()
                    .name(dto.getName())
                    .address(dto.getAddress())
                    .phoneNumber(dto.getPhoneNumber())
                    .build();

            Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

            responseDto = PostRestaurantResponseDto.builder()
                    .id(savedRestaurant.getId())
                    .name(savedRestaurant.getName())
                    .address(savedRestaurant.getAddress())
                    .phoneNumber(savedRestaurant.getPhoneNumber())
                    .build();

            return ResponseDto.setSuccess("레스토랑 등록 완료", responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("레스토랑 등록 실패" + e.getMessage());
        }

    }

    @Override
    public ResponseDto<RestaurantResponseDto> getRestaurantById(Long id) {
        RestaurantResponseDto responseDto = null;
        try {

            Restaurant restaurant = restaurantRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("레스토랑 not found with id: " + id));

            responseDto = RestaurantResponseDto.builder()
                    .id(restaurant.getId())
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .phoneNumber(restaurant.getPhoneNumber())
                    .build();


            return ResponseDto.setSuccess("레스트로랑 조회에 성공하였습니다.", responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("레스토랑 조회에 실패하였습니다: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<List<RestaurantResponseDto>> getAllRestaurants() {
        List<RestaurantResponseDto> responseDtos = null;

        try {
            List<Restaurant> restaurants = restaurantRepository.findAll();

            responseDtos = restaurants.stream()
                    .map(restaurant -> RestaurantResponseDto.builder()
                            .id(restaurant.getId())
                            .name(restaurant.getName())
                            .address(restaurant.getAddress())
                            .phoneNumber(restaurant.getPhoneNumber())
                            .build())
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("전체 조회에 성공하였습니다.", responseDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("전체 조회에 실패하였습니다.: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, PostRestaurantRequestDto dto) {
        RestaurantResponseDto responseDto = null;

        try {
            Restaurant restaurant = restaurantRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("레스트랑 not found with id: " + id));

            restaurant.setName(dto.getName());
            restaurant.setAddress(dto.getAddress());
            restaurant.setPhoneNumber(dto.getPhoneNumber());

            Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

            responseDto = RestaurantResponseDto.builder()
                    .id(updatedRestaurant.getId())
                    .name(updatedRestaurant.getName())
                    .address(updatedRestaurant.getAddress())
                    .phoneNumber(updatedRestaurant.getPhoneNumber())
                    .build();

            return ResponseDto.setSuccess("수정에 성공하였습니다.", responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("수정에 실패하였습니다: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<Void> deleteRestaurant(Long id) {
        try {
            Restaurant restaurant = restaurantRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("레스토랑 not found with id: " + id));

            List<Menu> Menus = new ArrayList<>(restaurant.getMenus());
            for (Menu menu : Menus) {
                restaurant.removeRestaurant(menu);
            }

            restaurantRepository.deleteById(id);

            return ResponseDto.setSuccess("삭제 성공하였습니다.", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("삭제 실패하였습니다.: " + e.getMessage());
        }

    }
}
