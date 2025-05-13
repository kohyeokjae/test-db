package com.test.testback.service.serviceImpl;

import com.test.testback.dto.request.PostMenuRequestDto;
import com.test.testback.dto.request.PostRestaurantRequestDto;
import com.test.testback.dto.response.MenuResponseDto;
import com.test.testback.dto.response.PostMenuResponseDto;
import com.test.testback.dto.response.ResponseDto;
import com.test.testback.dto.response.RestaurantResponseDto;
import com.test.testback.entity.Menu;
import com.test.testback.entity.Restaurant;
import com.test.testback.repository.MenuRepository;
import com.test.testback.repository.RestaurantRepository;
import com.test.testback.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public ResponseDto<PostMenuResponseDto> createMenu(RestaurantResponseDto restaurantDto, PostMenuRequestDto dto) {
        PostMenuResponseDto responseDto = null;

        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("레스토랑 not found with id: " + restaurantDto.getId()));


            Menu newMenu = Menu.builder()
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .description(dto.getDescription())
                    .build();

            restaurant.addMenu(newMenu);

            Menu savedMenu = menuRepository.save(newMenu);

            responseDto = PostMenuResponseDto.builder()
                    .id(savedMenu.getId())
                    .name(savedMenu.getName())
                    .price(savedMenu.getPrice())
                    .description(savedMenu.getDescription())
                    .restaurant(RestaurantResponseDto.builder()
                            .id(restaurant.getId())
                            .name(restaurant.getName())
                            .address(restaurant.getAddress())
                            .phoneNumber(restaurant.getPhoneNumber())
                            .build())
                    .build();

            return ResponseDto.setSuccess("메뉴가 등록되었습니다. ", responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("메뉴 등록에 실패하였습니다." + e.getMessage());
        }
    }

    @Override
    public ResponseDto<List<MenuResponseDto>> findByIdRestaurant(RestaurantResponseDto restaurantDto) {
        List<MenuResponseDto> responseDtos = null;

        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("레스트로 not found with id: " + restaurantDto.getId()));

            responseDtos = restaurant.getMenus().stream()
                    .map(menu -> MenuResponseDto.builder()
                            .id(menu.getId())
                            .name(menu.getName())
                            .price(menu.getPrice())
                            .description(menu.getDescription())
                            .build())
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("전체 조회에 성공하였습니다.", responseDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("전체 조회에 실패하였습니다.: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<MenuResponseDto> getByIdMenu(Long id) {
        MenuResponseDto responseDto = null;
        try {
            Menu menu = menuRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("메뉴 not found with id: " + id));

            responseDto = MenuResponseDto.builder()
                    .id(menu.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .description(menu.getDescription())
                    .build();

            return ResponseDto.setSuccess("메뉴 조회에 성공하였습니다.", responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("메뉴 조회에 실패하였습니다: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<MenuResponseDto> updateMenu(Long id, PostMenuRequestDto dto) {
        MenuResponseDto responseDto = null;
        try {
            Menu menu = menuRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("메뉴 not found with id: " + id));

            menu.setName(dto.getName());
            menu.setPrice(dto.getPrice());
            menu.setDescription(dto.getDescription());

            Menu updatedMenu = menuRepository.save(menu);

            responseDto = MenuResponseDto.builder()
                    .id(updatedMenu.getId())
                    .name(updatedMenu.getName())
                    .price(updatedMenu.getPrice())
                    .description(updatedMenu.getDescription())
                    .build();


            return ResponseDto.setSuccess("수정에 성공하였습니다.", responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("수정에 실패하였습니다: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<Void> deleteMenu(Long id) {
        try {
            Menu menu = menuRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("메뉴 not found with id: " + id));

            menuRepository.deleteById(id);

            return ResponseDto.setSuccess("삭제 성공하였습니다.", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("삭제 실패하였습니다.: " + e.getMessage());
        }
    }
}
