package peaksoft.dto.request.subCategory;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record SubCategoryRequest(
        @NotEmpty(message = "Name should not be null")
        String name,
        @NotEmpty(message = "category id should not be null")
        Long categoryId
) {
}
