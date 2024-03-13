package jqz.fps.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {

    // This class is only used for use the plugin of sqlite

    /**
     * This method makes the connection of the database
     * @return the connection
     */

    public static java.sql.Connection connect(){
        try {
            return DriverManager.getConnection
                    ("jdbc:sqlite:" + System.getProperty("user.dir") + "\\database\\pokemon.db");
            // This is the directory of the database
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * If we start a connection, we need to close it right?
     * so this is the method for close the connection and a command
     * @param connection is the active connection
     * @param command is a command used in the query
     */

    public static void close_connection(java.sql.Connection connection, Statement command){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            command.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is same as the previous, just add a parameter
     * @param connection is the active connection
     * @param command is the command used in the query
     * @param resultSet are the results of the query
     */

    public static void close_connection(java.sql.Connection connection, Statement command, ResultSet resultSet){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(command != null) {
                command.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
