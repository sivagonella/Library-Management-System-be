package com.library.management.resources;

import com.library.management.domain.LoginUserDTO;
import com.library.management.domain.User;
import com.library.management.domain.UserDTO;
import com.library.management.services.UserAuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody UserDTO findUser(@PathVariable String email, @RequestBody LoginUserDTO loginUserDTO) {
        User user = userAuthenticationService.getUserByEmailID(email);
        if(user != null)
            return modelMapper.map(user, UserDTO.class);
        return null;
    }

    @PostMapping(path = "/users")
    public @ResponseBody UserDTO addUser(UserDTO userDTO) {
        return modelMapper.map(userAuthenticationService.addUser(modelMapper.map(userDTO, User.class)), UserDTO.class);
    }
}
