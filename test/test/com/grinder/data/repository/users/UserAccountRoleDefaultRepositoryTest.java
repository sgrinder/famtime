package com.grinder.data.repository.users;

import com.grinder.data.connections.mysql.MySqlUsersDevelopmentSchemaConnection;
import com.grinder.entities.users.UserAccountRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class UserAccountRoleDefaultRepositoryTest {
    private UserAccountRoleRepository userAccountRoleRepository;

    @Before
    public void setUp(){
        userAccountRoleRepository = new UserAccountRoleDefaultRepository(new MySqlUsersDevelopmentSchemaConnection());
    }

    @After
    public void tearDown(){
        userAccountRoleRepository.removeAllUserAccountRoles();
    }

    private UserAccountRole getTestUserAccountRole(){
        UserAccountRole userAccountRole = new UserAccountRole();
        userAccountRole.setAccountId(1);
        userAccountRole.setRoleId(1);
        userAccountRole.setStatusId(1);
        return userAccountRole;
    }

    @Test
    public void testCreateUserAccountRole() throws Exception {
        UserAccountRole testUserAccountRole = getTestUserAccountRole();

        boolean successful = userAccountRoleRepository.createUserAccountRole(testUserAccountRole);

        assertTrue(successful);
    }

    @Test
    public void testUpdateUserAccountRole() throws Exception {
        UserAccountRole testUserAccountRole = getTestUserAccountRole();

        userAccountRoleRepository.createUserAccountRole(testUserAccountRole);

        UserAccountRole userAccountRole = userAccountRoleRepository.getUserAccountRoles().get(0);
        userAccountRole.setRoleId(2);
        userAccountRole.setStatusId(2);

        userAccountRoleRepository.updateUserAccountRole(userAccountRole);

        UserAccountRole updatedUserAccountRole = userAccountRoleRepository.getUserAccountRoles().get(0);

        assertTrue(updatedUserAccountRole.getAccountId() == 1);
        assertTrue(updatedUserAccountRole.getRoleId() == 2);
        assertTrue(updatedUserAccountRole.getStatusId() == 2);
    }

    @Test
    public void testRemoveUserAccountRoleByUserAccountRoleId() throws Exception {
        UserAccountRole testUserAccountRole = getTestUserAccountRole();

        userAccountRoleRepository.createUserAccountRole(testUserAccountRole);

        UserAccountRole userAccountRole = userAccountRoleRepository.getUserAccountRoles().get(0);

        userAccountRoleRepository.removeUserAccountRoleByUserAccountRoleId(userAccountRole.getAccountRoleId());

        List<UserAccountRole> userAccountRoles = userAccountRoleRepository.getUserAccountRoles();

        assertTrue(userAccountRoles.size() == 0);
    }

    @Test
    public void testGetUserAccountRoles() throws Exception {
        UserAccountRole testUserAccountRole = getTestUserAccountRole();

        userAccountRoleRepository.createUserAccountRole(testUserAccountRole);

        List<UserAccountRole> userAccountRoles = userAccountRoleRepository.getUserAccountRoles();

        assertTrue(userAccountRoles.size() == 1);
    }

    @Test
    public void testGetUserAccountRolesByAccountId() throws Exception {
        UserAccountRole testUserAccountRole = getTestUserAccountRole();

        userAccountRoleRepository.createUserAccountRole(testUserAccountRole);

        List<UserAccountRole> userAccountRolesByAccountId = userAccountRoleRepository.getUserAccountRolesByAccountId(testUserAccountRole.getAccountId());

        assertTrue(userAccountRolesByAccountId.size() == 1);

    }

    @Test
    public void testGetUserAccountRolesByRoleId() throws Exception {
        UserAccountRole testUserAccountRole = getTestUserAccountRole();

        userAccountRoleRepository.createUserAccountRole(testUserAccountRole);

        List<UserAccountRole> userAccountRolesByRoleId = userAccountRoleRepository.getUserAccountRolesByRoleId(testUserAccountRole.getRoleId());

        assertTrue(userAccountRolesByRoleId.size() == 1);
    }
}