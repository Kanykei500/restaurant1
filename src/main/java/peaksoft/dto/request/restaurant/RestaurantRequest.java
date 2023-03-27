package peaksoft.dto.request.restaurant;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record RestaurantRequest(
        @NotEmpty(message = "Name should not be null")
         String name,
         @NotEmpty(message = "location should not be null")
         String location,
         @NotEmpty(message = "rest type should not be null")
         String restType,
         @Min(value = 0,message = "It must not be a negative number")
        @NotEmpty(message = "service should not be null")
         int service

) {
}
