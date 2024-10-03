package com.keycloak.application.Service;


import com.keycloak.application.Configuration.Credentials;
import com.keycloak.application.Dto.UserDTO;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImp implements UserService{

    @Value("${keycloak.realm}")
    public String realm;

    private Keycloak kaycloak;

    public UserServiceImp(Keycloak kaycloak) {
        this.kaycloak = kaycloak;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserRepresentation user=new UserRepresentation();
        user.setEnabled(true);
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmailVerified(true);

        CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());

        List<CredentialRepresentation> list =new ArrayList<>();
        list.add(credential);
        user.setCredentials(list);

        //RealmResource realm1= kaycloak.realm(realm);
        UsersResource usersResource = getUsersResource();

        Response response= usersResource.create(user);

        if (Objects.equals(201,response.getStatus())) {
            return userDTO;
        }
        return null;
    }

    private UsersResource getUsersResource() {
        RealmResource realm1= kaycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {
        return getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {
        getUsersResource().delete(userId);

    }
//    @Value("${keycloak.realm")
//    private String realm;
//
//    private Keycloak keycloak;
//
//    public UserServiceImp(Keycloak keycloak) {
//        this.keycloak = keycloak;
//    }
//
//
//
//    @Override
//    public UserDTO createUser(UserDTO userDTO) {
//
//        UserRepresentation userRepresentation = new UserRepresentation();
//        userRepresentation.setEnabled(true);
//        userRepresentation.setEmail(userDTO.getEmail());
//        userRepresentation.setFirstName(userDTO.getFirstName());
//        userRepresentation.setLastName(userDTO.getLastName());
//        userRepresentation.setUsername(userDTO.getUserName());
//        userRepresentation.setEmailVerified(true);
//
//        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
//        credentialRepresentation.setTemporary(false);
//        credentialRepresentation.setValue(userDTO.getPassword());
//        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//
//        List<CredentialRepresentation> credentialRepresentationsList = new ArrayList<>();
//        credentialRepresentationsList.add(credentialRepresentation);
//        userRepresentation.setCredentials(credentialRepresentationsList);
//
//        UsersResource usersResource = getUsersResource();
//        Response response = usersResource.create(userRepresentation);
//
//        if(Objects.equals(201, response.getStatus()))
//            return userDTO;
//        return null;
//    }
//
//    private UsersResource getUsersResource() {
//        RealmResource realmResource = keycloak.realm(realm);
//        return realmResource.users();
//    }
//
//    @Override
//    public UserRepresentation getUserById(String userId) {
//        return getUsersResource().get(userId).toRepresentation();
//    }
//
//    @Override
//    public void deleteUserById(String userId) {
//        getUsersResource().delete(userId);
//    }
//
//
////    public void addUser(UserDTO userDTO){
////        CredentialRepresentation credential = Credentials
////                .createPasswordCredentials(userDTO.getPassword());
////        UserRepresentation user = new UserRepresentation();
////        user.setUsername(userDTO.getUserName());
////        user.setFirstName(userDTO.getFirstname());
////        user.setLastName(userDTO.getLastName());
////        user.setEmail(userDTO.getEmailId());
////        user.setCredentials(Collections.singletonList(credential));
////        user.setEnabled(true);
////
////       // Keycloak keycloak = getInstance();
////
////        // Get realm
////        Keycloak keycloak = KeyCloakConfig.getInstance();
////
////        RealmResource realmResource = keycloak.realm(KeyCloakConfig.realm);
////        UsersResource usersResource = realmResource.users();
////        usersResource.create(user);
////    }
////
////    public List<UserRepresentation> getUser(String userName){
////        Keycloak keycloak = KeyCloakConfig.getInstance();
////
////        RealmResource realmResource = keycloak.realm(KeyCloakConfig.realm);
////        UsersResource usersResource = realmResource.users();
////        List<UserRepresentation> user = usersResource.search(userName, true);
////        return user;
////
////    }
//
////    public void updateUser(String userId, UserDTO userDTO){
////        CredentialRepresentation credential = Credentials
////                .createPasswordCredentials(userDTO.getPassword());
////        UserRepresentation user = new UserRepresentation();
////        user.setUsername(userDTO.getUserName());
////        user.setFirstName(userDTO.getFirstname());
////        user.setLastName(userDTO.getLastName());
////        user.setEmail(userDTO.getEmailId());
////        user.setCredentials(Collections.singletonList(credential));
////
////        UsersResource usersResource = getInstance();
////        usersResource.get(userId).update(user);
////    }
//

}
