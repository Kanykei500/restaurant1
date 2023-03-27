package peaksoft.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.request.auth.AuthRequest;
import peaksoft.dto.response.auth.AuthResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthService;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
          authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()
                )
        );

        User user =userRepository.findByEmail(authRequest.email())
                .orElseThrow(() -> new NotFoundException(String.format
                        ("User with email: %s doesn't exists", authRequest.email())));
        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }
    @PostConstruct
    public void init() {
        User user=new User();
        user.setEmail("admin@gmail.com");
        user.setFirstName("Kanykei");
        user.setLastName("Askarbekova");
        user.setDateOfBirth(LocalDate.of(2003,3,18));
        user.setExperience(1);
        user.setPhoneNumber("0990128880");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRole(Role.ADMIN);
        if (!userRepository.existsUserByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }
}
