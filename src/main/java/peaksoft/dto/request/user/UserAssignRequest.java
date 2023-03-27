package peaksoft.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserAssignRequest(
        @NotEmpty(message = "user id should not be null")
        Long userId,
        @NotEmpty(message = "restaurant id should not be null")
        Long restaurantId

) {
}
