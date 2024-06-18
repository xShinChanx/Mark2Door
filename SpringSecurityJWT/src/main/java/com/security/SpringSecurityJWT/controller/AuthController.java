package com.security.SpringSecurityJWT.controller;

import com.security.SpringSecurityJWT.dto.ReqRes;
import com.security.SpringSecurityJWT.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signup(@RequestBody ReqRes signupRequest) {
        return ResponseEntity.ok(authService.signUp(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signin(@RequestBody ReqRes signinRequest) {
        return ResponseEntity.ok(authService.signIn(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/inner/validate")
    public ResponseEntity<Boolean> validate(@RequestBody String token){
        return ResponseEntity.ok(authService.validateToken(token));
    }

    @GetMapping("/gfg")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Welcome to GeeksforGeeks");
    }
}
