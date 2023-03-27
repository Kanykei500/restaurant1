package peaksoft.dto.request.menuItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record MenuItemRequest(
        @NotEmpty(message = "Name should not be null")
        String name,
        @NotEmpty(message = "image should not be null")
        String image,
        @Min(value = 0,message = "It must not be a negative number")
        @NotEmpty(message = "price should not be null")
        int price,
        @NotEmpty(message = "description should not be null")
        String description,
        @NotEmpty(message = "is vegetarian should not be null")
        Boolean isVegetarian,
        @NotEmpty(message = "restaurant id should not be null")
        Long restaurantId,
        @NotEmpty(message = "sub category id should not be null")
        Long subCategoryId
) {
}
