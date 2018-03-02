package com.irongate.services.business.crud;

import com.irongate.dao.exception.EntityNotExistException;
import com.irongate.dao.repository.GenericDaoService;
import com.irongate.entities.BusinessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericCrudServiceImpl implements GenericCrudService {

    @Autowired
    private GenericDaoService genericRepository;

    @Override
    public void save(BusinessEntity entity) {
        genericRepository.save(entity);
    }

    @Override
    public BusinessEntity read(Class clazz, String id) throws EntityNotExistException {
        return genericRepository.read(clazz, id);
    }

    @Override
    public void update(BusinessEntity entity) throws EntityNotExistException {
        genericRepository.update(entity);
    }

    @Override
    public void delete(Class entity, String id) {
        genericRepository.delete(entity, id);
    }

    @Override
    public List<BusinessEntity> findAll(Class entity) {
        return genericRepository.findAllByType(entity);
    }
}
