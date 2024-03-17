package jqz.fps.Utilities;

import javax.swing.*;
import java.awt.*;

public class Images {

    /*
    The Pokémon images used in this content are property of The Pokémon Company.
    We recognize and respect your copyright, and its use here is purely informational
    with no intention of infringing such rights.
     */

    public static ImageIcon get_pokemon_icon(int id){
        // DISCLAIMER: All the home sprites used have been downloaded through the use of "PokéApi"
        // this project is open source and no profit is sought from it.
        return new ImageIcon("assets\\sprites\\" + id + ".png");
    }

    /**
     * This method will return the icon of the
     * type deserved
     * @param type is the type deserved
     * @return the icon
     */

    public static ImageIcon get_type_icon(String type){
        // DISCLAIMER: All the XY type sprites were obtained through "Bulbapedia"
        return new ImageIcon("assets\\types\\" + Convert.capitalize_first_letter(type) + "IC_XY.png");
    }

    /**
     * This method return the icon of the check
     * @param check can be yes, no or hidden
     * @return the icon xd
     */

    public static ImageIcon get_check_icon(String check){
        return new ImageIcon("assets\\check\\" + Convert.capitalize_first_letter(check) + ".png");
    }

    /**
     * So in this method i scale an image to a new resolution
     * @param originalIcon is the original icon for scale
     * @param width is the width deserved
     * @param height is the height deserved
     * @return the new scaled icon
     */

    public static ImageIcon scale_image_icon(ImageIcon originalIcon, int width, int height) {
        return new ImageIcon(originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

}
