package com.security.SpringSecurityJWT.service;

import com.security.SpringSecurityJWT.dto.ReqRes;
import com.security.SpringSecurityJWT.entity.OurUsers;
import com.security.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private OurUserRepo ourUserRepo;

    public AuthService(OurUserRepo ourUserRepo) {
        this.ourUserRepo = ourUserRepo;
    }

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationRequest) {
        ReqRes response = new ReqRes();
        try {
            OurUsers ourUsers = new OurUsers();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            OurUsers ourUserResult = ourUserRepo.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getId() > 0) {
                response.setOurUsers(ourUserResult); // Set the saved user in response
                response.setMessage("User saved successfully");
                response.setStatusCode(200);
            } else {
                response.setMessage("Failed to save user");
                response.setStatusCode(500);
            }
        } catch (Exception exception) {
            response.setStatusCode(500);
            response.setError(exception.getMessage());
        }
        return response;
    }

    public ReqRes signIn(ReqRes signinRequest) {
        ReqRes response = new ReqRes();
        try {
            // Check if user exists
            Optional<OurUsers> optionalUser = ourUserRepo.findByEmail(signinRequest.getEmail());
            if (!optionalUser.isPresent()) {
                response.setStatusCode(404);
                response.setMessage("User does not exist");
                return response;
            }

            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword())
            );

            // User exists, proceed with token generation
            OurUsers user = optionalUser.get();
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateToken(user);
            Integer userId = user.getId();
            String role = user.getRole();

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Authorization successful");
            response.setUserId(userId);
            response.setRole(role);

        } catch (BadCredentialsException badCredentialsException) {
            response.setStatusCode(401);
            response.setMessage("Invalid email or password");
        } catch (Exception exception) {
            response.setStatusCode(500);
            response.setError(exception.getMessage());
        }
        return response;
    }



    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        OurUsers users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(),users)){
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Token refreshed successfully");
        }else{
            response.setStatusCode(500);
        }
        return response;
    }

    public Boolean validateToken(String token) {
        return jwtUtils.validateToken(token);
    }

    public ReqRes deleteUserById(Integer userId) {
        ReqRes response = new ReqRes();
        try {
            Optional<OurUsers> ourUserOptional = ourUserRepo.findById(userId);
            if (ourUserOptional.isPresent()) {
                ourUserRepo.deleteById(userId);
                response.setMessage("User deleted successfully");
                response.setStatusCode(200);
            } else {
                response.setMessage("User not found");
                response.setStatusCode(404);
            }
        } catch (Exception exception) {
            response.setStatusCode(500);
            response.setError(exception.getMessage());
        }
        return response;
    }
}
