package com.irongate.dao.repository;

import com.irongate.dao.exception.EntityNotExistException;
import com.irongate.dao.factory.MongoService;
import com.irongate.entities.BusinessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericDaoServiceImpl implements GenericDaoService {

    @Autowired
    private MongoService mongoService;

    @Override
    public void save(BusinessEntity entity) {
        mongoService.save(entity);
    }

    @Override
    public BusinessEntity read(Class entity, String id) throws EntityNotExistException {
        return (BusinessEntity) mongoService.findById(id, entity);
    }

    @Override
    public void update(BusinessEntity entity) throws EntityNotExistException {
        mongoService.update(entity);
    }

    @Override
    public void delete(Class entity, String id) {
        mongoService.removeById(id, entity);
    }

    @Override
    public List<BusinessEntity> findAllByType(Class entity) {
        return mongoService.findAllByType(entity);
    }

}
