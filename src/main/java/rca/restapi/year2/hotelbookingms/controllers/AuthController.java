package rca.restapi.year2.hotelbookingms.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rca.restapi.year2.hotelbookingms.dtos.JwtResponse;
import rca.restapi.year2.hotelbookingms.dtos.LoginDto;
import rca.restapi.year2.hotelbookingms.dtos.RegisterDto;
import rca.restapi.year2.hotelbookingms.services.UserService;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @RequestMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) {
        String token = userService.login(loginDto, httpServletResponse);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        userService.register(registerDto);
        return ResponseEntity.ok("User registered successfully");
    }
}
