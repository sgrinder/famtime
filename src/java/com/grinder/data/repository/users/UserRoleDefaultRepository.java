package com.grinder.data.repository.users;

import com.grinder.data.connections.AccessConnection;
import com.grinder.data.connections.UserAccessConnection;
import com.grinder.data.queries.users.UserRoleQueries;
import com.grinder.entities.users.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDefaultRepository implements UserRoleRepository {
    private AccessConnection accessConnection = new UserAccessConnection();
    private Connection connection;
    private boolean testMode;

    public UserRoleDefaultRepository(boolean testMode){
        this.testMode = testMode;
    }

    private void checkConnection(){
        try{
            if(connection == null || connection.isClosed()){
                if(testMode)
                    connection = accessConnection.getTestConnection();
                else
                    connection = accessConnection.getConnection();
            }
        } catch(Exception ex){
            System.out.println("Failed to connect to User database :: \n\n" + ex.getMessage());
        }
    }

    @Override
    public boolean createRole(UserRole userRole) {
        String sql = UserRoleQueries.INSERT_ROLE;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userRole.getDescription());
            preparedStatement.setInt(2, userRole.getStatusId());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

            return true;
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateRole(UserRole userRole) {
        String sql = UserRoleQueries.UPDATE_ROLE;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userRole.getDescription());
            preparedStatement.setInt(2, userRole.getStatusId());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStatement.setInt(4, userRole.getRoleId());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<UserRole> getRoles() {
        String sql = UserRoleQueries.SELECT_ALL;

        List<UserRole> userRoles = new ArrayList<>();

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                UserRole userRole = new UserRole();
                userRole.setRoleId(resultSet.getInt("role_id"));
                userRole.setDescription(resultSet.getString("description"));
                userRole.setStatusId(resultSet.getInt("status_id"));
                userRole.setCreatedDate(resultSet.getTimestamp("created_date"));
                userRole.setModifiedDate(resultSet.getTimestamp("modified_date"));

                userRoles.add(userRole);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return userRoles;
    }

    @Override
    public UserRole getRoleByRoleId(int roleId) {
        String sql = UserRoleQueries.SELECT_BY_ROLE_ID;

        UserRole userRole = null;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roleId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                userRole = new UserRole();
                userRole.setRoleId(resultSet.getInt("role_id"));
                userRole.setDescription(resultSet.getString("description"));
                userRole.setStatusId(resultSet.getInt("status_id"));
                userRole.setCreatedDate(resultSet.getTimestamp("created_date"));
                userRole.setModifiedDate(resultSet.getTimestamp("modified_date"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return userRole;
    }

    @Override
    public boolean removeAllRoles() {
        String sql = UserRoleQueries.DELETE_ALL_ROLES;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();

            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
