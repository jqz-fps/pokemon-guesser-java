package jqz.fps.DAO;

import jqz.fps.DAO.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOConfig extends Database {

    /**
     * With this method we will load the language we want
     * @return a byte in representation of the language
     */

    public static byte load_language(){
        Connection connection = connect("assets\\usercfg\\config.db");
        PreparedStatement command = null;
        ResultSet resultSet = null;

        try {
            command = connection.prepareStatement("""
                    SELECT language FROM Config
                    """);

            resultSet = command.executeQuery();

            while (resultSet.next()){
                return resultSet.getByte(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close_connection(connection, command, resultSet);
        }

        return 0;
    }

    /**
     * With this method we will know if the dark mode is enabled
     * @return a boolean in representation of the dark mode
     */

    public static boolean load_dark_mode(){
        Connection connection = connect("assets\\usercfg\\config.db");
        PreparedStatement command = null;
        ResultSet resultSet = null;

        try {
            command = connection.prepareStatement("""
                    SELECT dark_mode FROM Config
                    """);

            resultSet = command.executeQuery();

            while (resultSet.next()){
                return Boolean.parseBoolean(resultSet.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close_connection(connection, command, resultSet);
        }

        return false;
    }

    /**
     * With this method we will update the language
     * @param language is the language selected
     */

    public static void update_language(byte language){
        Connection connection = connect("assets\\usercfg\\config.db");
        PreparedStatement command = null;

        try {
            command = connection.prepareStatement("""
                    UPDATE Config SET language = ?
                    """);

            command.setByte(1, language);

            command.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close_connection(connection, command);
        }

    }

    /**
     * With this method we will change the dark mode option
     * @param isDark 0 = disabled, 1 = enabled
     */

    public static void update_dark_mode(byte isDark){
        Connection connection = connect("assets\\usercfg\\config.db");
        PreparedStatement command = null;

        try {
            command = connection.prepareStatement("""
                    UPDATE Config SET dark_mode = ?
                    """);

            command.setString(1, isDark == 0 ? "true" : "false");

            command.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close_connection(connection, command);
        }

    }

}
