package com.grinder.data.repository.users;

import com.grinder.entities.users.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserRoleDefaultRepositoryTest {
    private UserRoleRepository userRoleRepository;

    @Before
    public void setUp(){
        boolean testMode = true;
        userRoleRepository = new UserRoleDefaultRepository(testMode);
    }

    @After
    public void tearDown(){
        userRoleRepository.removeAllRoles();
    }

    private UserRole getTestUserRole(){
        UserRole userRole = new UserRole();
        userRole.setDescription("Test");
        userRole.setStatusId(1);

        return userRole;
    }

    @Test
    public void testCreateRole() throws Exception {
        UserRole testUserRole = getTestUserRole();

        boolean successful = userRoleRepository.createRole(testUserRole);

        assertTrue(successful);
    }

    @Test
    public void testUpdateRole() throws Exception {
        UserRole testUserRole = getTestUserRole();

        userRoleRepository.createRole(testUserRole);

        UserRole userRole = userRoleRepository.getRoles().get(0);
        userRole.setDescription("Tester");

        userRoleRepository.updateRole(userRole);

        UserRole updatedUserRole = userRoleRepository.getRoles().get(0);

        boolean successful = updatedUserRole.getDescription().equals("Tester");

        assertTrue(successful);
    }

    @Test
    public void testGetRoles() throws Exception {
        UserRole testUserRole = getTestUserRole();

        userRoleRepository.createRole(testUserRole);

        List<UserRole> userRoles = userRoleRepository.getRoles();

        assertTrue(userRoles.size() == 1);
    }

    @Test
    public void testGetRoleByRoleId() throws Exception {
        UserRole testUserRole = getTestUserRole();

        userRoleRepository.createRole(testUserRole);

        UserRole userRole = userRoleRepository.getRoles().get(0);

        UserRole userRoleByRoleId = userRoleRepository.getRoleByRoleId(userRole.getRoleId());

        assertEquals(userRole.getRoleId(), userRoleByRoleId.getRoleId());
    }
}