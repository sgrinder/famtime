package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.AccountRoleEntity;

import java.util.List;

public interface AccountRoleRepository {
    AccountRoleEntity saveAccountRole(AccountRoleEntity accountRoleEntity);
    AccountRoleEntity updateAccountRole(AccountRoleEntity accountRoleEntity);
    AccountRoleEntity getById(int accountRoleId);
    List<AccountRoleEntity> getByAccountId(int accountId);
    List<AccountRoleEntity> getByRoleId(int roleId);
    List<AccountRoleEntity> getAll();
    void removeAccountRole(AccountRoleEntity accountRoleEntity);
}
