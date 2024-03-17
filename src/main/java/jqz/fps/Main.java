package jqz.fps;

import jqz.fps.DAO.DAOConfig;
import jqz.fps.Forms.MainMenu;
import jqz.fps.Utilities.Colors;
import jqz.fps.Utilities.Language;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Bienvenidos al proyecto xd

        prepare_config();

        MainMenu startApp = new MainMenu();
        startApp.setVisible(true);

    }

    public static void prepare_config(){
        boolean isDark = DAOConfig.load_dark_mode();

        // Option Pane
        UIManager.put("OptionPane.background", isDark ? Colors.panelBG_Dark : Colors.panelBG_Light);
        UIManager.put("OptionPane.messageForeground", isDark ? Colors.foreground_Dark : Colors.foreground_Light);
        UIManager.put("OptionPane.buttonForeground", isDark ? Colors.componentBG_Dark : Colors.componentBG_Light);
        // Panel
        UIManager.put("Panel.background", isDark ? Colors.panelBG_Dark : Colors.panelBG_Light);
        UIManager.put("Panel.foreground", isDark ? Colors.foreground_Dark : Colors.foreground_Light);
        // Button
        UIManager.put("Button.background", isDark ? Colors.componentBG_Dark : Colors.componentBG_Light);
        UIManager.put("Button.foreground", isDark ? Colors.foreground_Dark : Colors.foreground_Light);
        // Combo Box
        UIManager.put("ComboBox.foreground", isDark ? Colors.foreground_Dark : Colors.foreground_Light);
        UIManager.put("ComboBox.selectionForeground", isDark ? Colors.foreground_Dark : Colors.foreground_Light);
        UIManager.put("ComboBox.background", isDark ? Colors.componentBG_Dark : Colors.componentBG_Light);
        UIManager.put("ComboBox.selectionBackground", isDark ? Colors.componentBG_Dark : Colors.componentBG_Light);
        // Label
        UIManager.put("Label.foreground", isDark ? Colors.foreground_Dark : Colors.foreground_Light);
        // Text Field
        UIManager.put("TextField.foreground", isDark ? Colors.foreground_Dark : Colors.foreground_Light);

        UIManager.put("OptionPane.yesButtonText", Language.language.get(27));
        UIManager.put("OptionPane.noButtonText", Language.language.get(28));
        UIManager.put("OptionPane.okButtonText", Language.language.get(29));
        UIManager.put("OptionPane.cancelButtonText", Language.language.get(33));
    }

}