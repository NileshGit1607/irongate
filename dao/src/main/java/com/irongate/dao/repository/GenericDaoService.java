package com.irongate.dao.repository;

import com.irongate.dao.exception.EntityNotExistException;
import com.irongate.entities.BusinessEntity;

import java.util.List;

public interface GenericDaoService {

    public void save(BusinessEntity entity);

    public BusinessEntity read(Class clazz, String id) throws EntityNotExistException;

    public void update(BusinessEntity entity) throws EntityNotExistException;

    public void delete(Class entity, String id);

    public List<BusinessEntity> findAllByType(Class entity);
}
