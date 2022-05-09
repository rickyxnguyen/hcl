package com.hcl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertSQL {
    private static final String INSERT_USERS_SQL = "INSERT INTO users" +
            "  (id, name, email, country, password) VALUES " +
            " (?, ?, ?, ?, ?);";

    public static void main(String[] argv) throws SQLException {
        InsertSQL createTableExample = new InsertSQL();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = ExampleJDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "jack");
            preparedStatement.setString(3, "jack@gmail.com");
            preparedStatement.setString(4, "US");
            preparedStatement.setString(5, "secret");

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            ExampleJDBCUtils.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
}