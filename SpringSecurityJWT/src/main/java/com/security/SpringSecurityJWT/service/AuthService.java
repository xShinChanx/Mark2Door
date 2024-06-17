package com.security.SpringSecurityJWT.service;

import com.security.SpringSecurityJWT.dto.ReqRes;
import com.security.SpringSecurityJWT.entity.OurUsers;
import com.security.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationRequest) {
        ReqRes response = new ReqRes();
        try{
            OurUsers ourUsers = new OurUsers();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            OurUsers ourUserResult = ourUserRepo.save(ourUsers);
            if(ourUserResult != null && ourUserResult.getId() > 0){
                response.setOurUsers(ourUserResult);
                response.setMessage("user saved successfully");
                response.setStatusCode(200);
            }
        }catch(Exception expection ){
            response.setStatusCode(500);
            response.setError(expection.getMessage());
        }
        return response;
    }

    public ReqRes signIn (ReqRes signinRequest){
        ReqRes response = new ReqRes();
        try{
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()))  ;
          var user = ourUserRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
          System.out.println("USER IS: "+user);
          var jwt = jwtUtils.generateToken(user);
          var refreshToken = jwtUtils.generateToken(user);
          response.setStatusCode(200);
          response.setToken(jwt);
          response.setRefreshToken(refreshToken);
          response.setExpirationTime("24Hr");
          response.setMessage("Authorization successful");
        }catch(Exception expection ){
            response.setStatusCode(500);
            response.setError(expection.getMessage());
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
}
