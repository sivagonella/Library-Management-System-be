package com.library.management.resources;

import com.library.management.domain.dto.LoginUserDTO;
import com.library.management.domain.entity.User;
import com.library.management.domain.dto.UserDTO;
import com.library.management.services.UserAuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class UserController {

    private UserAuthenticationService userAuthenticationService;

    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserAuthenticationService userAuthenticationService, ModelMapper modelMapper) {
        this.userAuthenticationService = userAuthenticationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/users/email={email}")
    public ResponseEntity<UserDTO> findUser(@PathVariable String email, @RequestBody LoginUserDTO loginUserDTO) {
        User user = userAuthenticationService.getUserByEmailID(email);
        UserDTO responseBody = null;
        if(user != null){
            responseBody = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDTO> addUser(UserDTO userDTO) {
        UserDTO responseBody = modelMapper.map(userAuthenticationService.addUser(modelMapper.map(userDTO, User.class)), UserDTO.class);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
