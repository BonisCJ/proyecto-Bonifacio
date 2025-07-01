package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bussines.model.Credential; 

public class UserService {

    private final List<Credential> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public void addUser(Credential cred) {
        if (cred != null && cred.getUserName() != null && !cred.getUserName().isEmpty()) {
            users.add(cred);
        }
    }

    public List<Credential> getAllUsers() {
        return Collections.unmodifiableList(users);
    }
}
