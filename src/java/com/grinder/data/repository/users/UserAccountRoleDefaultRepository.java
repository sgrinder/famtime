package com.grinder.data.repository.users;

import com.grinder.data.connections.AccessConnection;
import com.grinder.data.connections.UserAccessConnection;
import com.grinder.data.queries.users.UserAccountRoleQueries;
import com.grinder.entities.users.UserAccountRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserAccountRoleDefaultRepository implements UserAccountRoleRepository {
    private AccessConnection accessConnection = new UserAccessConnection();
    private Connection connection;
    private boolean testMode;

    public UserAccountRoleDefaultRepository(boolean testMode){
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
    public boolean createUserAccountRole(UserAccountRole userAccountRole) {
        String sql = UserAccountRoleQueries.INSERT_ACCOUNT_ROLE;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userAccountRole.getAccountId());
            preparedStatement.setInt(2, userAccountRole.getRoleId());
            preparedStatement.setInt(3, userAccountRole.getStatusId());

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
    public boolean updateUserAccountRole(UserAccountRole userAccountRole) {
        String sql = UserAccountRoleQueries.UPDATE_ACCOUNT_ROLE;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userAccountRole.getAccountId());
            preparedStatement.setInt(2, userAccountRole.getRoleId());
            preparedStatement.setInt(3, userAccountRole.getStatusId());
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStatement.setInt(5, userAccountRole.getAccountRoleId());

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
    public boolean removeUserAccountRoleByUserAccountRoleId(int userAccountRoleId) {
        String sql = UserAccountRoleQueries.REMOVE_ACCOUNT_ROLE_BY_ID;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userAccountRoleId);

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
    public List<UserAccountRole> getUserAccountRoles() {
        String sql = UserAccountRoleQueries.SELECT_ALL;

        List<UserAccountRole> userAccountRoles = new ArrayList<>();

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                UserAccountRole userAccountRole = new UserAccountRole();
                userAccountRole.setAccountRoleId(resultSet.getInt("account_role_id"));
                userAccountRole.setAccountId(resultSet.getInt("account_id"));
                userAccountRole.setRoleId(resultSet.getInt("role_id"));
                userAccountRole.setStatusId(resultSet.getInt("status_id"));
                userAccountRole.setCreatedDate(resultSet.getString("created_date"));
                userAccountRole.setModifiedDate(resultSet.getString("modified_date"));

                userAccountRoles.add(userAccountRole);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return userAccountRoles;
    }

    @Override
    public List<UserAccountRole> getUserAccountRolesByAccountId(int accountId) {
        String sql = UserAccountRoleQueries.SELECT_BY_ACCOUNT_ID;

        List<UserAccountRole> userAccountRoles = new ArrayList<>();

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                UserAccountRole userAccountRole = new UserAccountRole();
                userAccountRole.setAccountRoleId(resultSet.getInt("account_role_id"));
                userAccountRole.setAccountId(resultSet.getInt("account_id"));
                userAccountRole.setRoleId(resultSet.getInt("role_id"));
                userAccountRole.setRoleId(resultSet.getInt("status_id"));
                userAccountRole.setCreatedDate(resultSet.getString("created_date"));
                userAccountRole.setModifiedDate(resultSet.getString("modified_date"));

                userAccountRoles.add(userAccountRole);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return userAccountRoles;
    }

    @Override
    public List<UserAccountRole> getUserAccountRolesByRoleId(int roleId) {
        String sql = UserAccountRoleQueries.SELECT_BY_ROLE_ID;

        List<UserAccountRole> userAccountRoles = new ArrayList<>();

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roleId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                UserAccountRole userAccountRole = new UserAccountRole();
                userAccountRole.setAccountRoleId(resultSet.getInt("account_role_id"));
                userAccountRole.setAccountId(resultSet.getInt("account_id"));
                userAccountRole.setRoleId(resultSet.getInt("role_id"));
                userAccountRole.setStatusId(resultSet.getInt("status_id"));
                userAccountRole.setCreatedDate(resultSet.getString("created_date"));
                userAccountRole.setModifiedDate(resultSet.getString("modified_date"));

                userAccountRoles.add(userAccountRole);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return userAccountRoles;
    }

    @Override
    public boolean removeAllUserAccountRoles() {
        String sql = UserAccountRoleQueries.REMOVE_ALL_ACCOUNT_ROLES;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
