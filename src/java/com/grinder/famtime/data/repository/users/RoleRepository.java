package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.RoleEntity;

import java.util.List;

public interface RoleRepository {
    RoleEntity saveRole(RoleEntity roleEntity);
    RoleEntity updateRole(RoleEntity roleEntity);
    RoleEntity getById(int userAccountId);
    List<RoleEntity> getAll();
    void removeRole(RoleEntity roleEntity);
}
