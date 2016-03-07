package com.grinder.data.repository.users;

import com.grinder.entities.users.UserAccountRole;

import java.util.List;

public interface UserAccountRoleRepository {
    boolean createUserAccountRole(UserAccountRole userAccountRole);
    boolean updateUserAccountRole(UserAccountRole userAccountRole);
    boolean removeUserAccountRoleByUserAccountRoleId(int userAccountRoleId);

    List<UserAccountRole> getUserAccountRoles();
    List<UserAccountRole> getUserAccountRolesByAccountId(int accountId);
    List<UserAccountRole> getUserAccountRolesByRoleId(int roleId);

    boolean removeAllUserAccountRoles();
}
