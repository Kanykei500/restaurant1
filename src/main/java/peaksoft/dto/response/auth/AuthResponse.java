package peaksoft.dto.response.auth;

import lombok.Builder;

@Builder
public record AuthResponse(
        String email,
        String token
) {
}
