package jqz.fps.DAO;

import jqz.fps.DTO.Pokemon;
import jqz.fps.Utilities.Language;

import java.sql.*;
import java.util.ArrayList;

public class DAOPokemon extends Database {

    // This class only will have static methods for themanagement of the database
    // So it extends "Database" for use the database management methods

    /**
     * This method will return the pokemon that we want
     * @param id is the number of the pokedex entry of the pokemon wanted
     * @return a new object of the Pokemon selected
     */

    public static Pokemon select(int id){

        byte language = DAOConfig.load_language();
        byte abLang = 10;
        byte nameLang = 7;
        byte descLang = 16;
        switch (language){
            case 0: // English
                break;
            case 1: // Spanish
                abLang = 11;
                descLang = 17;
                break;
            case 2: // French
                abLang = 12;
                nameLang = 9;
                descLang = 18;
                break;
            case 3: // Portuguesse
                break;
        }

        Connection connection = connect("data\\pokemon.db");
        PreparedStatement command = null;
        ResultSet resultSet = null;
        // The command and resultSet needs to start as null for close the active connections

        try {
            command = connection.prepareStatement("""
                    SELECT * FROM Pokemon WHERE id = ?;
                    """);
            command.setInt(1, id); // This is the id entered

            resultSet = command.executeQuery();

            while(resultSet.next()){

                // Alright, so here is the hard code for manage the obtained data

                // // Here i manage the abilities
                ArrayList<String> abilityes = new ArrayList<>();
                String[] absObtained = resultSet.getString(abLang).split(",");
                for(String ab : absObtained) abilityes.add(ab);
                // // Here the types
                ArrayList<String> types = new ArrayList<>();
                String[] typesObtained = resultSet.getString(13).split(",");
                for(String type : typesObtained) types.add(type); // it's not too ez dude. :(
                // // And here the stats
                int[] stats = new int[6];
                String[] statsObtained = resultSet.getString(14).split(",");
                for (int i = 0; i < statsObtained.length; i++)
                    stats[i] = Integer.parseInt(statsObtained[i]);

                // Finally i create the object with the data

                Pokemon pokemonObtained = new Pokemon(
                        resultSet.getInt(1),
                        resultSet.getByte(2),
                        Boolean.parseBoolean(resultSet.getString(3)),
                        Boolean.parseBoolean(resultSet.getString(4)),
                        Boolean.parseBoolean(resultSet.getString(5)),
                        Boolean.parseBoolean(resultSet.getString(6)),
                        resultSet.getString(nameLang),
                        resultSet.getString(8),
                        abilityes, types, stats,
                        resultSet.getInt(15),
                        resultSet.getString(descLang)
                );

                return pokemonObtained;
                // I can do this in a line? Yes, but i want to be more explanatory
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close_connection(connection, command, resultSet);
            // We need to close connection or our pc will be processing this all the time 💀
        }

        return null; // If the try fails, it only will return a null object :)
    }

    /**
     * This method, unlike the previous, will return all the pokemons
     * of the database
     * @return an arraylist of all the pokemons in the db
     */

    public static ArrayList<Pokemon> select_all(){

        byte language = DAOConfig.load_language();
        byte abLang = 10;
        byte nameLang = 7;
        byte descLang = 16;
        switch (language){
            case 0: // English
                break;
            case 1: // Spanish
                abLang = 11;
                descLang = 17;
                break;
            case 2: // French
                abLang = 12;
                nameLang = 9;
                descLang = 18;
                break;
            case 3: // Portuguesse
                break;
        }

        Connection connection = connect("data\\pokemon.db");
        PreparedStatement command = null;
        ResultSet resultSet = null;

        ArrayList<Pokemon> pokemons = new ArrayList<>();

        try {
            command = connection.prepareStatement("""
                    SELECT * FROM Pokemon;
                    """);

            resultSet = command.executeQuery();

            while(resultSet.next()){
                // bro this is same as the previos code,
                // here only we need to add the poke to the arraylist

                ArrayList<String> abilityes = new ArrayList<>();
                String[] absObtained = resultSet.getString(abLang).split(",");
                for(String ab : absObtained) abilityes.add(ab);
                ArrayList<String> types = new ArrayList<>();
                String[] typesObtained = resultSet.getString(13).split(",");
                for(String type : typesObtained) types.add(type);
                int[] stats = new int[6];
                String[] statsObtained = resultSet.getString(14).split(",");
                for (int i = 0; i < statsObtained.length; i++)
                    stats[i] = Integer.parseInt(statsObtained[i]);

                Pokemon pokemonObtained = new Pokemon(
                        resultSet.getInt(1),
                        resultSet.getByte(2),
                        Boolean.parseBoolean(resultSet.getString(3)),
                        Boolean.parseBoolean(resultSet.getString(4)),
                        Boolean.parseBoolean(resultSet.getString(5)),
                        Boolean.parseBoolean(resultSet.getString(6)),
                        resultSet.getString(nameLang),
                        resultSet.getString(8),
                        abilityes, types, stats,
                        resultSet.getInt(15),
                        resultSet.getString(descLang)
                );

                pokemons.add(pokemonObtained);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pokemons; // yes, this return the pokemons, duh
    }

    /**
     * This method is not implemented because we won't
     * insert any pokemon in the app
     * @param pokemon is the pokemon we want to insert
     * @return a boolean that confirms the database update
     */

    public static boolean insert(Pokemon pokemon){
        // Insert hard code here...
        return false;
    }

    /**
     * With this method we will be able to update the data of any pokemon,
     * it's not implemented because we don't want to update the database... right?
     * @param id is the id of the pokemon to change
     * @param pokemon is the object of the pokemon what will be to update (?
     * @return a boolean that confirms the database update
     */

    public static boolean update(int id, Pokemon pokemon){
        // Insert hard code here...
        return false;
    }

    /**
     * You know what goes here
     * @param id is the pokemon what we want to delete (?
     * @return a boolean that confirms the blah blah blah
     */

    public static boolean delete(int id){
        // Insert hard code here...
        return false;
    }

}
