package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.restaurant.RestaurantRequest;
import peaksoft.dto.response.restaurant.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

@Service
public interface RestaurantService {
    SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest);
    List<RestaurantResponse> getAllRestaurants();
    RestaurantResponse getRestaurantById(Long restaurantId);
   SimpleResponse updateRestaurant(Long restaurantId,RestaurantRequest restaurantRequest);
   SimpleResponse deleteRestaurant(Long restaurantId);


}
