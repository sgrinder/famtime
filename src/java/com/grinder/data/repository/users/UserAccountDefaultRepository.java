package com.grinder.data.repository.users;

import com.grinder.data.connections.AccessConnection;
import com.grinder.data.connections.UserAccessConnection;
import com.grinder.data.queries.users.UserAccountQueries;
import com.grinder.entities.users.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDefaultRepository implements UserAccountRepository {
    private AccessConnection accessConnection = new UserAccessConnection();
    private Connection connection;
    private boolean testMode;

    public UserAccountDefaultRepository(boolean testMode){
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
    public boolean createUserAccount(UserAccount userAccount) {
        String sql = UserAccountQueries.INSERT_ACCOUNT;

        try{
            checkConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userAccount.getAccountName());
            preparedStatement.setString(2, userAccount.getPassword());
            preparedStatement.setInt(3, userAccount.getStatusId());

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
    public boolean updateUserAccount(UserAccount userAccount) {
        String sql = UserAccountQueries.UPDATE_ACCOUNT;

        try{
            checkConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userAccount.getAccountName());
            preparedStatement.setString(2, userAccount.getPassword());
            preparedStatement.setInt(3, userAccount.getStatusId());
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStatement.setInt(5, userAccount.getAccountId());

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
    public List<UserAccount> getUserAccounts() {
        String sql = UserAccountQueries.SELECT_ALL;

        List<UserAccount> userAccounts = new ArrayList<>();

        try{
            checkConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                UserAccount userAccount = new UserAccount();
                userAccount.setAccountId(resultSet.getInt("account_id"));
                userAccount.setAccountName(resultSet.getString("account_name"));
                userAccount.setPassword(resultSet.getString("password"));
                userAccount.setStatusId(resultSet.getInt("status_id"));
                userAccount.setCreatedDate(resultSet.getString("created_date"));
                userAccount.setModifiedDate(resultSet.getString("modified_date"));

                userAccounts.add(userAccount);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return userAccounts;
    }

    @Override
    public UserAccount getUserAccountByUserAccountId(int userAccountId) {
        String sql = UserAccountQueries.SELECT_BY_ACCOUNT_ID;

        UserAccount userAccount = null;

        try{
            checkConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userAccountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                userAccount = new UserAccount();
                userAccount.setAccountId(resultSet.getInt("account_id"));
                userAccount.setAccountName(resultSet.getString("account_name"));
                userAccount.setPassword(resultSet.getString("password"));
                userAccount.setStatusId(resultSet.getInt("status_id"));
                userAccount.setCreatedDate(resultSet.getString("created_date"));
                userAccount.setModifiedDate(resultSet.getString("modified_date"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return userAccount;
    }

    @Override
    public UserAccount getUserAccountByUserName(String userAccountName) {
        String sql = UserAccountQueries.SELECT_BY_ACCOUNT_NAME;

        UserAccount userAccount = null;

        try{
            checkConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userAccountName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                userAccount = new UserAccount();
                userAccount.setAccountId(resultSet.getInt("account_id"));
                userAccount.setAccountName(resultSet.getString("account_name"));
                userAccount.setPassword(resultSet.getString("password"));
                userAccount.setStatusId(resultSet.getInt("status_id"));
                userAccount.setCreatedDate(resultSet.getString("created_date"));
                userAccount.setModifiedDate(resultSet.getString("modified_date"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return userAccount;
    }

    @Override
    public UserAccount getUserAccountByUserNameAndPassword(String userAccountName, String password) {
        String sql = UserAccountQueries.SELECT_BY_ACCOUNT_NAME_AND_PASSWORD;

        UserAccount userAccount = null;

        try{
            checkConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userAccountName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                userAccount = new UserAccount();
                userAccount.setAccountId(resultSet.getInt("account_id"));
                userAccount.setAccountName(resultSet.getString("account_name"));
                userAccount.setPassword(resultSet.getString("password"));
                userAccount.setStatusId(resultSet.getInt("status_id"));
                userAccount.setCreatedDate(resultSet.getString("created_date"));
                userAccount.setModifiedDate(resultSet.getString("modified_date"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return userAccount;
    }

    @Override
    public boolean removeAllUserAccounts() {
        String sql = UserAccountQueries.DELETE_ALL_ACCOUNTS;

        try{
            checkConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

            return true;
        } catch(Exception ex){
            return false;
        }
    }
}
