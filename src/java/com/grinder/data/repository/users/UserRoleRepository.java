package com.grinder.data.repository.users;

import com.grinder.entities.users.UserRole;

import java.util.List;

public interface UserRoleRepository {
    boolean createRole(UserRole userRole);
    boolean updateRole(UserRole userRole);

    List<UserRole> getRoles();
    UserRole getRoleByRoleId(int roleId);

    //WARNING: TESTING ONLY
    boolean removeAllRoles();
}
