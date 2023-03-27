package peaksoft.dto.response.stopList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class StopListResponse {
    private Long id;
    private String reason;
    private LocalDate date;

}
