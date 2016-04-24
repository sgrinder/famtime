//package com.grinder.famtime.data.repository.users;
//
//import com.grinder.famtime.entities.users.AccountRoleEntity;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AccountRoleDefaultRepository implements AccountRoleRepository {
//    private MySqlConnections mySqlConnections;
//    private Connection connection;
//
//    public AccountRoleDefaultRepository(MySqlConnections mySqlConnection){
//        this.mySqlConnections = mySqlConnection;
//    }
//
//    private void checkConnection(){
//        try{
//            if(this.connection != null || this.connection.isClosed()){
//                this.connection = mySqlConnections.getConnection();
//            }
//        } catch(Exception ex){
//            System.out.println("Failed to connect to User schema :: \n\n" + ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public boolean createUserAccountRole(AccountRoleEntity userAccountRole) {
//        String sql = UserAccountRoleQueries.INSERT_ACCOUNT_ROLE;
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, userAccountRole.getAccountId());
//            preparedStatement.setInt(2, userAccountRole.getRoleId());
//            preparedStatement.setInt(3, userAccountRole.getStatusId());
//
//            preparedStatement.execute();
//
//            preparedStatement.close();
//            connection.close();
//
//            return true;
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public boolean updateUserAccountRole(AccountRoleEntity userAccountRole) {
//        String sql = UserAccountRoleQueries.UPDATE_ACCOUNT_ROLE;
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, userAccountRole.getAccountId());
//            preparedStatement.setInt(2, userAccountRole.getRoleId());
//            preparedStatement.setInt(3, userAccountRole.getStatusId());
//            preparedStatement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
//            preparedStatement.setInt(5, userAccountRole.getId());
//
//            preparedStatement.execute();
//
//            preparedStatement.close();
//            connection.close();
//
//            return true;
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public boolean removeUserAccountRoleByUserAccountRoleId(int userAccountRoleId) {
//        String sql = UserAccountRoleQueries.REMOVE_ACCOUNT_ROLE_BY_ID;
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, userAccountRoleId);
//
//            preparedStatement.execute();
//
//            preparedStatement.close();
//            connection.close();
//
//            return true;
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public List<AccountRoleEntity> getUserAccountRoles() {
//        String sql = UserAccountRoleQueries.SELECT_ALL;
//
//        List<AccountRoleEntity> userAccountRoles = new ArrayList<>();
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()){
//                AccountRoleEntity userAccountRole = new AccountRoleEntity();
//                userAccountRole.setId(resultSet.getInt("account_role_id"));
//                userAccountRole.setAccountId(resultSet.getInt("account_id"));
//                userAccountRole.setRoleId(resultSet.getInt("role_id"));
//                userAccountRole.setStatusId(resultSet.getInt("status_id"));
//                userAccountRole.setCreatedDate(resultSet.getString("created_date"));
//                userAccountRole.setModifiedDate(resultSet.getString("modified_date"));
//
//                userAccountRoles.add(userAccountRole);
//            }
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//
//        return userAccountRoles;
//    }
//
//    @Override
//    public List<AccountRoleEntity> getUserAccountRolesByAccountId(int accountId) {
//        String sql = UserAccountRoleQueries.SELECT_BY_ACCOUNT_ID;
//
//        List<AccountRoleEntity> userAccountRoles = new ArrayList<>();
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, accountId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()){
//                AccountRoleEntity userAccountRole = new AccountRoleEntity();
//                userAccountRole.setId(resultSet.getInt("account_role_id"));
//                userAccountRole.setAccountId(resultSet.getInt("account_id"));
//                userAccountRole.setRoleId(resultSet.getInt("role_id"));
//                userAccountRole.setRoleId(resultSet.getInt("status_id"));
//                userAccountRole.setCreatedDate(resultSet.getString("created_date"));
//                userAccountRole.setModifiedDate(resultSet.getString("modified_date"));
//
//                userAccountRoles.add(userAccountRole);
//            }
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//
//        return userAccountRoles;
//    }
//
//    @Override
//    public List<AccountRoleEntity> getUserAccountRolesByRoleId(int roleId) {
//        String sql = UserAccountRoleQueries.SELECT_BY_ROLE_ID;
//
//        List<AccountRoleEntity> userAccountRoles = new ArrayList<>();
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, roleId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()){
//                AccountRoleEntity userAccountRole = new AccountRoleEntity();
//                userAccountRole.setId(resultSet.getInt("account_role_id"));
//                userAccountRole.setAccountId(resultSet.getInt("account_id"));
//                userAccountRole.setRoleId(resultSet.getInt("role_id"));
//                userAccountRole.setStatusId(resultSet.getInt("status_id"));
//                userAccountRole.setCreatedDate(resultSet.getString("created_date"));
//                userAccountRole.setModifiedDate(resultSet.getString("modified_date"));
//
//                userAccountRoles.add(userAccountRole);
//            }
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//
//        return userAccountRoles;
//    }
//
//    @Override
//    public boolean removeAllUserAccountRoles() {
//        String sql = UserAccountRoleQueries.REMOVE_ALL_ACCOUNT_ROLES;
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.execute();
//
//            preparedStatement.close();
//            connection.close();
//
//            return true;
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
//}
