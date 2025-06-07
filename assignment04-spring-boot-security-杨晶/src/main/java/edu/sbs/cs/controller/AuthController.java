package edu.sbs.cs.controller;

import edu.sbs.cs.model.User;
import edu.sbs.cs.security.CustomUserDetails;
import edu.sbs.cs.security.JwtUtil;
import edu.sbs.cs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> registrationRequest) {
        String username = registrationRequest.get("username");
        String password = registrationRequest.get("password");
        String role = registrationRequest.getOrDefault("role", "ROLE_USER");

        User user = userService.registerUser(username, password, role);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Map<String, String> authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.get("username"), authenticationRequest.get("password"))
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final User user = userService.findByUsername(authenticationRequest.get("username"));
        final UserDetails userDetails = new CustomUserDetails(user);
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", jwt);
        tokens.put("refreshToken", refreshToken);
        return ResponseEntity.ok(tokens);
    }

    @GetMapping("/oauth2/success")
    public ResponseEntity<?> oauth2LoginSuccess() {
        // This endpoint will be called after successful OAuth2 authentication
        // You can generate JWT token here for the authenticated user
        return ResponseEntity.ok("OAuth2 login successful");
    }
}
