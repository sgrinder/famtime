package com.grinder.data.queries.users;

public class UserAccountRoleQueries {
    public static final String SELECT_ALL = "SELECT * FROM account_roles";
    public static final String SELECT_BY_ROLE_ID = "SELECT * FROM account_roles WHERE role_id = ?";
    public static final String SELECT_BY_ACCOUNT_ID = "SELECT * FROM account_roles WHERE account_id = ?";
    public static final String INSERT_ACCOUNT_ROLE = "INSERT INTO account_roles (account_id, role_id, status_id) VALUES (?,?,?)";
    public static final String UPDATE_ACCOUNT_ROLE = "UPDATE account_roles SET account_id = ?, role_id = ?, status_id = ?, modified_date = ? WHERE account_role_id = ?";
    public static final String REMOVE_ACCOUNT_ROLE_BY_ID = "DELETE FROM account_roles WHERE account_role_id = ?";
    public static final String REMOVE_ALL_ACCOUNT_ROLES = "DELETE FROM account_roles";
}
