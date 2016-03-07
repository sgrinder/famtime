package com.grinder.data.queries.users;

public class UserStatusQueries {
    public static final String SELECT_ALL = "SELECT * FROM statuses";
    public static final String SELECT_BY_STATUS_ID = "SELECT * FROM statuses WHERE status_id = ?";
    public static final String INSERT_STATUS = "INSERT INTO statuses (description) VALUES (?)";
    public static final String UPDATE_STATUS = "UPDATE statuses SET description = ?, modified_date = ? WHERE status_id = ?";

    public static final String REMOVE_ALL_STATUSES = "DELETE FROM statuses";
}
