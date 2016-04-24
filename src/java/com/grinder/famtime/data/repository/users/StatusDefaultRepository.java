//package com.grinder.famtime.data.repository.users;
//
//import com.grinder.famtime.data.connections.AccessConnection;
//import com.grinder.famtime.data.queries.users.UserStatusQueries;
//import com.grinder.famtime.entities.users.StatusEntity;
//import com.grinder.famtime.data.connections.UserAccessConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StatusDefaultRepository implements StatusRepository {
//    private AccessConnection accessConnection = new UserAccessConnection();
//    private Connection connection;
//    private boolean testMode;
//
//    public StatusDefaultRepository(boolean testMode){
//        this.testMode = testMode;
//    }
//
//    private void checkConnection(){
//        try{
//            if(connection == null || connection.isClosed()){
//                if(testMode)
//                    connection = accessConnection.getTestConnection();
//                else
//                    connection = accessConnection.getConnection();
//            }
//        } catch(Exception ex){
//            System.out.println("Failed to connect to User database :: \n\n" + ex.getMessage());
//        }
//    }
//
//    @Override
//    public boolean createStatus(StatusEntity userStatus) {
//        String sql = UserStatusQueries.INSERT_STATUS;
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, userStatus.getDescription());
//
//            preparedStatement.execute();
//
//            preparedStatement.close();
//            connection.close();
//
//            return true;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public boolean updateStatus(StatusEntity userStatus) {
//        String sql = UserStatusQueries.UPDATE_STATUS;
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, userStatus.getDescription());
//            preparedStatement.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
//            preparedStatement.setInt(3, userStatus.getStatusId());
//
//            preparedStatement.execute();
//
//            preparedStatement.close();
//            connection.close();
//
//            return true;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public List<StatusEntity> getStatuses() {
//        String sql = UserStatusQueries.SELECT_ALL;
//
//        List<StatusEntity> userStatuses = new ArrayList<>();
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()){
//                StatusEntity userStatus = new StatusEntity();
//                userStatus.setStatusId(resultSet.getInt("status_id"));
//                userStatus.setDescription(resultSet.getString("description"));
//                userStatus.setCreatedDate(resultSet.getString("created_date"));
//                userStatus.setModifiedDate(resultSet.getString("modified_date"));
//
//                userStatuses.add(userStatus);
//            }
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//
//        return userStatuses;
//    }
//
//    @Override
//    public StatusEntity getStatusByStatusId(int statusId) {
//        String sql = UserStatusQueries.SELECT_BY_STATUS_ID;
//
//        StatusEntity userStatus = null;
//
//        try{
//            checkConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, statusId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()){
//                userStatus = new StatusEntity();
//                userStatus.setStatusId(resultSet.getInt("status_id"));
//                userStatus.setDescription(resultSet.getString("description"));
//                userStatus.setCreatedDate(resultSet.getString("created_date"));
//                userStatus.setModifiedDate(resultSet.getString("modified_date"));
//            }
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//
//        return userStatus;
//    }
//
//    @Override
//    public boolean removeAllStatuses() {
//        String sql = UserStatusQueries.REMOVE_ALL_STATUSES;
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
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
//}
