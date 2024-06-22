package com.security.SpringSecurityJWT.controller;

import com.security.SpringSecurityJWT.dto.ReqRes;
import com.security.SpringSecurityJWT.service.AuthService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private RabbitTemplate template;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable int id) {
        ReqRes response = authService.deleteUserById(id);

        // Convert id to long before sending
        long longId = (long) id;
        template.convertAndSend("shop-exchange", "shop-routingKey", longId);

        if (response.getStatusCode() == 200) {
            return ResponseEntity.ok(response);
        } else if (response.getStatusCode() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
