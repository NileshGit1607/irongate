package com.irongate.services.application;

import com.irongate.dao.repository.UserDaoService;
import com.irongate.entities.user.User;
import com.irongate.utils.AuthConstant;
import com.irongate.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDaoService userRepository;

    @Override
    public User authenticate(String userName, String password) {
        String inputHash = AuthUtils.generateHash(AuthConstant.SALT + password);
        User user = userRepository.findByUsernameAndHash(userName, inputHash);
        return user;
    }
}
