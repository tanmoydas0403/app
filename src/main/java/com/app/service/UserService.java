package com.app.service;


import com.app.payload.LoginDTO;
import com.app.payload.UserDTO;
import com.app.entity.User;
import com.app.exception.UserAlreadyExistsException;
import com.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    //private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JWTService jwtService/*, PasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        //this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserDTO userDTO) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.findByEmailId(userDTO.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException("Email Id already exists");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmailId(userDTO.getEmailId());
        user.setMobile(userDTO.getMobile());
//        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());// Assuming password is already encrypted
//        user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }

    public User createContentManagerUser(UserDTO userDTO) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.findByEmailId(userDTO.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException("Email Id already exists");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmailId(userDTO.getEmailId());
//        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());// Assuming password is already encrypted
//        user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENTMANAGER");
        return userRepository.save(user);
    }

    public User createBlogManagerUser(UserDTO userDTO) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.findByEmailId(userDTO.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException("Email Id already exists");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmailId(userDTO.getEmailId());
//        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());// Assuming password is already encrypted
//        user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOGMANAGER");
        return userRepository.save(user);
    }

    public String verifyLogin(
            LoginDTO dto
    ){
        Optional<User> opUser=userRepository.findByUsername(dto.getUsername());
        if(opUser.isPresent()){
            User user = opUser.get();
            if(BCrypt.checkpw(dto.getPassword(), user.getPassword())){
                return jwtService.generateToken(user.getUsername());
            };
        }
        return null;
    }
}

