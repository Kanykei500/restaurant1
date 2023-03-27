package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.restaurant.RestaurantRequest;
import peaksoft.dto.response.restaurant.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Restaurant;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.RestaurantService;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;



    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;


    }


    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant=new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setService(restaurantRequest.service());
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder().status(HttpStatus.OK).
                message(String.format("Restaurant with name : %s " +
                                "successfully saved",
                        restaurant.getName())).build();


    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
       return restaurantRepository.getAllRestaurant();

    }

    @Override
    public RestaurantResponse getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Restaurant with id: %s not found!", restaurantId)));
        if (restaurant.getUsers().size() <= 15) {
            restaurant.setNumberOfEmployees(restaurant.getUsers().size());
            restaurantRepository.save(restaurant);

        return restaurantRepository.getRestaurantById(restaurantId).
                orElseThrow(()->new NotFoundException(String.
                        format("Restaurant with id"+restaurantId+"doesn't exist")));
    } else {
            throw new AlreadyExistException("No vacancies");
        }
    }
    @Transactional
    @Override
    public SimpleResponse updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).
                orElseThrow(()->new NotFoundException(String.
                format("Restaurant with id"+restaurantId+"doesn't exist")));
        restaurant.setName(restaurantRequest.name());
       restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setService(restaurantRequest.service());
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .message("Successfully updated").build();

    }

    @Override
    public SimpleResponse deleteRestaurant(Long restaurantId) {
         restaurantRepository.deleteById(restaurantId);
         return SimpleResponse.builder().status(HttpStatus.OK).
                 message("Successfully deleted").build();
    }

//    @Override
//    public PaginationResponse getRestaurantPage(int page, int size) {
//        Pageable pageable= PageRequest.of(page-1, size, Sort.by(""));
//        Page<Restaurant> pageRestaurants = restaurantRepository.findAll(pageable);
//        PaginationResponse paginationResponse=new PaginationResponse();
//        paginationResponse.setRestaurants(pageRestaurants.getContent());
//        paginationResponse.setCurrentPage(pageable.getPageNumber());
//        paginationResponse.setPageSize(pageRestaurants.getTotalPages());
//
//        return paginationResponse;
    }



