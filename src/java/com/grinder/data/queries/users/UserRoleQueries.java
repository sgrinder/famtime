package com.grinder.data.queries.users;

public class UserRoleQueries {
    public static String SELECT_ALL = "SELECT * FROM roles";
    public static String SELECT_BY_ROLE_ID = "SELECT * FROM roles WHERE role_id = ?";
    public static String INSERT_ROLE = "INSERT INTO roles (description, status_id) VALUES (?,?)";
    public static String UPDATE_ROLE = "UPDATE roles SET description = ?, status_id = ?, modified_date = ? WHERE role_id = ?";
    public static String DELETE_ALL_ROLES = "DELETE FROM roles";
}
