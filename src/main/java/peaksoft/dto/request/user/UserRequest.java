package peaksoft.dto.request.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import peaksoft.enums.Role;
import peaksoft.validation.PasswordValid;
import peaksoft.validation.PhoneNumberValid;

import java.time.LocalDate;
@Builder


public record UserRequest(
        @NotEmpty(message = "FirstName should not be null")
        String firstName,
        @NotEmpty(message = "LastName should not be null")
        String lastName,
         @NotEmpty(message = "Date Of Birth should not be null")
         LocalDate dateOfBirth,
         @Email(message = "email should  be valid")
                 @NotEmpty(message = "email should not be null")
         String email,
        @PasswordValid
                @NotEmpty(message = "password should not be null")
        String password,

         @PhoneNumberValid
                 @NotEmpty(message = "PhoneNumber should not be null")
         String phoneNumber,
         @NotEmpty(message = "role should not be null")

         Role role,
         @NotEmpty(message = "experience should not be null")
         @Min(value = 0,message = "It must not be a negative number")
         int experience

) {
}
