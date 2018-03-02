package com.irongate.dao.factory;

import com.irongate.dao.exception.EntityNotExistException;
import com.irongate.entities.BusinessEntity;
import com.irongate.entities.sequence.SequenceId;
import org.apache.catalina.core.ApplicationContext;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> T findOne(Query query, Class<? extends BusinessEntity> entity) {
        return (T) mongoTemplate.findOne(query, entity);
    }

    public <T> T findById(String id, Class<? extends BusinessEntity> entity)
            throws EntityNotExistException {
        T savedEntity = (T) mongoTemplate.findById(id, entity);
        if (savedEntity == null)
            throw new EntityNotExistException("Entity with id " + id + " does not exist");
        return savedEntity;
    }

    public <T> List<T> findAll(Class<? extends BusinessEntity> entity) {
        return (List<T>) mongoTemplate.findAll(entity);
    }

    public <T> List<T> findAllByType(Class<? extends BusinessEntity> entity) {
        Query typeQuery = new Query();
        typeQuery.addCriteria(Criteria.where("_type").is(entity.getSimpleName()));
        return (List<T>) mongoTemplate.find(typeQuery, entity);
    }

    public void update(BusinessEntity entity) throws EntityNotExistException {
        BusinessEntity existingEntity = findById(entity.getId(), entity.getClass());
        entity.setId(existingEntity.getId());
        entity.setCreated(existingEntity.getCreated());
        entity.set_type(existingEntity.get_type());
        mongoTemplate.save(entity);
    }

    public void save(BusinessEntity entity) {
        String _type = entity.getClass().getSimpleName();
        entity.set_type(_type);
        entity.setCreated(Instant.now().toEpochMilli());
        mongoTemplate.insert(entity);

    }

    public <T> List<T> executeQuery(Query query, Class<? extends BusinessEntity> entity) {
        return (List<T>) mongoTemplate.find(query, entity);
    }

    public <T> void removeById(String id, Class<? extends BusinessEntity> entity) {
        Query deleteQuery = new Query();
        deleteQuery.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(deleteQuery, entity);
    }
}
