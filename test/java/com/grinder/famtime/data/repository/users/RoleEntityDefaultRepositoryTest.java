package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.RoleEntity;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class RoleEntityDefaultRepositoryTest extends TestSessionFactory {
    private static RoleRepository roleRepository;
    private RoleEntity roleEntity;

    @BeforeClass
    public static void setUpClass(){
        roleRepository = new RoleDefaultRepository(mySqlUsersFactorySession);
    }

    @After
    public void tearDown(){
        removeTestRole(roleEntity);
    }

    @Ignore
    private void removeTestRole(RoleEntity roleEntity) {
        roleRepository.removeRole(roleEntity);
    }

    @Ignore
    private RoleEntity getTestUserRole(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setDescription("Test");
        roleEntity.setStatusId(1);
        roleEntity.setCreatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        roleEntity.setModifiedDate(new java.sql.Timestamp(new java.util.Date().getTime()));

        return roleEntity;
    }

    @Test
    public void testCreateRole() throws Exception {
        roleEntity = getTestUserRole();
        roleRepository.saveRole(roleEntity);

        assertTrue(roleEntity.getId() != 0);
    }

    @Test
    public void testUpdateRole() throws Exception {
        roleEntity = getTestUserRole();
        roleRepository.saveRole(roleEntity);
        roleEntity.setDescription("Test Role");
        roleRepository.updateRole(roleEntity);

        RoleEntity updatedRoleEntity = roleRepository.getById(roleEntity.getId());

        assertTrue(updatedRoleEntity.getDescription().equals("Test Role"));
    }

    @Test
    public void testGetRoles() throws Exception {
        roleEntity = getTestUserRole();
        roleRepository.saveRole(roleEntity);

        List<RoleEntity> roleEntities = roleRepository.getAll();

        assertTrue(roleEntities.size() > 0);
    }

    @Test
    public void testGetRoleByRoleId() throws Exception {
        roleEntity = getTestUserRole();
        roleRepository.saveRole(roleEntity);

        RoleEntity roleByRoleEntityId = roleRepository.getById(roleEntity.getId());

        assertEquals(roleEntity.getId(), roleByRoleEntityId.getId());
    }
}