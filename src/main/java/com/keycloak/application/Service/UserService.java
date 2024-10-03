package com.keycloak.application.Service;

import com.keycloak.application.Dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserRepresentation getUserById(String userId);
    void deleteUserById(String userId);
}
