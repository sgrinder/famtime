package com.grinder.data.queries.users;

public class UserAccountQueries {
    public static final String SELECT_ALL = "SELECT * FROM accounts";
    public static final String SELECT_BY_ACCOUNT_ID = "SELECT * FROM accounts WHERE account_id = ?";
    public static final String SELECT_BY_ACCOUNT_NAME = "SELECT * FROM accounts WHERE account_name = ?";
    public static final String SELECT_BY_ACCOUNT_NAME_AND_PASSWORD = "SELECT * FROM accounts WHERE account_name = ? AND password = ?";
    public static final String UPDATE_ACCOUNT = "UPDATE accounts SET account_name = ?, password = ?, status_id = ?, modified_date= ? WHERE account_id = ?";
    public static final String INSERT_ACCOUNT = "INSERT INTO accounts (account_name, password, status_id) VALUES (?,?,?)";
    public static final String DELETE_ALL_ACCOUNTS = "DELETE FROM accounts";
}
