package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.RoleEntity;
import org.hibernate.Session;

import java.util.List;

public class RoleDefaultRepository implements RoleRepository {
    private MySqlUsersFactorySession mySqlUsersFactorySession;

    public RoleDefaultRepository(MySqlUsersFactorySession mySqlUsersFactorySession){
        this.mySqlUsersFactorySession = mySqlUsersFactorySession;
    }

    @Override
    public RoleEntity saveRole(RoleEntity roleEntity) {
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(roleEntity);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return roleEntity;
    }

    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(roleEntity);
        session.getTransaction().commit();
        session.close();
        return roleEntity;
    }

    @Override
    public RoleEntity getById(int userRoleId) {
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        RoleEntity roleEntity = session.get(RoleEntity.class, userRoleId);
        session.close();
        return roleEntity;
    }

    @Override
    public List<RoleEntity> getAll() {
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        List<RoleEntity> roleEntityList = session.createCriteria(RoleEntity.class).list();
        session.close();
        return roleEntityList;
    }

    @Override
    public void removeRole(RoleEntity roleEntity) {
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(roleEntity);
        session.getTransaction().commit();
        session.close();
    }
}
