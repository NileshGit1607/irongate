package com.irongate.services.application;

import com.irongate.entities.user.User;

public interface AuthenticationService {

    public User authenticate(String userName, String password);
}
