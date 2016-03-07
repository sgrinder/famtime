package com.grinder.data.repository.users;

import com.grinder.data.connections.AccessConnection;
import com.grinder.data.connections.UserAccessConnection;
import com.grinder.data.queries.users.UserStatusQueries;
import com.grinder.entities.users.UserStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserStatusDefaultRepository implements UserStatusRepository {
    private AccessConnection accessConnection = new UserAccessConnection();
    private Connection connection;
    private boolean testMode;

    public UserStatusDefaultRepository(boolean testMode){
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
    public boolean createStatus(UserStatus userStatus) {
        String sql = UserStatusQueries.INSERT_STATUS;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userStatus.getDescription());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateStatus(UserStatus userStatus) {
        String sql = UserStatusQueries.UPDATE_STATUS;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userStatus.getDescription());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStatement.setInt(3, userStatus.getStatusId());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<UserStatus> getStatuses() {
        String sql = UserStatusQueries.SELECT_ALL;

        List<UserStatus> userStatuses = new ArrayList<>();

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                UserStatus userStatus = new UserStatus();
                userStatus.setStatusId(resultSet.getInt("status_id"));
                userStatus.setDescription(resultSet.getString("description"));
                userStatus.setCreatedDate(resultSet.getString("created_date"));
                userStatus.setModifiedDate(resultSet.getString("modified_date"));

                userStatuses.add(userStatus);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return userStatuses;
    }

    @Override
    public UserStatus getStatusByStatusId(int statusId) {
        String sql = UserStatusQueries.SELECT_BY_STATUS_ID;

        UserStatus userStatus = null;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, statusId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                userStatus = new UserStatus();
                userStatus.setStatusId(resultSet.getInt("status_id"));
                userStatus.setDescription(resultSet.getString("description"));
                userStatus.setCreatedDate(resultSet.getString("created_date"));
                userStatus.setModifiedDate(resultSet.getString("modified_date"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return userStatus;
    }

    @Override
    public boolean removeAllStatuses() {
        String sql = UserStatusQueries.REMOVE_ALL_STATUSES;

        try{
            checkConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
