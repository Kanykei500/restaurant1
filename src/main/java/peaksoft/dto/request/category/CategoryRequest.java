package peaksoft.dto.request.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotEmpty(message = "Name should not be null")
        String name

) {
}
