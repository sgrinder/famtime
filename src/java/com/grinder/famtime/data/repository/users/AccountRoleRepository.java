package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.AccountRoleEntity;

import java.util.List;

public interface AccountRoleRepository {
    boolean createUserAccountRole(AccountRoleEntity accountRoleEntity);
    boolean updateUserAccountRole(AccountRoleEntity accountRoleEntity);
    boolean removeUserAccountRoleByUserAccountRoleId(int userAccountRoleId);

    List<AccountRoleEntity> getUserAccountRoles();
    List<AccountRoleEntity> getUserAccountRolesByAccountId(int accountId);
    List<AccountRoleEntity> getUserAccountRolesByRoleId(int roleId);

    boolean removeAllUserAccountRoles();
}
