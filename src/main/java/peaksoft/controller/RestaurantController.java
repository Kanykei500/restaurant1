package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.restaurant.RestaurantRequest;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.restaurant.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveRestaurant(@Valid  @RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.saveRestaurant(restaurantRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<RestaurantResponse> getAllRestaurant() {
        return restaurantService.getAllRestaurants();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{restaurantId}")
    public RestaurantResponse getRestaurantById(@PathVariable Long restaurantId) {

        return restaurantService.getRestaurantById(restaurantId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{restaurantId}")
    public SimpleResponse update(@Valid @PathVariable Long restaurantId, @RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.updateRestaurant(restaurantId, restaurantRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{restaurantId}")
    public SimpleResponse delete(@PathVariable Long restaurantId) {
        return restaurantService.deleteRestaurant(restaurantId);
    }
}




