package net.worklogtracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.worklogtracker.dto.AuthRequest;
import net.worklogtracker.dto.AuthResponse;
import net.worklogtracker.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Tag(name="auth")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입", security = @SecurityRequirement(name = ""))
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthRequest request) {
        try {
            authService.signup(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "로그인", security = @SecurityRequirement(name = ""))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)))
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            String token = authService.authenticateAndGenerateToken(request);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
