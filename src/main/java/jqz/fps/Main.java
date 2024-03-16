package jqz.fps;

import jqz.fps.Forms.MainMenu;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Bienvenidos al proyecto xd

        UIManager.put("OptionPane.background", new Color(206, 244, 252));
        UIManager.put("Panel.background", new Color(206, 244, 252));
        UIManager.put("Button.background", new Color(243, 240, 240));

        MainMenu startApp = new MainMenu();
        startApp.setVisible(true);

    }
}