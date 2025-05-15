package rca.restapi.year2.hotelbookingms.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rca.restapi.year2.hotelbookingms.dtos.LoginDto;
import rca.restapi.year2.hotelbookingms.dtos.RegisterDto;
import rca.restapi.year2.hotelbookingms.models.User;
import rca.restapi.year2.hotelbookingms.repositories.UserRepository;

@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final PasswordEncoder passwordEncoder;

    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService, JwtConfig jwtConfig, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginDto loginDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail() != null ? loginDto.getName() : loginDto.getEmail(),
                        loginDto.getPassword()
                );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByName(loginDto.getName());
        if (user == null) {
            throw new RuntimeException("User not found.");
        }
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Store refresh token in the HttpOnly cookie
        var cookie = ResponseCookie.from("refreshToken", refreshToken.toString())
                .httpOnly(true)
                .secure(true)
                .path("/auth/refresh")
                .maxAge(jwtConfig.getRefreshTokenExpiration())
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return accessToken.toString();
    }


    public void register(RegisterDto registerDTO) {
        if (userRepository.existsByEmail((registerDTO.getEmail()))) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        if(registerDTO.getRole() != null){
            user.setRole(registerDTO.getRole());
        }
        userRepository.save(user);
    }

}
