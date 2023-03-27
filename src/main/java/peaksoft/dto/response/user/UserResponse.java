package peaksoft.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class UserResponse {
    private Long id;
   private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private int experience;
}




