package com.app.controller;

import com.app.entity.User;
import com.app.payload.JWTTokenDTO;
import com.app.payload.LoginDTO;
import com.app.payload.UserDTO;
import com.app.exception.UserAlreadyExistsException;
import com.app.repository.UserRepository;
import com.app.service.JWTService;
import com.app.service.OTPService;
import com.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final OTPService otpService;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    public AuthController(UserService userService, OTPService otpService,
                          UserRepository userRepository, JWTService jwtService) {
        this.userService = userService;
        this.otpService = otpService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/usersign")
    public ResponseEntity<?> userSignIn(
            @RequestBody LoginDTO loginDTO
    ){
        String jwtToken = userService.verifyLogin(loginDTO);
        if(jwtToken!=null){
            JWTTokenDTO tokenDTO = new JWTTokenDTO();
            tokenDTO.setToken(jwtToken);
            tokenDTO.setTokenType("JWT");
            return new ResponseEntity<>(tokenDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/content-manager-signup")
    public ResponseEntity<String> createContentManagerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createContentManagerUser(userDTO);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/blog-manager-signup")
    public ResponseEntity<String> createBlogManagerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createBlogManagerUser(userDTO);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login-otp")
    public String generateOtp(
            @RequestParam String mobile
    ){
        Optional<User> opUser = userRepository.findByMobile(mobile);
        if(opUser.isPresent()){
            String otp=otpService.generateOTP(mobile);
            return otp +"   "+mobile;
        };
        return "User not found";
    }

    @PostMapping("/validate-otp")
    public String validateOtp(
            @RequestParam String mobile,
            @RequestParam String otp
    ){
        boolean status = otpService.validateOTP(mobile, otp);
        if(status){
            //genetate JWT token
            Optional<User> opUser = userRepository.findByMobile(mobile);
            if(opUser.isPresent()){
                String jwtToken = jwtService.generateToken(opUser.get().getUsername());
                return "JWT token generated: "+jwtToken;
            }
        }
        return status? "OTP validated successfully":"Invalid otp";
    }


}
