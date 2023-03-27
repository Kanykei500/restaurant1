package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.auth.AuthRequest;
import peaksoft.dto.response.auth.AuthResponse;
@Service
public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
}
