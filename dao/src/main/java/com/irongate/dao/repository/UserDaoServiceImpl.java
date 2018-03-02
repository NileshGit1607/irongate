package com.irongate.dao.repository;

import com.irongate.dao.factory.MongoService;
import com.irongate.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserDaoServiceImpl implements UserDaoService {

    @Autowired
    private MongoService mongoService;

    @Override
    public User findByUsernameAndHash(String userName, String hash) {
        Query query = new Query(Criteria.where("username").is(userName).and("hash").is(hash));
        return mongoService.findOne(query, User.class);
    }
}
