package com.irongate.dao.repository;

import com.irongate.entities.user.User;
import org.springframework.stereotype.Service;

public interface UserDaoService {

    public User findByUsernameAndHash(String userName, String hash);
}
