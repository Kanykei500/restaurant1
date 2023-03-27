package peaksoft.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import peaksoft.validation.PasswordValid;

@Builder
public record AuthRequest(
        @NotEmpty(message = "email should not be null")
        @Email(message = "email should be valid")
        String email,
       @NotEmpty(message = "password should not be null")
        @PasswordValid(message = "password should be valid")
        String password
) {
}
