package jqz.fps.Utilities;

import jqz.fps.DAO.DAOConfig;

import java.util.ArrayList;

public class Language {

    public static ArrayList<String> language = get_language();

    /**
     * With this method we will be able to load a language
     * and save it in a local variable. This way it will
     * be easier to access the translations
     * @return the list of translations
     */

    public static ArrayList<String> get_language(){

        byte languageSelected = DAOConfig.load_language();

        String languagesText = FileManager.read_txt_file("assets\\languages\\languages.csv");

        String[] languages = languagesText.split("\r?\n");

        ArrayList<String> finalLanguage = new ArrayList<>();

        for (int i = 0; i < languages.length; i++)
            finalLanguage.add(languages[i].split("\t")[languageSelected].replace("$$$", "\n"));

        return finalLanguage;
    }

}
