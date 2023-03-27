package peaksoft.dto.request.cheque;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChequeRequest{
    @NotEmpty(message = "waiter id should not be null")
       private Long waiterId;
    @NotEmpty(message = "menuItems should not be null")
      private  List<Long>menuItems;
        }


