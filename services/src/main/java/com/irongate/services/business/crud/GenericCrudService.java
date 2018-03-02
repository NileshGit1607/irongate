package com.irongate.services.business.crud;

import com.irongate.dao.exception.EntityNotExistException;
import com.irongate.entities.BusinessEntity;

import java.util.List;

public interface GenericCrudService {

    public void save(BusinessEntity entity);

    public BusinessEntity read(Class clazz, String id) throws EntityNotExistException;

    public void update(BusinessEntity entity) throws EntityNotExistException;

    public void delete(Class entity, String id);

    public List<BusinessEntity> findAll(Class entity);
}
