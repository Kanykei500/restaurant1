package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import peaksoft.dto.response.restaurant.RestaurantResponse;

import peaksoft.entity.Restaurant;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("select new peaksoft.dto.response.restaurant.RestaurantResponse(r.id,r.name, r.location, r.restType ,r.service )from Restaurant r")
   List<RestaurantResponse> getAllRestaurant();
    @Query("select new peaksoft.dto.response.restaurant.RestaurantResponse(r.id,r.name, r.location, r.restType ,r.service ,r.numberOfEmployees)from Restaurant r  where r.id =?1")
    Optional<RestaurantResponse> getRestaurantById(Long restaurantId );

    @Override
    Page<Restaurant> findAll(Pageable pageable);
}