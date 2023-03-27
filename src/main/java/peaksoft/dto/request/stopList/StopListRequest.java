package peaksoft.dto.request.stopList;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public record StopListRequest(
        @NotEmpty(message = "reason should not be null")
        String reason,
        @NotEmpty(message = "date should not be null")

        LocalDate date,
        @NotEmpty(message = "menuItem id should not be null")


        Long menuItemId
) {
}
