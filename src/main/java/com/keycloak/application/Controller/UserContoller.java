package com.keycloak.application.Controller;

import com.keycloak.application.Dto.UserDTO;
import com.keycloak.application.Service.UserService;
import com.keycloak.application.Service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserContoller {

    private final UserService userService;

    @PostMapping
    private UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping
    private UserRepresentation getUserById(Principal principal){
        return userService.getUserById(principal.getName());
    }

    @DeleteMapping("/{userId}")
    private void deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);
    }
}
