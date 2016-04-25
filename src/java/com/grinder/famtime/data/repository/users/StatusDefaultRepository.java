package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.StatusEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StatusDefaultRepository implements StatusRepository {
    private SessionFactory sessionFactory;

    public StatusDefaultRepository(MySqlUsersFactorySession mySqlUsersFactorySession){
        this.sessionFactory = mySqlUsersFactorySession.getSessionFactory();
    }

    @Override
    public StatusEntity saveStatus(StatusEntity statusEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(statusEntity);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return statusEntity;
    }

    @Override
    public StatusEntity updateStatus(StatusEntity statusEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(statusEntity);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return statusEntity;
    }

    @Override
    public StatusEntity getById(int statusId) {
        Session session = sessionFactory.openSession();
        StatusEntity statusEntity = session.get(StatusEntity.class, statusId);
        session.close();
        return statusEntity;
    }

    @Override
    public List<StatusEntity> getAll() {
        Session session = sessionFactory.openSession();
        List<StatusEntity> statusEntityList = session.createCriteria(StatusEntity.class).list();
        session.close();
        return statusEntityList;
    }

    @Override
    public void removeStatus(StatusEntity statusEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(statusEntity);
        session.getTransaction().commit();
        session.close();
    }
}
